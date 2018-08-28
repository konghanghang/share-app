package com.ysla.api.auto.mapper;

import com.ysla.api.auto.model.WxTemplate;
import org.apache.ibatis.jdbc.SQL;

public class WxTemplateSqlProvider {

    public String insertSelective(WxTemplate record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("m_wx_template");
        
        if (record.getWxTemplateId() != null) {
            sql.VALUES("wx_template_id", "#{wxTemplateId,jdbcType=VARCHAR}");
        }
        
        if (record.getFirst() != null) {
            sql.VALUES("`first`", "#{first,jdbcType=VARCHAR}");
        }
        
        if (record.getKeyNote() != null) {
            sql.VALUES("key_note", "#{keyNote,jdbcType=VARCHAR}");
        }
        
        if (record.getRemark() != null) {
            sql.VALUES("remark", "#{remark,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateDate() != null) {
            sql.VALUES("create_date", "#{createDate,jdbcType=INTEGER}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(WxTemplate record) {
        SQL sql = new SQL();
        sql.UPDATE("m_wx_template");
        
        if (record.getWxTemplateId() != null) {
            sql.SET("wx_template_id = #{wxTemplateId,jdbcType=VARCHAR}");
        }
        
        if (record.getFirst() != null) {
            sql.SET("`first` = #{first,jdbcType=VARCHAR}");
        }
        
        if (record.getKeyNote() != null) {
            sql.SET("key_note = #{keyNote,jdbcType=VARCHAR}");
        }
        
        if (record.getRemark() != null) {
            sql.SET("remark = #{remark,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateDate() != null) {
            sql.SET("create_date = #{createDate,jdbcType=INTEGER}");
        }
        
        sql.WHERE("template_id = #{templateId,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}