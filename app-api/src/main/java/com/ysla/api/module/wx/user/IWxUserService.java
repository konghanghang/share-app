package com.ysla.api.module.wx.user;

import com.ysla.api.auto.model.WxUser;

import java.util.List;

/**
 * 微信用户api
 * @author konghang
 */
public interface IWxUserService {

    /**
     * 初始化微信用户信息保存到数据库
     * @param appId
     * @return
     */
    List<String> initUserFromWx(String appId);

    /**
     * 获取微信用户信息
     * @param openId
     * @param appId
     * @return
     */
    WxUser getUserInfo(String openId, String appId);

    /**
     * 保存微信用户信息
     * @param wechatUser
     * @return
     */
    int saveUser(WxUser wechatUser);

}
