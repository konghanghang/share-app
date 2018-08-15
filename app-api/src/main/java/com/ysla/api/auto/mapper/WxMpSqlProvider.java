package com.ysla.api.auto.mapper;

import com.ysla.api.auto.model.WxMp;
import org.apache.ibatis.jdbc.SQL;

public class WxMpSqlProvider {

    public String insertSelective(WxMp record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("wx_mp");
        
        if (record.getAccount() != null) {
            sql.VALUES("account", "#{account,jdbcType=VARCHAR}");
        }
        
        if (record.getAccountName() != null) {
            sql.VALUES("account_name", "#{accountName,jdbcType=VARCHAR}");
        }
        
        if (record.getAppId() != null) {
            sql.VALUES("app_id", "#{appId,jdbcType=VARCHAR}");
        }
        
        if (record.getAppSecret() != null) {
            sql.VALUES("app_secret", "#{appSecret,jdbcType=VARCHAR}");
        }
        
        if (record.getToken() != null) {
            sql.VALUES("token", "#{token,jdbcType=VARCHAR}");
        }
        
        if (record.getAccessToken() != null) {
            sql.VALUES("access_token", "#{accessToken,jdbcType=VARCHAR}");
        }
        
        if (record.getExpiresIn() != null) {
            sql.VALUES("expires_in", "#{expiresIn,jdbcType=BIGINT}");
        }
        
        if (record.getJsapiTicket() != null) {
            sql.VALUES("jsapi_ticket", "#{jsapiTicket,jdbcType=VARCHAR}");
        }
        
        if (record.getJsapiTicketExpiresIn() != null) {
            sql.VALUES("jsapi_ticket_expires_in", "#{jsapiTicketExpiresIn,jdbcType=BIGINT}");
        }
        
        if (record.getJsOauthUrl() != null) {
            sql.VALUES("js_oauth_url", "#{jsOauthUrl,jdbcType=VARCHAR}");
        }
        
        if (record.getApiTicket() != null) {
            sql.VALUES("api_ticket", "#{apiTicket,jdbcType=VARCHAR}");
        }
        
        if (record.getApiTicketExpiresIn() != null) {
            sql.VALUES("api_ticket_expires_in", "#{apiTicketExpiresIn,jdbcType=BIGINT}");
        }
        
        if (record.getMchId() != null) {
            sql.VALUES("mch_id", "#{mchId,jdbcType=VARCHAR}");
        }
        
        if (record.getWxPaySecret() != null) {
            sql.VALUES("wx_pay_secret", "#{wxPaySecret,jdbcType=VARCHAR}");
        }
        
        if (record.getWxPayNotifyUrl() != null) {
            sql.VALUES("wx_pay_notify_url", "#{wxPayNotifyUrl,jdbcType=VARCHAR}");
        }
        
        if (record.getWxPayCertPath() != null) {
            sql.VALUES("wx_pay_cert_path", "#{wxPayCertPath,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateDate() != null) {
            sql.VALUES("create_date", "#{createDate,jdbcType=BIGINT}");
        }
        
        if (record.getCreateIp() != null) {
            sql.VALUES("create_ip", "#{createIp,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateBy() != null) {
            sql.VALUES("create_by", "#{createBy,jdbcType=VARCHAR}");
        }
        
        if (record.getLastUpdateDate() != null) {
            sql.VALUES("last_update_date", "#{lastUpdateDate,jdbcType=BIGINT}");
        }
        
        if (record.getLastUpdateBy() != null) {
            sql.VALUES("last_update_by", "#{lastUpdateBy,jdbcType=VARCHAR}");
        }
        
        if (record.getWebAppId() != null) {
            sql.VALUES("web_app_id", "#{webAppId,jdbcType=VARCHAR}");
        }
        
        if (record.getWebAppSecret() != null) {
            sql.VALUES("web_app_secret", "#{webAppSecret,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(WxMp record) {
        SQL sql = new SQL();
        sql.UPDATE("wx_mp");
        
        if (record.getAccount() != null) {
            sql.SET("account = #{account,jdbcType=VARCHAR}");
        }
        
        if (record.getAccountName() != null) {
            sql.SET("account_name = #{accountName,jdbcType=VARCHAR}");
        }
        
        if (record.getAppId() != null) {
            sql.SET("app_id = #{appId,jdbcType=VARCHAR}");
        }
        
        if (record.getAppSecret() != null) {
            sql.SET("app_secret = #{appSecret,jdbcType=VARCHAR}");
        }
        
        if (record.getToken() != null) {
            sql.SET("token = #{token,jdbcType=VARCHAR}");
        }
        
        if (record.getAccessToken() != null) {
            sql.SET("access_token = #{accessToken,jdbcType=VARCHAR}");
        }
        
        if (record.getExpiresIn() != null) {
            sql.SET("expires_in = #{expiresIn,jdbcType=BIGINT}");
        }
        
        if (record.getJsapiTicket() != null) {
            sql.SET("jsapi_ticket = #{jsapiTicket,jdbcType=VARCHAR}");
        }
        
        if (record.getJsapiTicketExpiresIn() != null) {
            sql.SET("jsapi_ticket_expires_in = #{jsapiTicketExpiresIn,jdbcType=BIGINT}");
        }
        
        if (record.getJsOauthUrl() != null) {
            sql.SET("js_oauth_url = #{jsOauthUrl,jdbcType=VARCHAR}");
        }
        
        if (record.getApiTicket() != null) {
            sql.SET("api_ticket = #{apiTicket,jdbcType=VARCHAR}");
        }
        
        if (record.getApiTicketExpiresIn() != null) {
            sql.SET("api_ticket_expires_in = #{apiTicketExpiresIn,jdbcType=BIGINT}");
        }
        
        if (record.getMchId() != null) {
            sql.SET("mch_id = #{mchId,jdbcType=VARCHAR}");
        }
        
        if (record.getWxPaySecret() != null) {
            sql.SET("wx_pay_secret = #{wxPaySecret,jdbcType=VARCHAR}");
        }
        
        if (record.getWxPayNotifyUrl() != null) {
            sql.SET("wx_pay_notify_url = #{wxPayNotifyUrl,jdbcType=VARCHAR}");
        }
        
        if (record.getWxPayCertPath() != null) {
            sql.SET("wx_pay_cert_path = #{wxPayCertPath,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateDate() != null) {
            sql.SET("create_date = #{createDate,jdbcType=BIGINT}");
        }
        
        if (record.getCreateIp() != null) {
            sql.SET("create_ip = #{createIp,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateBy() != null) {
            sql.SET("create_by = #{createBy,jdbcType=VARCHAR}");
        }
        
        if (record.getLastUpdateDate() != null) {
            sql.SET("last_update_date = #{lastUpdateDate,jdbcType=BIGINT}");
        }
        
        if (record.getLastUpdateBy() != null) {
            sql.SET("last_update_by = #{lastUpdateBy,jdbcType=VARCHAR}");
        }
        
        if (record.getWebAppId() != null) {
            sql.SET("web_app_id = #{webAppId,jdbcType=VARCHAR}");
        }
        
        if (record.getWebAppSecret() != null) {
            sql.SET("web_app_secret = #{webAppSecret,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("mp_id = #{mpId,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}