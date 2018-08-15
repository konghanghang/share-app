package com.ysla.api.auto.mapper;

import com.ysla.api.auto.model.UserCollect;
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

public interface UserCollectMapper {
    @Delete({
        "delete from t_user_collect",
        "where collect_id = #{collectId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer collectId);

    @Insert({
        "insert into t_user_collect (ref_collect_id, user_id, ",
        "link_id, `status`, ",
        "`type`, create_date)",
        "values (#{refCollectId,jdbcType=VARCHAR}, #{userId,jdbcType=VARCHAR}, ",
        "#{linkId,jdbcType=VARCHAR}, #{status,jdbcType=TINYINT}, ",
        "#{type,jdbcType=TINYINT}, #{createDate,jdbcType=BIGINT})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="collectId", before=false, resultType=Integer.class)
    int insert(UserCollect record);

    @InsertProvider(type=UserCollectSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="collectId", before=false, resultType=Integer.class)
    int insertSelective(UserCollect record);

    @Select({
        "select",
        "collect_id, ref_collect_id, user_id, link_id, `status`, `type`, create_date",
        "from t_user_collect",
        "where collect_id = #{collectId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="collect_id", property="collectId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="ref_collect_id", property="refCollectId", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.VARCHAR),
        @Result(column="link_id", property="linkId", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.TINYINT),
        @Result(column="type", property="type", jdbcType=JdbcType.TINYINT),
        @Result(column="create_date", property="createDate", jdbcType=JdbcType.BIGINT)
    })
    UserCollect selectByPrimaryKey(Integer collectId);

    @UpdateProvider(type=UserCollectSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(UserCollect record);

    @Update({
        "update t_user_collect",
        "set ref_collect_id = #{refCollectId,jdbcType=VARCHAR},",
          "user_id = #{userId,jdbcType=VARCHAR},",
          "link_id = #{linkId,jdbcType=VARCHAR},",
          "`status` = #{status,jdbcType=TINYINT},",
          "`type` = #{type,jdbcType=TINYINT},",
          "create_date = #{createDate,jdbcType=BIGINT}",
        "where collect_id = #{collectId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(UserCollect record);
}