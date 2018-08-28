package com.ysla.api.auto.mapper;

import com.ysla.api.auto.model.Menses;
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

public interface MensesMapper {
    @Delete({
        "delete from m_menses",
        "where menses_id = #{mensesId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer mensesId);

    @Insert({
        "insert into m_menses (`year`, `month`, ",
        "open_id, last_menses_time, ",
        "menses_time, true_menses_time, ",
        "create_date)",
        "values (#{year,jdbcType=VARCHAR}, #{month,jdbcType=VARCHAR}, ",
        "#{openId,jdbcType=VARCHAR}, #{lastMensesTime,jdbcType=BIGINT}, ",
        "#{mensesTime,jdbcType=BIGINT}, #{trueMensesTime,jdbcType=BIGINT}, ",
        "#{createDate,jdbcType=BIGINT})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="mensesId", before=false, resultType=Integer.class)
    int insert(Menses record);

    @InsertProvider(type=MensesSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="mensesId", before=false, resultType=Integer.class)
    int insertSelective(Menses record);

    @Select({
        "select",
        "menses_id, `year`, `month`, open_id, last_menses_time, menses_time, true_menses_time, ",
        "create_date",
        "from m_menses",
        "where menses_id = #{mensesId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="menses_id", property="mensesId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="year", property="year", jdbcType=JdbcType.VARCHAR),
        @Result(column="month", property="month", jdbcType=JdbcType.VARCHAR),
        @Result(column="open_id", property="openId", jdbcType=JdbcType.VARCHAR),
        @Result(column="last_menses_time", property="lastMensesTime", jdbcType=JdbcType.BIGINT),
        @Result(column="menses_time", property="mensesTime", jdbcType=JdbcType.BIGINT),
        @Result(column="true_menses_time", property="trueMensesTime", jdbcType=JdbcType.BIGINT),
        @Result(column="create_date", property="createDate", jdbcType=JdbcType.BIGINT)
    })
    Menses selectByPrimaryKey(Integer mensesId);

    @UpdateProvider(type=MensesSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Menses record);

    @Update({
        "update m_menses",
        "set `year` = #{year,jdbcType=VARCHAR},",
          "`month` = #{month,jdbcType=VARCHAR},",
          "open_id = #{openId,jdbcType=VARCHAR},",
          "last_menses_time = #{lastMensesTime,jdbcType=BIGINT},",
          "menses_time = #{mensesTime,jdbcType=BIGINT},",
          "true_menses_time = #{trueMensesTime,jdbcType=BIGINT},",
          "create_date = #{createDate,jdbcType=BIGINT}",
        "where menses_id = #{mensesId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Menses record);
}