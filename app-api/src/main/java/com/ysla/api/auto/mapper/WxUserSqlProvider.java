package com.ysla.api.auto.mapper;

import com.ysla.api.auto.model.WxUser;
import org.apache.ibatis.jdbc.SQL;

public class WxUserSqlProvider {

    public String insertSelective(WxUser record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("wx_user");
        
        if (record.getAppId() != null) {
            sql.VALUES("app_id", "#{appId,jdbcType=VARCHAR}");
        }
        
        if (record.getOpenId() != null) {
            sql.VALUES("open_id", "#{openId,jdbcType=VARCHAR}");
        }
        
        if (record.getUnionId() != null) {
            sql.VALUES("union_id", "#{unionId,jdbcType=VARCHAR}");
        }
        
        if (record.getNickname() != null) {
            sql.VALUES("nickname", "#{nickname,jdbcType=VARCHAR}");
        }
        
        if (record.getSex() != null) {
            sql.VALUES("sex", "#{sex,jdbcType=TINYINT}");
        }
        
        if (record.getProvince() != null) {
            sql.VALUES("province", "#{province,jdbcType=VARCHAR}");
        }
        
        if (record.getCity() != null) {
            sql.VALUES("city", "#{city,jdbcType=VARCHAR}");
        }
        
        if (record.getCountry() != null) {
            sql.VALUES("country", "#{country,jdbcType=VARCHAR}");
        }
        
        if (record.getLanguage() != null) {
            sql.VALUES("`language`", "#{language,jdbcType=VARCHAR}");
        }
        
        if (record.getHeadImgUrl() != null) {
            sql.VALUES("head_img_url", "#{headImgUrl,jdbcType=VARCHAR}");
        }
        
        if (record.getSubscribe() != null) {
            sql.VALUES("subscribe", "#{subscribe,jdbcType=TINYINT}");
        }
        
        if (record.getSubscribeTime() != null) {
            sql.VALUES("subscribe_time", "#{subscribeTime,jdbcType=BIGINT}");
        }
        
        if (record.getCreateDate() != null) {
            sql.VALUES("create_date", "#{createDate,jdbcType=BIGINT}");
        }
        
        if (record.getLastUpdateDate() != null) {
            sql.VALUES("last_update_date", "#{lastUpdateDate,jdbcType=BIGINT}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(WxUser record) {
        SQL sql = new SQL();
        sql.UPDATE("wx_user");
        
        if (record.getAppId() != null) {
            sql.SET("app_id = #{appId,jdbcType=VARCHAR}");
        }
        
        if (record.getOpenId() != null) {
            sql.SET("open_id = #{openId,jdbcType=VARCHAR}");
        }
        
        if (record.getUnionId() != null) {
            sql.SET("union_id = #{unionId,jdbcType=VARCHAR}");
        }
        
        if (record.getNickname() != null) {
            sql.SET("nickname = #{nickname,jdbcType=VARCHAR}");
        }
        
        if (record.getSex() != null) {
            sql.SET("sex = #{sex,jdbcType=TINYINT}");
        }
        
        if (record.getProvince() != null) {
            sql.SET("province = #{province,jdbcType=VARCHAR}");
        }
        
        if (record.getCity() != null) {
            sql.SET("city = #{city,jdbcType=VARCHAR}");
        }
        
        if (record.getCountry() != null) {
            sql.SET("country = #{country,jdbcType=VARCHAR}");
        }
        
        if (record.getLanguage() != null) {
            sql.SET("`language` = #{language,jdbcType=VARCHAR}");
        }
        
        if (record.getHeadImgUrl() != null) {
            sql.SET("head_img_url = #{headImgUrl,jdbcType=VARCHAR}");
        }
        
        if (record.getSubscribe() != null) {
            sql.SET("subscribe = #{subscribe,jdbcType=TINYINT}");
        }
        
        if (record.getSubscribeTime() != null) {
            sql.SET("subscribe_time = #{subscribeTime,jdbcType=BIGINT}");
        }
        
        if (record.getCreateDate() != null) {
            sql.SET("create_date = #{createDate,jdbcType=BIGINT}");
        }
        
        if (record.getLastUpdateDate() != null) {
            sql.SET("last_update_date = #{lastUpdateDate,jdbcType=BIGINT}");
        }
        
        sql.WHERE("user_id = #{userId,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}