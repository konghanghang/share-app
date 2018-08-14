package com.ysla.web.controller.user;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ysla.api.auto.model.User;
import com.ysla.api.auto.model.UserCollect;
import com.ysla.api.model.common.ErrorCode;
import com.ysla.api.model.common.JsonApi;
import com.ysla.api.model.exception.BaseException;
import com.ysla.api.model.exception.TxException;
import com.ysla.api.model.page.PageModel;
import com.ysla.api.module.user.IUserCollectService;
import com.ysla.api.module.user.IUserService;
import com.ysla.web.config.shiro.JwtUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * 用户收藏api
 * @author konghang
 */
@CrossOrigin
@RestController
@RequestMapping("/api/collect")
public class UserCollectController {

    @Reference(version = "${dubbo.service.version}",check = false, timeout = 10000)
    private IUserCollectService collectService;

    @Reference(version = "${dubbo.service.version}",check = false, timeout = 10000)
    private IUserService userService;

    @ApiOperation(value="收藏api", notes="可以是收藏的文章,也可以是对评论的点赞")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "linkId",value = "可以是文章id,也可以是评论id",required = true,type = "String"),
            @ApiImplicitParam(name = "type",value = "1:文章,2:评论",required = true,type = "Byte"),
            @ApiImplicitParam(name = "Authorization",value = "RequestHeader",required = true,type = "String")
    })
    @PostMapping(produces="application/json;charset=UTF-8")
    public JsonApi collect(@RequestParam(name = "linkId") String linkId,
                           @RequestParam(name = "type", defaultValue = "1") byte type,
                           @RequestHeader(name = "Authorization") String token){
        User user = new User();
        user.setUsername(JwtUtil.getUsername(token));
        user = userService.selectUserBy(user);
        UserCollect userCollect = new UserCollect();
        userCollect.setLinkId(linkId);
        userCollect.setUserId(user.getRefUserId());
        userCollect.setType(type);

        try {
            collectService.collect(userCollect);
        } catch (TxException e) {
            throw new BaseException(e.getErrorCode());
        }
        return JsonApi.isOk().data(userCollect);
    }

    @ApiOperation(value="删除收藏", notes="删除收藏的文章或者取消评论点赞")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "collectId",value = "收藏id",required = true,type = "String"),
            @ApiImplicitParam(name = "type",value = "1:文章,2:评论",required = true,type = "Byte"),
    })
    @DeleteMapping(value = "/{collectId}", produces="application/json;charset=UTF-8")
    public JsonApi delete(@PathVariable String collectId,
                          @RequestParam(name = "type", defaultValue = "1") byte type){
        int result = 0;
        try {
            result = collectService.deleteCollect(collectId,type);
        } catch (TxException e) {
            throw new BaseException(e.getErrorCode());
        }
        if(result < 1){
            throw new BaseException(ErrorCode.ARTICLE_ID_ERROR);
        }
        return JsonApi.isOk().data(collectId);
    }

    @ApiOperation(value="收藏文章列表", notes="用户收藏的文章列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码", required = true, type = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "页条数", required = true, type = "Integer"),
            @ApiImplicitParam(name = "Authorization", value = "RequestHeader:Authorization", required = true, type = "String")
    })
    @GetMapping(produces="application/json;charset=UTF-8")
    public JsonApi articleCollects(@RequestParam(name = "pageNo", required = false, defaultValue = "1") int pageNo,
                                   @RequestParam(name = "pageSize", required = false, defaultValue = "15") int pageSize,
                                   @RequestHeader(name = "Authorization") String token){
        PageModel pageModel = PageModel.startPage(pageNo, pageSize);
        pageModel = collectService.articleCollects(JwtUtil.getUsername(token), pageModel);
        return JsonApi.isOk().data(pageModel);
    }

}
