package com.ysla.api.auto.mapper;

import com.ysla.api.auto.model.WxUser;
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

public interface WxUserMapper {
    @Delete({
        "delete from wx_user",
        "where user_id = #{userId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer userId);

    @Insert({
        "insert into wx_user (app_id, open_id, ",
        "union_id, nickname, ",
        "sex, province, city, ",
        "country, `language`, ",
        "head_img_url, subscribe, ",
        "subscribe_time, create_date, ",
        "last_update_date)",
        "values (#{appId,jdbcType=VARCHAR}, #{openId,jdbcType=VARCHAR}, ",
        "#{unionId,jdbcType=VARCHAR}, #{nickname,jdbcType=VARCHAR}, ",
        "#{sex,jdbcType=TINYINT}, #{province,jdbcType=VARCHAR}, #{city,jdbcType=VARCHAR}, ",
        "#{country,jdbcType=VARCHAR}, #{language,jdbcType=VARCHAR}, ",
        "#{headImgUrl,jdbcType=VARCHAR}, #{subscribe,jdbcType=TINYINT}, ",
        "#{subscribeTime,jdbcType=BIGINT}, #{createDate,jdbcType=BIGINT}, ",
        "#{lastUpdateDate,jdbcType=BIGINT})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="userId", before=false, resultType=Integer.class)
    int insert(WxUser record);

    @InsertProvider(type=WxUserSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="userId", before=false, resultType=Integer.class)
    int insertSelective(WxUser record);

    @Select({
        "select",
        "user_id, app_id, open_id, union_id, nickname, sex, province, city, country, ",
        "`language`, head_img_url, subscribe, subscribe_time, create_date, last_update_date",
        "from wx_user",
        "where user_id = #{userId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="app_id", property="appId", jdbcType=JdbcType.VARCHAR),
        @Result(column="open_id", property="openId", jdbcType=JdbcType.VARCHAR),
        @Result(column="union_id", property="unionId", jdbcType=JdbcType.VARCHAR),
        @Result(column="nickname", property="nickname", jdbcType=JdbcType.VARCHAR),
        @Result(column="sex", property="sex", jdbcType=JdbcType.TINYINT),
        @Result(column="province", property="province", jdbcType=JdbcType.VARCHAR),
        @Result(column="city", property="city", jdbcType=JdbcType.VARCHAR),
        @Result(column="country", property="country", jdbcType=JdbcType.VARCHAR),
        @Result(column="language", property="language", jdbcType=JdbcType.VARCHAR),
        @Result(column="head_img_url", property="headImgUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="subscribe", property="subscribe", jdbcType=JdbcType.TINYINT),
        @Result(column="subscribe_time", property="subscribeTime", jdbcType=JdbcType.BIGINT),
        @Result(column="create_date", property="createDate", jdbcType=JdbcType.BIGINT),
        @Result(column="last_update_date", property="lastUpdateDate", jdbcType=JdbcType.BIGINT)
    })
    WxUser selectByPrimaryKey(Integer userId);

    @UpdateProvider(type=WxUserSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(WxUser record);

    @Update({
        "update wx_user",
        "set app_id = #{appId,jdbcType=VARCHAR},",
          "open_id = #{openId,jdbcType=VARCHAR},",
          "union_id = #{unionId,jdbcType=VARCHAR},",
          "nickname = #{nickname,jdbcType=VARCHAR},",
          "sex = #{sex,jdbcType=TINYINT},",
          "province = #{province,jdbcType=VARCHAR},",
          "city = #{city,jdbcType=VARCHAR},",
          "country = #{country,jdbcType=VARCHAR},",
          "`language` = #{language,jdbcType=VARCHAR},",
          "head_img_url = #{headImgUrl,jdbcType=VARCHAR},",
          "subscribe = #{subscribe,jdbcType=TINYINT},",
          "subscribe_time = #{subscribeTime,jdbcType=BIGINT},",
          "create_date = #{createDate,jdbcType=BIGINT},",
          "last_update_date = #{lastUpdateDate,jdbcType=BIGINT}",
        "where user_id = #{userId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(WxUser record);
}