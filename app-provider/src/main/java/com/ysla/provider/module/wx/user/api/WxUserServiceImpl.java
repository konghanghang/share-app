package com.ysla.provider.module.wx.user.api;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONObject;
import com.ysla.api.auto.model.WxUser;
import com.ysla.api.model.wx.api.WechatApi;
import com.ysla.api.module.wx.mp.IWxMpService;
import com.ysla.api.module.wx.user.IWxUserService;
import com.ysla.api.utils.http.HttpClientUtils;
import com.ysla.api.utils.wx.WxUserUtils;
import com.ysla.provider.module.wx.user.dao.IWxUserDao;
import com.ysla.utils.date.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 微信用户api实现类
 * @author konghang
 */
@Slf4j
@Component
@Service(version = "${dubbo.service.version}")
public class WxUserServiceImpl implements IWxUserService {

    @Resource
    private IWxMpService mpService;
    @Resource
    private IWxUserDao wxUserDao;

    private final String OPENID = "OPENID";
    private final String ACCESS_TOKEN = "ACCESS_TOKEN";
    private final String ERROR_CODE = "errcode";

    /**
     * 初始化微信用户信息保存到数据库
     * @param appId
     * @return
     */
    @Override
    public List<String> initUserFromWx(String appId) {
        String url = WechatApi.M_USER_GET.getUrl();
        String accessToken = mpService.getAccessToken(appId);
        List<String> list = new ArrayList<>();
        getUser(list,url,accessToken,1);
        list.stream().forEach(openId -> getUserInfo(openId,appId));
        return list;
    }

    /**
     * 获取微信用户信息
     * @param openId
     * @param appId
     * @return
     */
    @Override
    public WxUser getUserInfo(String openId, String appId) {
        String url = WechatApi.M_USER_INFO.getUrl().replace(OPENID,openId);
        String accessToken = mpService.getAccessToken(appId);
        JSONObject json = HttpClientUtils.httpGet(url.replace(ACCESS_TOKEN, accessToken));
        Integer errorCode = json.getInteger(ERROR_CODE);
        WxUser wxUser = null;
        if(errorCode == null){
            wxUser = WxUserUtils.copyWxUserInfo(json);
            wxUser.setLastUpdateDate(DateUtils.getUnixStamp());
            wxUser.setAppId(appId);
            wxUser.setCreateDate(DateUtils.getUnixStamp());
            saveUser(wxUser);
        }else {
            log.error(json.toJSONString());
        }
        return wxUser;
    }

    /**
     * 保存微信用户信息
     * @param wechatUser
     * @return
     */
    @Override
    public int saveUser(WxUser wechatUser) {
        return wxUserDao.insertUser(wechatUser);
    }

    private List<String> getUser(List<String> list,String url, String accessToken, Integer num){
        JSONObject json = HttpClientUtils.httpGet(url.replace(ACCESS_TOKEN, accessToken));
        Integer errorCode = json.getInteger(ERROR_CODE);
        if(errorCode == null){
            Integer total = json.getInteger("total");
            Integer count = json.getInteger("count");
            String nextOpenId = json.getString("next_openid");
            List<String> temp = (List<String>) json.getJSONObject("data").get("openid");
            list.addAll(temp);
            if(count * num < total){
                url = WechatApi.M_USER_GET.getUrl()+"&&next_openid=NEXT_OPENID";
                getUser(list,url.replace("NEXT_OPENID",nextOpenId),accessToken,num++);
            }
        }else {
            log.error(json.toJSONString());
        }
        return list;
    }
}
