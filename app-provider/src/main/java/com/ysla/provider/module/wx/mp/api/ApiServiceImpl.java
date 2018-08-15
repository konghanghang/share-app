package com.ysla.provider.module.wx.mp.api;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONObject;
import com.ysla.api.auto.model.WxMp;
import com.ysla.api.auto.model.WxUser;
import com.ysla.api.model.common.ErrorCode;
import com.ysla.api.model.wx.api.WechatApi;
import com.ysla.api.model.wx.oauth.OauthScope;
import com.ysla.api.model.wx.oauth.WxAccessToken;
import com.ysla.api.module.wx.mp.IApiService;
import com.ysla.api.module.wx.mp.IWxMpService;
import com.ysla.api.utils.http.HttpClientUtils;
import com.ysla.api.utils.http.UrlUtils;
import com.ysla.api.utils.wx.WxUserUtils;
import com.ysla.utils.date.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 微信api服务实现类
 * @author konghang
 */
@Slf4j
@Component
@Service(version = "${dubbo.service.version}")
public class ApiServiceImpl implements IApiService {

    @Resource
    private IWxMpService mpService;

    /**
     * 1. 引导用户开始授权
     * 默认是基础授权,静默跳转
     * @param redirectUri
     * @param appId
     * @return
     */
    @Override
    public String getOauthConnectUrl(String redirectUri, String appId) {
        return getOauthConnectUrl(redirectUri, OauthScope.BASE, appId);
    }

    /**
     * 默认是基础授权,指定授权方式
     * @param redirectUri
     * @param scope
     * @param appId
     * @return
     */
    @Override
    public String getOauthConnectUrl(String redirectUri, OauthScope scope, String appId) {
        return getOauthConnectUrl(redirectUri, scope, "",appId);
    }

    /**
     * 默认是基础授权,指定授权方式以及state
     * @param redirectUri
     * @param scope
     * @param state
     * @param appId
     * @return
     */
    @Override
    public String getOauthConnectUrl(String redirectUri, OauthScope scope, String state, String appId) {
        String redirectUrl = "http://" + mpService.getWxMp(appId).getJsOauthUrl() + redirectUri;
        String connectUrl = WechatApi.AUTHORIZE_URL.getUrl();
        String oauthScope = scope.getScope();
        if (state.length() > 100) {
            log.info("stateUrl too Long:" + state);
            state = state.substring(0, 100);
        }
        String stateUrl = UrlUtils.urlEncode(state);

        if (stateUrl.length() >= 128) {
            //微信允许的最大state长度是128位
            log.info("stateUrl too Long" + stateUrl);
            //进一步的减少
            stateUrl = UrlUtils.urlEncode(state.substring(0, 80));
        }
        return connectUrl.replace("APPID", appId)
                .replace("REDIRECT_URI", UrlUtils.urlEncode(redirectUrl))
                .replace("SCOPE", oauthScope)
                .replace("STATE", stateUrl);
    }

    /**
     * 2. 用户确认授权后,得到授权凭证code,获取accessToken
     * @param code
     * @param appId
     * @return
     */
    @Override
    public WxAccessToken getAccessToken(String code, String appId) {
        String oauthUrl = WechatApi.GET_ACCESS_TOKEN.getUrl();
        WxMp mp = mpService.getWxMp(appId);
        JSONObject oauthResult = HttpClientUtils.httpGet(
                oauthUrl
                .replace("APPID", mp.getAppId())
                .replace("SECRET", mp.getAppSecret())
                .replace("CODE", code));
        if(oauthResult == null){
            log.error("Wechat Get Oauth AccessToken Fail");
            return new WxAccessToken(ErrorCode.SYSTEM_ERROR);
        }
        return new WxAccessToken(oauthResult);
    }

    /**
     * 3. 通过授权获得用户的基础信息
     * @param accessToken
     * @param openId
     * @return
     */
    @Override
    public WxUser getWxUserInfo(String accessToken, String openId) {
        String userInfoUrl = WechatApi.USER_INFO.getUrl();
        JSONObject userInfoResult = HttpClientUtils.httpGet(userInfoUrl.replace("ACCESS_TOKEN", accessToken)
                .replace("OPENID", openId));
        if(userInfoResult == null){
            log.info("getWxUserInfo fail");
            return null;
        }
        if (userInfoResult.getInteger("errcode") != null) {
            log.error("getWxUserInfo fail:" + userInfoResult.getString("errmsg") + ", openId:" + openId);
            return null;
        }
        //返回成功
        return WxUserUtils.copyWxUserInfo(userInfoResult);
    }

    /**
     * 获取二维码先获取ticket
     * @param ticket
     * @param appId
     * @return
     */
    @Override
    public JSONObject createTicket(String ticket, String appId) {
        String url = WechatApi.QR_CODE_TICKET.getUrl();
        String accessToken = mpService.getAccessToken(appId);
        return HttpClientUtils.httpPostJson(url.replace("TOKEN", accessToken), ticket);
    }

