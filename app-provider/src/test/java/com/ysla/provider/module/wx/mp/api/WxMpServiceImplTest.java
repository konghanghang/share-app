package com.ysla.provider.module.wx.mp.api;

import com.ysla.api.auto.model.WxMp;
import com.ysla.api.module.wx.mp.IWxMpService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class WxMpServiceImplTest {

    @Resource
    private IWxMpService mpService;
    @Value("${com.ysla.wechat.appId}")
    private String appId;

    @Test
    public void getWxMp() {
        WxMp wxMp = mpService.getWxMp(appId);
        Assert.assertEquals("wx6293aeebbff7a4e6",wxMp.getAppId());
    }

    @Test
    public void updateMp() {
        WxMp wxMp = new WxMp();
        wxMp.setCreateBy("konghang");
        wxMp.setMpId(2);
        boolean result = mpService.updateMp(wxMp);
        Assert.assertEquals(true,result);
    }

    @Test
    public void getAccessToken() {
        String accessToken = mpService.getAccessToken(appId);
        Assert.assertNotNull(accessToken);
    }

    @Test
    public void getJsApiTicket() {
        String result = mpService.getJsApiTicket(appId);
        System.out.println(result);
    }

    @Test
    public void getApiTicket() {
        String result = mpService.getApiTicket(appId);
        System.out.println(result);
    }
}