package com.ysla.api.model.wx.resp;

import lombok.Data;

/**
 * 图灵机器人返回的新闻对象
 * @author konghang
 */
@Data
public class RobotArticle {

    private String article;

    private String source;

    private String icon;

    private String detailurl;

}