package com.ysla.web.controller.user;

import com.ysla.api.common.JsonApi;
import com.ysla.web.config.shiro.JwtUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户api
 * @author konghang
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @ApiOperation(value="用户登录", notes="用户登录,使用用户名和邮箱都可以")
    @RequestMapping(value="/login", method= RequestMethod.POST, produces="application/json;charset=UTF-8")
    public JsonApi login(String username, String password) {
        JsonApi jsonApi = new JsonApi();
        String token = JwtUtil.createToken(username,"123456");
        jsonApi.setMessage(token);
        return jsonApi;
    }

}
