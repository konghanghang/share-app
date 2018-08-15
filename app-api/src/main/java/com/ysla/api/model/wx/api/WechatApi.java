package com.ysla.api.model.wx.api;

import lombok.Getter;
import lombok.Setter;

/**
 * 微信api地址
 * @author konghang
 */
public enum WechatApi {

    M_USER_GET("https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN"),
    M_USER_INFO("https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN"),
    CREATE_MENU("https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN"),
    GET_TICKET("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type="),
    SEND_TM("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN"),
    TOKEN("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET"),
    USER_INFO("https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN"),
    GET_ACCESS_TOKEN("https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code"),
    AUTHORIZE_URL("https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect"),
    QR_CODE_TICKET("https://api.weixin.qq.com/card/qrcode/create?access_token=TOKEN");

    @Setter @Getter private String url;

    WechatApi(String url){
        this.url = url;
    }

}
