package com.ysla.api.auto.mapper;

import com.ysla.api.auto.model.NoticeTemplate;
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

public interface NoticeTemplateMapper {
    @Delete({
        "delete from m_notice_template",
        "where notice_id = #{noticeId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer noticeId);

    @Insert({
        "insert into m_notice_template (notice, create_date)",
        "values (#{notice,jdbcType=VARCHAR}, #{createDate,jdbcType=BIGINT})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="noticeId", before=false, resultType=Integer.class)
    int insert(NoticeTemplate record);

    @InsertProvider(type=NoticeTemplateSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="noticeId", before=false, resultType=Integer.class)
    int insertSelective(NoticeTemplate record);

    @Select({
        "select",
        "notice_id, notice, create_date",
        "from m_notice_template",
        "where notice_id = #{noticeId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="notice_id", property="noticeId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="notice", property="notice", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_date", property="createDate", jdbcType=JdbcType.BIGINT)
    })
    NoticeTemplate selectByPrimaryKey(Integer noticeId);

    @UpdateProvider(type=NoticeTemplateSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(NoticeTemplate record);

    @Update({
        "update m_notice_template",
        "set notice = #{notice,jdbcType=VARCHAR},",
          "create_date = #{createDate,jdbcType=BIGINT}",
        "where notice_id = #{noticeId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(NoticeTemplate record);
}