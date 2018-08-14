package com.ysla.web.controller.article;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.ysla.api.auto.model.Article;
import com.ysla.api.auto.model.User;
import com.ysla.api.model.common.BaseMap;
import com.ysla.api.model.common.ErrorCode;
import com.ysla.api.model.common.JsonApi;
import com.ysla.api.model.exception.BaseException;
import com.ysla.api.model.exception.TxException;
import com.ysla.api.model.page.PageModel;
import com.ysla.api.module.article.IArticleService;
import com.ysla.api.module.user.IUserService;
import com.ysla.api.utils.http.IpUtils;
import com.ysla.utils.date.DateUtils;
import com.ysla.utils.string.StringUtils;
import com.ysla.web.config.shiro.JwtUtil;
import com.ysla.web.vo.ArticleVO;
import io.swagger.annotations.*;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * 文章api
 * @author konghang
 */
@CrossOrigin
@Api(tags = "文章api", description = "文章api")
@RestController
@RequestMapping("/api/article")
public class ArticleController {

    @Reference(version = "${dubbo.service.version}", check = false, timeout = 10000)
    private IArticleService articleService;

    @Reference(version = "${dubbo.service.version}", check = false, timeout = 10000)
    private IUserService userService;

    //@IpStatisticsAop
    @ApiOperation(value="添加文章", httpMethod = "POST", notes="所有字段均不能为空,且标题不能超过100字")
    @ApiImplicitParam(name = "articleVO", value = "文章详细实体articleVO", required = true)
    @ApiResponse(code = 200, message = "success", response = JsonApi.class)
    @PostMapping(value = "",produces="application/json;charset=UTF-8")
    @ResponseStatus(HttpStatus.CREATED)
    public JsonApi add(@Valid ArticleVO articleVO,
                       @RequestHeader(name = "Authorization") String token,
                       HttpServletRequest request) {
        Article article = new Article();
        BeanUtils.copyProperties(articleVO, article);
        User user = new User();
        user.setUsername(JwtUtil.getUsername(token));
        user = userService.selectUserBy(user);
        article.setRefUserId(user.getRefUserId());
        article.setRefArticleId(StringUtils.getUUID());
        article.setCreateDate(DateUtils.getUnixStamp());
        article.setUpdateDate(DateUtils.getUnixStamp());
        article.setCreateIp(IpUtils.getRealIp(request));
        if(articleService.insert(article)>0){
            BaseMap baseMap = new BaseMap("id",article.getRefArticleId());
            return JsonApi.isOk().data(baseMap);
        }else {
            throw new BaseException(ErrorCode.SYSTEM_ERROR);
        }
    }

    //@ArticleAop
    @ApiOperation(value="获取某一篇文章", notes="根据文章ID获取文章")
    @ApiImplicitParam(name = "articleId", value = "文章ID", required = true, dataType = "String")
    @GetMapping(value = "/{articleId}", produces="application/json;charset=UTF-8")
    public JsonApi get(@PathVariable String articleId,
                       @RequestHeader(name = "Authorization", required = false) String token){
        JSONObject json = articleService.getArticleByRef(articleId,JwtUtil.getUsername(token));
        return JsonApi.isOk().data(json);
    }

    @ApiOperation(value = "更新文章内容",notes = "更新文章")
    @ApiImplicitParam(name = "articleVo", value = "文章Vo", required = true)
    @PatchMapping(value = "", produces="application/json;charset=UTF-8")
    @ResponseStatus(HttpStatus.CREATED)
    public JsonApi update(@Valid ArticleVO articleVO) throws TxException {
        if(articleVO.getArticleId() == null || "".equals(articleVO.getArticleId())){
            throw new TxException(ErrorCode.PARAM_IS_NULL);
        }
        Article article = new Article();
        BeanUtils.copyProperties(articleVO,article);
        article.setRefArticleId(articleVO.getArticleId());
        article.setUpdateDate(DateUtils.getUnixStamp());
        int result = articleService.updateByRefSelective(article);
        if(result < 1){
            throw new TxException(ErrorCode.ARTICLE_ID_ERROR);
        }
        return JsonApi.isOk().data(articleVO.getArticleId());
    }

    @ApiOperation(value = "删除文章",notes = "删除文章")
    @ApiImplicitParam(name = "articleId", value = "文章id", required = true, dataType = "Integer")
    @DeleteMapping(value = "/{articleId}", produces="application/json;charset=UTF-8")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public JsonApi delete(@PathVariable String articleId) {
        Article article = new Article();
        article.setStatus((byte)1);
        article.setRefArticleId(articleId);
        int result = articleService.updateByRefSelective(article);
        if(result < 1){
            throw new BaseException(ErrorCode.ARTICLE_ID_ERROR);
        }
        return JsonApi.isOk();
    }

    @ApiOperation(value="获取所有文章", notes="根据页码获取某一页的文章:页条数默认为10")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", dataType = "Integer")
    })
    @GetMapping(value = "", produces="application/json;charset=UTF-8")
    public JsonApi articles(@RequestParam(value = "pageNo") int pageNo,
                            @RequestParam(value = "pageSize",required = false, defaultValue = "10") int pageSize){
        PageModel pageModel = new PageModel(pageNo,pageSize);
        pageModel = articleService.articles(null,pageModel);
        return JsonApi.isOk().data(pageModel);
    }

    @ApiOperation(value = "文章精选",notes = "文章精选列表")
    @GetMapping(value = "/chosen", produces="application/json;charset=UTF-8")
    public JsonApi chosen(){
         List<JSONObject> list = articleService.chosen();
         return JsonApi.isOk().data(list);
    }

    /*@RequiresPermissions(value={"user:view"}, logical= Logical.OR)*/
    @ApiOperation(value = "获取自己所有文章", notes = "页码和页条数默认分别为1和15,参数可以不传")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", dataType = "Integer")
    })
    @GetMapping(value = "/me", produces="application/json;charset=UTF-8")
    public JsonApi articlesMe(@RequestParam(value = "pageNo", required = false, defaultValue = "1") int pageNo,
                              @RequestParam(value = "pageSize", required = false, defaultValue = "15") int pageSize,
                              @RequestHeader(name = "Authorization") String token){
        PageModel pageModel = PageModel.startPage(pageNo,pageSize);
        pageModel = articleService.articles(JwtUtil.getUsername(token),pageModel);
        return JsonApi.isOk().data(pageModel);
    }
}
