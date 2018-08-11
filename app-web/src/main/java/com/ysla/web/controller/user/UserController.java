package com.ysla.web.controller.user;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ysla.api.auto.model.User;
import com.ysla.api.model.common.JsonApi;
import com.ysla.api.module.user.IUserService;
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

    @Reference(version = "${dubbo.service.version}")
    private IUserService userService;

    @ApiOperation(value="用户登录", notes="用户登录,使用用户名和邮箱都可以")
    @RequestMapping(value="/login", method= RequestMethod.POST, produces="application/json;charset=UTF-8")
    public JsonApi login(String username, String password) {
        JsonApi jsonApi = new JsonApi();
        String token = JwtUtil.createToken(username,"123456");
        jsonApi.setMessage(token);
        return jsonApi;
    }

    @ApiOperation(value="测试方法", notes="用来测试")
    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String test(){
        User user = new User();
        user.setUsername("konghang");
        user = userService.selectUserBy(user);
        System.out.println(user.toString());
        JSONObject jsonObject = userService.simpleInfo("afa379e415b044aca48a7e0d2025d4b1");
        System.out.println(JSON.toJSONString(jsonObject));
        JSONObject json = userService.simpleInfo2("afa379e415b044aca48a7e0d2025d4b1");
        System.out.println(JSON.toJSONString(json));
        return "success";
    }

}
