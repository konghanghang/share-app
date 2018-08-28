package com.ysla.api.auto.mapper;

import com.ysla.api.auto.model.Menses;
import org.apache.ibatis.jdbc.SQL;

public class MensesSqlProvider {

    public String insertSelective(Menses record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("m_menses");
        
        if (record.getYear() != null) {
            sql.VALUES("`year`", "#{year,jdbcType=VARCHAR}");
        }
        
        if (record.getMonth() != null) {
            sql.VALUES("`month`", "#{month,jdbcType=VARCHAR}");
        }
        
        if (record.getOpenId() != null) {
            sql.VALUES("open_id", "#{openId,jdbcType=VARCHAR}");
        }
        
        if (record.getLastMensesTime() != null) {
            sql.VALUES("last_menses_time", "#{lastMensesTime,jdbcType=BIGINT}");
        }
        
        if (record.getMensesTime() != null) {
            sql.VALUES("menses_time", "#{mensesTime,jdbcType=BIGINT}");
        }
        
        if (record.getTrueMensesTime() != null) {
            sql.VALUES("true_menses_time", "#{trueMensesTime,jdbcType=BIGINT}");
        }
        
        if (record.getCreateDate() != null) {
            sql.VALUES("create_date", "#{createDate,jdbcType=BIGINT}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Menses record) {
        SQL sql = new SQL();
        sql.UPDATE("m_menses");
        
        if (record.getYear() != null) {
            sql.SET("`year` = #{year,jdbcType=VARCHAR}");
        }
        
        if (record.getMonth() != null) {
            sql.SET("`month` = #{month,jdbcType=VARCHAR}");
        }
        
        if (record.getOpenId() != null) {
            sql.SET("open_id = #{openId,jdbcType=VARCHAR}");
        }
        
        if (record.getLastMensesTime() != null) {
            sql.SET("last_menses_time = #{lastMensesTime,jdbcType=BIGINT}");
        }
        
        if (record.getMensesTime() != null) {
            sql.SET("menses_time = #{mensesTime,jdbcType=BIGINT}");
        }
        
        if (record.getTrueMensesTime() != null) {
            sql.SET("true_menses_time = #{trueMensesTime,jdbcType=BIGINT}");
        }
        
        if (record.getCreateDate() != null) {
            sql.SET("create_date = #{createDate,jdbcType=BIGINT}");
        }
        
        sql.WHERE("menses_id = #{mensesId,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}