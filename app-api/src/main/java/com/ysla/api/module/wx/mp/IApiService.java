package com.ysla.api.module.wx.mp;

import com.alibaba.fastjson.JSONObject;
import com.ysla.api.auto.model.WxMp;
import com.ysla.api.auto.model.WxUser;
import com.ysla.api.model.wx.oauth.OauthScope;
import com.ysla.api.model.wx.oauth.WxAccessToken;

/**
 * 微信api服务类
 * @author konghang
 */
public interface IApiService {

    /**
     * 1. 引导用户开始授权
     * 默认是基础授权,静默跳转
     * @param redirectUri
     * @param appId
     * @return
     */
    String getOauthConnectUrl(String redirectUri, String appId);

    /**
     * 默认是基础授权,指定授权方式
     * @param redirectUri
     * @param scope
     * @param appId
     * @return
     */
    String getOauthConnectUrl(String redirectUri, OauthScope scope, String appId);

    /**
     * 默认是基础授权,指定授权方式以及state
     * @param redirectUri
     * @param scope
     * @param state
     * @param appId
     * @return
     */
    String getOauthConnectUrl(String redirectUri, OauthScope scope, String state, String appId);

    /**
     * 2. 用户确认授权后,得到授权凭证code,获取accessToken
     * @param code
     * @param appId
     * @return
     */
    WxAccessToken getAccessToken(String code, String appId);

    /**
     * 3. 通过授权获得用户的基础信息
     * @param accessToken
     * @param openId
     * @return
     */
    WxUser getWxUserInfo(String accessToken, String openId);

    /**
     * 获取二维码先获取ticket
     * @param ticket
     * @param appId
     * @return
     */
    JSONObject createTicket(String ticket, String appId);

    /**
     * 微信登录，用户授权获取用户信息
     * @param code
     * @param appId
     * @return
     */
    JSONObject wxLoginGetAccessToken(String code, String appId);

    /**
     * 创建公众号菜单
     * @param menuData
     * @param appId
     * @return
     */
    JSONObject createMenu(String menuData, String appId);

    /**
     * 刷新accessToken
     * @param mp
     * @return
     */
    boolean refreshAccessToken(WxMp mp);

    /**
     * 刷新jsApiTicket
     * @param mp
     * @return
     */
    boolean refreshJsApiTicket(WxMp mp);

    /**
     * 刷新卡券apiTicket
     * @param mp
     * @return
     */
    boolean refreshApiTicket(WxMp mp);

}