    /**
     * 微信登录，用户授权获取用户信息
     * @param code
     * @param appId
     * @return
     */
    @Override
    public JSONObject wxLoginGetAccessToken(String code, String appId) {
        WxMp mp = mpService.getWxMp(appId);
        String url = WechatApi.GET_ACCESS_TOKEN.getUrl();
        String temp = url.replace("APPID", mp.getWebAppId())
                .replace("SECRET", mp.getWebAppSecret()).replace("CODE", code);
        JSONObject jsonObject = HttpClientUtils.httpGet(temp);
        if(jsonObject == null){
            log.error("Wechat Get Oauth AccessToken Fail");
            return null;
        }
        return jsonObject;
    }

    /**
     * 创建公众号菜单
     * @param menuData
     * @param appId
     * @return
     */
    @Override
    public JSONObject createMenu(String menuData, String appId) {
        String accessToken = mpService.getAccessToken(appId);
        String createMenuUrl = WechatApi.CREATE_MENU.getUrl().replace("ACCESS_TOKEN", accessToken);
        return HttpClientUtils.httpPostJson(createMenuUrl, menuData);
    }

    /**
     * 刷新accessToken
     * @param mp
     * @return
     */
    @Override
    public boolean refreshAccessToken(WxMp mp) {
        String appId = mp.getAppId();
        String appSecret = mp.getAppSecret();
        String accessTokenUrl = WechatApi.TOKEN.getUrl().replace("APPID", appId).replace("APPSECRET", appSecret);
        JSONObject accessToken = HttpClientUtils.httpGet(accessTokenUrl);
        if(accessToken == null){
            return false;
        }
        String newAccessToken = accessToken.getString("access_token");
        Long newExpiresIn = Long.valueOf(accessToken.getInteger("expires_in"));
        if (newAccessToken != null && newExpiresIn != null) {
            long currentTimestamp = DateUtils.getUnixStamp();
            mp.setAccessToken(newAccessToken);
            mp.setExpiresIn(currentTimestamp + newExpiresIn);
            mpService.updateMp(mp);
            return true;
        } else {
            String errcode = accessToken.getString("errcode");
            String errmsg = accessToken.getString("errmsg");
            log.error("Get AccessToken Fail: Errcode:" + errcode + ", Errmsg:" + errmsg);
            return false;
        }
    }

    /**
     * 刷新jsApiTicket
     * @param mp
     * @return
     */
    @Override
    public boolean refreshJsApiTicket(WxMp mp) {
        String appId = mp.getAppId();
        String accessTokenUrl = WechatApi.GET_TICKET.getUrl() + "jsapi";
        String accessToken = mpService.getAccessToken(appId);
        JSONObject jsApiTicket = HttpClientUtils.httpGet(accessTokenUrl.replace("ACCESS_TOKEN", accessToken));
        if(jsApiTicket == null){
            return false;
        }
        String newTicket = jsApiTicket.getString("ticket");
        Long newExpiresIn = Long.valueOf(jsApiTicket.getInteger("expires_in")*1000);
        if (newTicket != null && newExpiresIn != null) {
            long currentTimestamp = DateUtils.getUnixStamp();
            mp.setJsapiTicket(newTicket);
            mp.setJsapiTicketExpiresIn(currentTimestamp + newExpiresIn);
            mpService.updateMp(mp);
        } else {
            String errcode = jsApiTicket.getString("errcode");
            String errmsg = jsApiTicket.getString("errmsg");
            log.error("Get AccessToken Fail: Errcode:" + errcode + ", Errmsg:" + errmsg);
        }
        return false;
    }

    /**
     * 刷新卡券apiTicket
     * @param mp
     * @return
     */
    @Override
    public boolean refreshApiTicket(WxMp mp) {
        String appId = mp.getAppId();
        String url = WechatApi.GET_TICKET.getUrl() + "wx_card";
        String accessToken = mpService.getAccessToken(appId);
        JSONObject jsonObject = HttpClientUtils.httpGet(url.replace("ACCESS_TOKEN", accessToken));
        if(jsonObject == null){
            return false;
        }
        String ticket = jsonObject.getString("ticket");
        Long expiresIn = Long.valueOf(jsonObject.getInteger("expires_in")*1000);
        if (ticket != null && expiresIn != null) {
            long currentTimestamp = DateUtils.getUnixStamp();
            mp.setApiTicket(ticket);
            mp.setApiTicketExpiresIn(currentTimestamp + expiresIn);
            mpService.updateMp(mp);
        } else {
            String errcode = jsonObject.getString("errcode");
            String errmsg = jsonObject.getString("errmsg");
            log.error("Get apiTicket Fail: Errcode:" + errcode + ", Errmsg:" + errmsg);
        }
        return false;
    }
}
