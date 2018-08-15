package com.ysla.api.model.wx.resp;

import lombok.Data;

/**
 * 微信响应消息-文本信息
 * @author konghang
 */
@Data
public class TextMessage extends BaseMessage{

    private String Content;

}
