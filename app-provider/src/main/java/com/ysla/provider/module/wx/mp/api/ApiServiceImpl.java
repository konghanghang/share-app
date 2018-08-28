package com.ysla.provider.module.wx.mp.api;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONObject;
import com.ysla.api.auto.model.WxMp;
import com.ysla.api.auto.model.WxUser;
import com.ysla.api.model.common.ErrorCode;
import com.ysla.api.model.common.StringEnum;
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
        int len = 100;
        if (state.length() > len) {
            log.info("stateUrl too Long:" + state);
            state = state.substring(0, 100);
        }
        String stateUrl = UrlUtils.urlEncode(state);

        len = 128;
        if (stateUrl.length() >= len) {
            //微信允许的最大state长度是128位
            log.info("stateUrl too Long" + stateUrl);
            //进一步的减少
            stateUrl = UrlUtils.urlEncode(state.substring(0, 80));
        }
        return connectUrl.replace(StringEnum.APP_ID.getName(), appId)
                .replace(StringEnum.REDIRECT_URI.getName(), UrlUtils.urlEncode(redirectUrl))
                .replace(StringEnum.SCOPE.getName(), oauthScope)
                .replace(StringEnum.STATE.getName(), stateUrl);
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
                .replace(StringEnum.APP_ID.getName(), mp.getAppId())
                .replace(StringEnum.SECRET.getName(), mp.getAppSecret())
                .replace(StringEnum.CODE.getName(), code));
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
        JSONObject userInfoResult = HttpClientUtils.httpGet(userInfoUrl.replace(StringEnum.ACCESS_TOKEN.getName(), accessToken)
                .replace(StringEnum.OPENID.getName(), openId));
        if(userInfoResult == null){
            log.info("getWxUserInfo fail");
            return null;
        }
        if (userInfoResult.getInteger(StringEnum.ERR_CODE.getName()) != null) {
            log.error("getWxUserInfo fail:{}, openId:{}", userInfoResult.getString(StringEnum.ERR_MSG.getName()), openId);
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
        return HttpClientUtils.httpPostJson(url.replace(StringEnum.ACCESS_TOKEN.getName(), accessToken), ticket);
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
        String temp = url.replace(StringEnum.APP_ID.getName(), mp.getWebAppId())
                .replace(StringEnum.SECRET.getName(), mp.getWebAppSecret()).replace(StringEnum.CODE.getName(), code);
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
        String createMenuUrl = WechatApi.CREATE_MENU.getUrl().replace(StringEnum.ACCESS_TOKEN.getName(), accessToken);
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
        String accessTokenUrl = WechatApi.TOKEN.getUrl()
                .replace(StringEnum.APP_ID.getName(), appId)
                .replace(StringEnum.APP_SECRET.getName(), appSecret);
        JSONObject accessToken = HttpClientUtils.httpGet(accessTokenUrl);
        if(accessToken == null){
            return false;
        }
        String newAccessToken = accessToken.getString(StringEnum.ACCESS_TOKEN.getName());
        Long newExpiresIn = Long.valueOf(accessToken.getInteger(StringEnum.EXPIRES_IN.getName()));
        if (newAccessToken != null && newExpiresIn != null) {
            long currentTimestamp = DateUtils.getUnixStamp();
            mp.setAccessToken(newAccessToken);
            mp.setExpiresIn(currentTimestamp + newExpiresIn);
            mpService.updateMp(mp);
            return true;
        } else {
            String errcode = accessToken.getString(StringEnum.ERR_CODE.getName());
            String errmsg = accessToken.getString(StringEnum.ERR_MSG.getName());
            log.error("Get AccessToken Fail: Errcode:{}, Errmsg:{}", errcode, errmsg);
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
        JSONObject jsApiTicket = HttpClientUtils.httpGet(accessTokenUrl.replace(StringEnum.ACCESS_TOKEN.getName(), accessToken));
        if(jsApiTicket == null){
            return false;
        }
        String newTicket = jsApiTicket.getString(StringEnum.TICKET.getName());
        Long newExpiresIn = Long.valueOf(jsApiTicket.getInteger(StringEnum.EXPIRES_IN.getName())*1000);
        if (newTicket != null && newExpiresIn != null) {
            long currentTimestamp = DateUtils.getUnixStamp();
            mp.setJsapiTicket(newTicket);
            mp.setJsapiTicketExpiresIn(currentTimestamp + newExpiresIn);
            mpService.updateMp(mp);
        } else {
            String errcode = jsApiTicket.getString(StringEnum.ERR_CODE.getName());
            String errmsg = jsApiTicket.getString(StringEnum.ERR_MSG.getName());
            log.error("Get JsApiTicket Fail: Errcode:{}, Errmsg:{}", errcode, errmsg);
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
        JSONObject jsonObject = HttpClientUtils.httpGet(url.replace(StringEnum.ACCESS_TOKEN.getName(), accessToken));
        if(jsonObject == null){
            return false;
        }
        String ticket = jsonObject.getString(StringEnum.TICKET.getName());
        Long expiresIn = Long.valueOf(jsonObject.getInteger(StringEnum.EXPIRES_IN.getName())*1000);
        if (ticket != null && expiresIn != null) {
            long currentTimestamp = DateUtils.getUnixStamp();
            mp.setApiTicket(ticket);
            mp.setApiTicketExpiresIn(currentTimestamp + expiresIn);
            mpService.updateMp(mp);
        } else {
            String errcode = jsonObject.getString(StringEnum.ERR_CODE.getName());
            String errmsg = jsonObject.getString(StringEnum.ERR_MSG.getName());
            log.error("Get apiTicket Fail: Errcode:{}, Errmsg:{}", errcode, errmsg);
        }
        return false;
    }
}
