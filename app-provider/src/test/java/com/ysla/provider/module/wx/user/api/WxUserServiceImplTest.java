package com.ysla.provider.module.wx.user.api;

import com.ysla.api.auto.model.WxUser;
import com.ysla.api.module.wx.user.IWxUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class WxUserServiceImplTest {

    @Resource
    private IWxUserService userService;
    @Value("${com.ysla.wechat.appId}")
    private String appId;

    @Test
    public void initUserFromWx() {
        List<String> list =  userService.initUserFromWx(appId);
        list.stream().forEach(System.out::println);
    }

    @Test
    public void getUserInfo() {
        WxUser wxUser = userService.getUserInfo("oZPbCv9szzusPj9PrREzaittN-x4",appId);
        System.out.println(wxUser);
    }

    @Test
    public void saveUser() {
        WxUser wxUser = userService.getUserInfo("oZPbCv9szzusPj9PrREzaittN-x4",appId);
        userService.saveUser(wxUser);
    }
}