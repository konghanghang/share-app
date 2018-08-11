package com.ysla.provider.module.user.dao;

import com.alibaba.fastjson.JSONObject;
import com.ysla.api.auto.mapper.UserMapper;
import com.ysla.api.auto.model.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

/**
 * mapper扩展类
 * @author konghang
 */
@Mapper
public interface IUserDao extends UserMapper {

    /**
     * 查询用户
     * @param user
     * @return
     */
    @SelectProvider(type= UserDaoSqlProvider.class, method="selectUserBy")
    User selectUserBy(User user);

    /**
     * 查询用户信息
     * @param userId
     * @return
     */
    @Results(id = "userInfo",value = {
            @Result(column="ref_user_id", property="userId", jdbcType=JdbcType.VARCHAR),
            @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
            @Result(column="nick_name", property="nickName", jdbcType=JdbcType.VARCHAR),
            @Result(column="sex", property="sex", jdbcType=JdbcType.TINYINT),
            @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
    })
    @Select({"select ref_user_id, username, nick_name from t_user where ref_user_id = #{userId}"})
    JSONObject simpleInfo(String userId);

    /**
     * 测试resultMap查询用户信息
     * @param userId
     * @return
     */
    @ResultMap(value = "userInfo")
    @Select({"select ref_user_id, username, nick_name,sex,email from t_user where ref_user_id = #{userId}"})
    JSONObject simpleInfo2(String userId);

}
