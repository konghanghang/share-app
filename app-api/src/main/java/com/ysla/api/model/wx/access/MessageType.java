package com.ysla.api.model.wx.access;

import lombok.Getter;
import lombok.Setter;

/**
 * 微信消息类型
 * @author konghang
 */
public enum MessageType {

    MESSAGE_TEXT("text"),
    MESSAGE_IMAGE("image"),
    MESSAGE_VOICE("voice"),
    MESSAGE_VIDEO("video"),
    MESSAGE_SHORTVIDEO("shortvideo"),
    MESSAGE_LOCATION("location"),
    MESSAGE_LINK("link"),
    MESSAGE_EVENT("event"),
    MESSAGE_SUBSCRIBE("subscribe"),
    MESSAGE_UNSUBSCRIBE("unsubscribe"),
    MESSAGE_CLICK("CLICK"),
    MESSAGE_VIEW("VIEW"),
    MESSAGE_NEWS("news"),
    // 创建门店返回.审核事件推送
    POI_CHECK_NOTIFY("poi_check_notify"),
    // 卡券-------买单事件推送
    USER_PAY_FROM_PAY_CELL("user_pay_from_pay_cell"),
    CARD_PASS_CHECK("card_pass_check"),
    CARD_NOT_PASS_CHECK("card_not_pass_check"),
    USER_GET_CARD("user_get_card"),
    USER_DEL_CARD("user_del_card"),
    USER_CONSUME_CARD("user_consume_card"),
    USER_VIEW_CARD("user_view_card"),
    // 子商户审核事件
    CARD_MERCHANT_CHECK_RESULT("card_merchant_check_result");

    @Setter @Getter private String type;

    MessageType(String type){
        this.type = type;
    }
}
