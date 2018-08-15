package com.ysla.api.auto.mapper;

import com.ysla.api.auto.model.WxMp;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface WxMpMapper {
    @Delete({
        "delete from wx_mp",
        "where mp_id = #{mpId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer mpId);

    @Insert({
        "insert into wx_mp (account, account_name, ",
        "app_id, app_secret, ",
        "token, access_token, ",
        "expires_in, jsapi_ticket, ",
        "jsapi_ticket_expires_in, js_oauth_url, ",
        "api_ticket, api_ticket_expires_in, ",
        "mch_id, wx_pay_secret, ",
        "wx_pay_notify_url, wx_pay_cert_path, ",
        "create_date, create_ip, ",
        "create_by, last_update_date, ",
        "last_update_by, web_app_id, ",
        "web_app_secret)",
        "values (#{account,jdbcType=VARCHAR}, #{accountName,jdbcType=VARCHAR}, ",
        "#{appId,jdbcType=VARCHAR}, #{appSecret,jdbcType=VARCHAR}, ",
        "#{token,jdbcType=VARCHAR}, #{accessToken,jdbcType=VARCHAR}, ",
        "#{expiresIn,jdbcType=BIGINT}, #{jsapiTicket,jdbcType=VARCHAR}, ",
        "#{jsapiTicketExpiresIn,jdbcType=BIGINT}, #{jsOauthUrl,jdbcType=VARCHAR}, ",
        "#{apiTicket,jdbcType=VARCHAR}, #{apiTicketExpiresIn,jdbcType=BIGINT}, ",
        "#{mchId,jdbcType=VARCHAR}, #{wxPaySecret,jdbcType=VARCHAR}, ",
        "#{wxPayNotifyUrl,jdbcType=VARCHAR}, #{wxPayCertPath,jdbcType=VARCHAR}, ",
        "#{createDate,jdbcType=BIGINT}, #{createIp,jdbcType=VARCHAR}, ",
        "#{createBy,jdbcType=VARCHAR}, #{lastUpdateDate,jdbcType=BIGINT}, ",
        "#{lastUpdateBy,jdbcType=VARCHAR}, #{webAppId,jdbcType=VARCHAR}, ",
        "#{webAppSecret,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="mpId", before=false, resultType=Integer.class)
    int insert(WxMp record);

    @InsertProvider(type=WxMpSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="mpId", before=false, resultType=Integer.class)
    int insertSelective(WxMp record);

    @Select({
        "select",
        "mp_id, account, account_name, app_id, app_secret, token, access_token, expires_in, ",
        "jsapi_ticket, jsapi_ticket_expires_in, js_oauth_url, api_ticket, api_ticket_expires_in, ",
        "mch_id, wx_pay_secret, wx_pay_notify_url, wx_pay_cert_path, create_date, create_ip, ",
        "create_by, last_update_date, last_update_by, web_app_id, web_app_secret",
        "from wx_mp",
        "where mp_id = #{mpId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="mp_id", property="mpId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="account", property="account", jdbcType=JdbcType.VARCHAR),
        @Result(column="account_name", property="accountName", jdbcType=JdbcType.VARCHAR),
        @Result(column="app_id", property="appId", jdbcType=JdbcType.VARCHAR),
        @Result(column="app_secret", property="appSecret", jdbcType=JdbcType.VARCHAR),
        @Result(column="token", property="token", jdbcType=JdbcType.VARCHAR),
        @Result(column="access_token", property="accessToken", jdbcType=JdbcType.VARCHAR),
        @Result(column="expires_in", property="expiresIn", jdbcType=JdbcType.BIGINT),
        @Result(column="jsapi_ticket", property="jsapiTicket", jdbcType=JdbcType.VARCHAR),
        @Result(column="jsapi_ticket_expires_in", property="jsapiTicketExpiresIn", jdbcType=JdbcType.BIGINT),
        @Result(column="js_oauth_url", property="jsOauthUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="api_ticket", property="apiTicket", jdbcType=JdbcType.VARCHAR),
        @Result(column="api_ticket_expires_in", property="apiTicketExpiresIn", jdbcType=JdbcType.BIGINT),
        @Result(column="mch_id", property="mchId", jdbcType=JdbcType.VARCHAR),
        @Result(column="wx_pay_secret", property="wxPaySecret", jdbcType=JdbcType.VARCHAR),
        @Result(column="wx_pay_notify_url", property="wxPayNotifyUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="wx_pay_cert_path", property="wxPayCertPath", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_date", property="createDate", jdbcType=JdbcType.BIGINT),
        @Result(column="create_ip", property="createIp", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_by", property="createBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="last_update_date", property="lastUpdateDate", jdbcType=JdbcType.BIGINT),
        @Result(column="last_update_by", property="lastUpdateBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="web_app_id", property="webAppId", jdbcType=JdbcType.VARCHAR),
        @Result(column="web_app_secret", property="webAppSecret", jdbcType=JdbcType.VARCHAR)
    })
    WxMp selectByPrimaryKey(Integer mpId);

    @UpdateProvider(type=WxMpSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(WxMp record);

    @Update({
        "update wx_mp",
        "set account = #{account,jdbcType=VARCHAR},",
          "account_name = #{accountName,jdbcType=VARCHAR},",
          "app_id = #{appId,jdbcType=VARCHAR},",
          "app_secret = #{appSecret,jdbcType=VARCHAR},",
          "token = #{token,jdbcType=VARCHAR},",
          "access_token = #{accessToken,jdbcType=VARCHAR},",
          "expires_in = #{expiresIn,jdbcType=BIGINT},",
          "jsapi_ticket = #{jsapiTicket,jdbcType=VARCHAR},",
          "jsapi_ticket_expires_in = #{jsapiTicketExpiresIn,jdbcType=BIGINT},",
          "js_oauth_url = #{jsOauthUrl,jdbcType=VARCHAR},",
          "api_ticket = #{apiTicket,jdbcType=VARCHAR},",
          "api_ticket_expires_in = #{apiTicketExpiresIn,jdbcType=BIGINT},",
          "mch_id = #{mchId,jdbcType=VARCHAR},",
          "wx_pay_secret = #{wxPaySecret,jdbcType=VARCHAR},",
          "wx_pay_notify_url = #{wxPayNotifyUrl,jdbcType=VARCHAR},",
          "wx_pay_cert_path = #{wxPayCertPath,jdbcType=VARCHAR},",
          "create_date = #{createDate,jdbcType=BIGINT},",
          "create_ip = #{createIp,jdbcType=VARCHAR},",
          "create_by = #{createBy,jdbcType=VARCHAR},",
          "last_update_date = #{lastUpdateDate,jdbcType=BIGINT},",
          "last_update_by = #{lastUpdateBy,jdbcType=VARCHAR},",
          "web_app_id = #{webAppId,jdbcType=VARCHAR},",
          "web_app_secret = #{webAppSecret,jdbcType=VARCHAR}",
        "where mp_id = #{mpId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(WxMp record);
}