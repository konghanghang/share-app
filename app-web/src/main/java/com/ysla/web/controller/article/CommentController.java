package com.ysla.web.controller.article;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageHelper;
import com.ysla.api.auto.model.ArticleComment;
import com.ysla.api.auto.model.User;
import com.ysla.api.model.common.JsonApi;
import com.ysla.api.model.page.PageModel;
import com.ysla.api.module.article.IArticleCommentService;
import com.ysla.api.module.user.IUserService;
import com.ysla.api.utils.http.IpUtils;
import com.ysla.utils.date.DateUtils;
import com.ysla.utils.string.StringUtils;
import com.ysla.web.annotation.Articles;
import com.ysla.web.config.shiro.JwtUtil;
import com.ysla.web.vo.CommentVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 文章评论api
 * @author konghang
 */
@CrossOrigin
@Api(tags = "文章评论api", description = "文章评论api")
@RestController
@RequestMapping("/api/article/comment")
public class CommentController {

    @Reference(version = "${dubbo.service.version}", check = false, timeout = 10000)
    private IUserService userService;

    @Reference(version = "${dubbo.service.version}", check = false, timeout = 10000)
    private IArticleCommentService commentService;

    @Articles(comment = 1)
    @ApiOperation(value="添加文章评论", httpMethod = "POST", notes="新增文章评论")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "commentVo", value = "评论vo", required = true),
            @ApiImplicitParam(name = "Authorization", value = "RequestHeader:Authorization", required = true, type = "String")
    })
    @PostMapping(produces="application/json;charset=UTF-8")
    public JsonApi add(@Valid CommentVO commentVO,
                       @RequestHeader(name = "Authorization") String token,
                       HttpServletRequest request){
        User user = new User();
        user.setUsername(JwtUtil.getUsername(token));
        user = userService.selectUserBy(user);
        ArticleComment comment = new ArticleComment();
        BeanUtils.copyProperties(commentVO, comment);
        comment.setRefCommentId(StringUtils.getUUID());
        comment.setRefArticleId(commentVO.getArticleId());
        comment.setReplayUser(user.getRefUserId());
        comment.setReplayTo(commentVO.getReplayTo());
        comment.setApprove(0);
        comment.setCreateDate(DateUtils.getUnixStamp());
        comment.setCreateIp(IpUtils.getRealIp(request));
        commentService.addComment(comment);
        return JsonApi.isOk().data(comment);
    }

    @ApiOperation(value="删除文章评论", httpMethod = "DELETE", notes="删除文章评论")
    @ApiImplicitParam(name = "commentId", value = "评论id", required = true)
    @DeleteMapping(value = "/{commentId}", produces="application/json;charset=UTF-8")
    public JsonApi delete(@PathVariable String commentId){
        commentService.delete(commentId);
        return JsonApi.isOk();
    }

    @ApiOperation(value="获取评论列表", httpMethod = "GET", notes="评论列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "articleId", value = "文章id", required = true, type = "String"),
            @ApiImplicitParam(name = "pageNo", value = "页码", required = true, type = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "页条数", required = true, type = "Integer"),
            @ApiImplicitParam(name = "Authorization", value = "RequestHeader:Authorization", required = true, type = "String")
    })
    @GetMapping(value = "/{articleId}", produces="application/json;charset=UTF-8")
    public JsonApi list(@PathVariable String articleId,
                        @RequestParam(name = "pageNo", required = false, defaultValue = "1") int pageNo,
                        @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize,
                        @RequestHeader(name = "Authorization") String token){
        PageModel pageModel = PageModel.startPage(pageNo,pageSize);
        pageModel = commentService.getOneArticleComment(articleId, JwtUtil.getUsername(token), pageModel);
        return JsonApi.isOk().data(pageModel);
    }

    @ApiOperation(value="获取收到的评论列表", httpMethod = "GET", notes="收到的评论列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码", required = true, type = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "页条数", required = true, type = "Integer"),
            @ApiImplicitParam(name = "Authorization", value = "RequestHeader:Authorization", required = true, type = "String")
    })
    @GetMapping(value = "/received",produces="application/json;charset=UTF-8")
    public JsonApi received(@RequestParam(name = "pageNo", required = false, defaultValue = "1") int pageNo,
                            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize,
                            @RequestHeader(name = "Authorization") String token){

        PageModel pageModel = PageModel.startPage(pageNo,pageSize);
        pageModel = commentService.received(JwtUtil.getUsername(token),pageModel);
        return JsonApi.isOk().data(pageModel);
    }

    @ApiOperation(value="获取用户的评论历史列表", httpMethod = "GET", notes="评论列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码", required = true, type = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "页条数", required = true, type = "Integer"),
            @ApiImplicitParam(name = "Authorization", value = "RequestHeader:Authorization", required = true, type = "String")
    })
    @GetMapping(value = "/replayTo", produces="application/json;charset=UTF-8")
    public JsonApi replayTo(@RequestParam(name = "pageNo", required = false, defaultValue = "1") int pageNo,
                            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize,
                            @RequestHeader(name = "Authorization") String token){
        PageHelper.startPage(pageNo, pageSize);
        PageModel pageModel = PageModel.startPage(pageNo,pageSize);
        pageModel = commentService.replayTo(JwtUtil.getUsername(token),pageModel);
        return JsonApi.isOk().data(pageModel);
    }

}
