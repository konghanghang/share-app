package com.ysla.api.model.wx.access;

import lombok.Data;

/**
 * 接入微信校验vo
 * @author konghang
 */
@Data
public class CheckVo {

    private String signature;

    private String timestamp;

    private String nonce;

    private String echostr;
}
