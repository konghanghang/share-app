package com.ysla.api.model.wx.resp;

import lombok.Data;

/**
 * 微信响应消息基础信息
 * @author konghang
 */
@Data
public class BaseMessage {

    private String ToUserName;

    private String FromUserName;

    private long CreateTime;

    private String MsgType;

}
