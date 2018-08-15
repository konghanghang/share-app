package com.ysla.api.model.wx.oauth;

import com.alibaba.fastjson.JSONObject;
import com.ysla.api.model.common.ErrorCode;
import lombok.Getter;
import lombok.Setter;


/**
 * 微信认证AccessToken
 * @author konghang
 */
public class WxAccessToken {

    @Setter @Getter private int errCode;

    @Setter @Getter private String errMsg;

    @Setter @Getter private String accessToken;

    @Setter @Getter private int expiresIn;

    @Setter @Getter private String refreshToken;

    @Setter @Getter private String openId;

    @Setter @Getter private String scope;

    public WxAccessToken(ErrorCode errorCode) {
        this.errCode = errorCode.getErrorID();
        this.errMsg = errorCode.getErrorMsg();
    }

    public WxAccessToken(JSONObject jsonObject) {
        if (jsonObject.getInteger("errcode") == null) {
            this.accessToken = (String) jsonObject.get("access_token");
            this.expiresIn = (int) jsonObject.get("expires_in");
            this.refreshToken = (String) jsonObject.get("refresh_token");
            this.openId = (String) jsonObject.get("openid");
            this.scope = (String) jsonObject.get("scope");
        } else {
            this.errCode = jsonObject.getInteger("errcode");
            this.errMsg = jsonObject.getString("errmsg");
        }

    }

}
