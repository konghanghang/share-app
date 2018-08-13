package com.ysla.web.controller.user;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ysla.api.auto.model.User;
import com.ysla.api.model.common.ErrorCode;
import com.ysla.api.model.common.JsonApi;
import com.ysla.api.model.exception.AuthorizeException;
import com.ysla.api.model.exception.BaseException;
import com.ysla.api.model.exception.TxException;
import com.ysla.api.module.user.IUserService;
import com.ysla.api.utils.http.IpUtils;
import com.ysla.utils.crypto.CryptoUtils;
import com.ysla.utils.string.StringUtils;
import com.ysla.web.config.shiro.JwtUtil;
import com.ysla.web.vo.UserVO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 用户api
 * @author konghang
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Reference(version = "${dubbo.service.version}")
    private IUserService userService;

    @ApiOperation(value="用户登录", notes="用户登录,使用用户名和邮箱都可以")
    @PostMapping(value="/login", produces="application/json;charset=UTF-8")
    public JsonApi login(String username, String password) {
        User search = new User();
        search.setUsername(username);
        User user = userService.selectUserBy(search);
        if (StringUtils.isEmpty(user)){
            search.setUsername(null);
            search.setEmail(username);
            user = userService.selectUserBy(search);
        }
        if (StringUtils.isEmpty(user)){
            throw new AuthorizeException(ErrorCode.USER_UNKNOWN_ACCOUNT);
        }
        String token = "";
        if (CryptoUtils.verifyPassword(user.getPassword(), password + user.getSalt() + username)){
            token = JwtUtil.createToken(username,user.getSalt());
        } else {
            throw new AuthorizeException(ErrorCode.USER_ERROR_PASSWORD);
        }
        JSONObject json = JSON.parseObject(JSON.toJSONString(user));
        json.remove("password");
        json.remove("salt");
        return JsonApi.isOk().message(token).data(json);
    }

    @ApiOperation(value="用户注册", tags={"注册"}, notes="用户可以注册", produces="application/json;charset=UTF-8")
    @PostMapping(value = "/register")
    public JsonApi register(@Valid @ApiParam(name="用户对象",value="传入json格式",required=true) UserVO userVO,
                            HttpServletRequest request) {
        User user = new User();
        BeanUtils.copyProperties(userVO,user);
        user.setCreateIp(IpUtils.getRealIp(request));
        try {
            userService.saveUser(user);
        } catch (TxException e) {
            throw new BaseException(e.getErrorCode());
        }
        return JsonApi.isOk();
    }

    @ApiOperation(value="用户信息", tags={"获取用户信息"}, notes="获取用户信息")
    @RequestMapping(value = "/", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    public JsonApi userInfo(@RequestHeader(name = "Authorization", required = true) String token){
        String username = JwtUtil.getUsername(token);
        User user = new User();
        user.setUsername(username);
        user = userService.selectUserBy(user);
        JSONObject json = JSON.parseObject(JSON.toJSONString(user));
        json.remove("password");
        json.remove("salt");
        return JsonApi.isOk().data(json);
    }

}
