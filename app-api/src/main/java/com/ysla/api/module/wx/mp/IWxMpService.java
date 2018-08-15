package com.ysla.api.module.wx.mp;

import com.ysla.api.auto.model.WxMp;

/**
 * 公众账号api
 * @author konghang
 */
public interface IWxMpService {

    /**
     * 获得指定的微信公众号
     * @param appId
     * @return
     */
    WxMp getWxMp(String appId);

    /**
     * 更新公众号信息
     * @param mp
     * @return
     */
    boolean updateMp(WxMp mp);

    /**
     * 获取公众号的基础AccessToken
     * @param appId
     * @return
     */
    String getAccessToken(String appId);

    /**
     * 获取jsApiTicket
     * @param appId
     * @return
     */
    String getJsApiTicket(String appId);

    /**
     * 获取apiTicket
     * @param appId
     * @return
     */
    String getApiTicket(String appId);

}
