package com.ysla.api.model.wx.oauth;

import lombok.Getter;
import lombok.Setter;

public enum OauthScope {

    //普通授权,只获得OpenID
    BASE("snsapi_base"),
    //用户授权,获得详细的用户信息
    USER_INFO("snsapi_userinfo");

    OauthScope(String scope) {
        this.scope = scope;
    }

    @Getter @Setter private String scope;

}
