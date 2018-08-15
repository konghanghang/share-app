package com.ysla.api.utils.wx;

import com.alibaba.fastjson.JSONObject;
import com.ysla.api.auto.model.WxUser;

/**
 * 复制获取到的用户信息到wxUser对象中
 * @author konghang
 */
public class WxUserUtils {

    public static WxUser copyWxUserInfo(JSONObject json){
        WxUser wxUser = new WxUser();
        wxUser.setOpenId(json.getString("openid"));
        wxUser.setNickname(json.getString("nickname"));
        wxUser.setSex(json.getByte("sex"));
        wxUser.setLanguage(json.getString("language"));
        wxUser.setProvince(json.getString("province"));
        wxUser.setCity(json.getString("city"));
        wxUser.setCountry(json.getString("country"));
        wxUser.setHeadImgUrl(json.getString("headimgurl"));
        wxUser.setUnionId(json.getString("unionid"));
        wxUser.setSubscribeTime(json.getLong("subscribe_time"));
        wxUser.setSubscribe(json.getByte("subscribe"));
        //this.tagList = (List<Integer>) json.get("tagid_list");
        return wxUser;
    }

}
