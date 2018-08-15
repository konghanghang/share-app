package com.ysla.api.auto.mapper;

import com.ysla.api.auto.model.UserCollect;
import org.apache.ibatis.jdbc.SQL;

public class UserCollectSqlProvider {

    public String insertSelective(UserCollect record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("t_user_collect");
        
        if (record.getRefCollectId() != null) {
            sql.VALUES("ref_collect_id", "#{refCollectId,jdbcType=VARCHAR}");
        }
        
        if (record.getUserId() != null) {
            sql.VALUES("user_id", "#{userId,jdbcType=VARCHAR}");
        }
        
        if (record.getLinkId() != null) {
            sql.VALUES("link_id", "#{linkId,jdbcType=VARCHAR}");
        }
        
        if (record.getStatus() != null) {
            sql.VALUES("`status`", "#{status,jdbcType=TINYINT}");
        }
        
        if (record.getType() != null) {
            sql.VALUES("`type`", "#{type,jdbcType=TINYINT}");
        }
        
        if (record.getCreateDate() != null) {
            sql.VALUES("create_date", "#{createDate,jdbcType=BIGINT}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(UserCollect record) {
        SQL sql = new SQL();
        sql.UPDATE("t_user_collect");
        
        if (record.getRefCollectId() != null) {
            sql.SET("ref_collect_id = #{refCollectId,jdbcType=VARCHAR}");
        }
        
        if (record.getUserId() != null) {
            sql.SET("user_id = #{userId,jdbcType=VARCHAR}");
        }
        
        if (record.getLinkId() != null) {
            sql.SET("link_id = #{linkId,jdbcType=VARCHAR}");
        }
        
        if (record.getStatus() != null) {
            sql.SET("`status` = #{status,jdbcType=TINYINT}");
        }
        
        if (record.getType() != null) {
            sql.SET("`type` = #{type,jdbcType=TINYINT}");
        }
        
        if (record.getCreateDate() != null) {
            sql.SET("create_date = #{createDate,jdbcType=BIGINT}");
        }
        
        sql.WHERE("collect_id = #{collectId,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}