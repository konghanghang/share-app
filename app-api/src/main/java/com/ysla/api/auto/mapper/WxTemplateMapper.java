package com.ysla.api.auto.mapper;

import com.ysla.api.auto.model.WxTemplate;
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

public interface WxTemplateMapper {
    @Delete({
        "delete from m_wx_template",
        "where template_id = #{templateId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer templateId);

    @Insert({
        "insert into m_wx_template (wx_template_id, `first`, ",
        "key_note, remark, ",
        "create_date)",
        "values (#{wxTemplateId,jdbcType=VARCHAR}, #{first,jdbcType=VARCHAR}, ",
        "#{keyNote,jdbcType=VARCHAR}, #{remark,jdbcType=VARCHAR}, ",
        "#{createDate,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="templateId", before=false, resultType=Integer.class)
    int insert(WxTemplate record);

    @InsertProvider(type=WxTemplateSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="templateId", before=false, resultType=Integer.class)
    int insertSelective(WxTemplate record);

    @Select({
        "select",
        "template_id, wx_template_id, `first`, key_note, remark, create_date",
        "from m_wx_template",
        "where template_id = #{templateId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="template_id", property="templateId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="wx_template_id", property="wxTemplateId", jdbcType=JdbcType.VARCHAR),
        @Result(column="first", property="first", jdbcType=JdbcType.VARCHAR),
        @Result(column="key_note", property="keyNote", jdbcType=JdbcType.VARCHAR),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_date", property="createDate", jdbcType=JdbcType.INTEGER)
    })
    WxTemplate selectByPrimaryKey(Integer templateId);

    @UpdateProvider(type=WxTemplateSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(WxTemplate record);

    @Update({
        "update m_wx_template",
        "set wx_template_id = #{wxTemplateId,jdbcType=VARCHAR},",
          "`first` = #{first,jdbcType=VARCHAR},",
          "key_note = #{keyNote,jdbcType=VARCHAR},",
          "remark = #{remark,jdbcType=VARCHAR},",
          "create_date = #{createDate,jdbcType=INTEGER}",
        "where template_id = #{templateId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(WxTemplate record);
}