package com.ysla.api.auto.mapper;

import com.ysla.api.auto.model.NoticeTemplate;
import org.apache.ibatis.jdbc.SQL;

public class NoticeTemplateSqlProvider {

    public String insertSelective(NoticeTemplate record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("m_notice_template");
        
        if (record.getNotice() != null) {
            sql.VALUES("notice", "#{notice,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateDate() != null) {
            sql.VALUES("create_date", "#{createDate,jdbcType=BIGINT}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(NoticeTemplate record) {
        SQL sql = new SQL();
        sql.UPDATE("m_notice_template");
        
        if (record.getNotice() != null) {
            sql.SET("notice = #{notice,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateDate() != null) {
            sql.SET("create_date = #{createDate,jdbcType=BIGINT}");
        }
        
        sql.WHERE("notice_id = #{noticeId,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}