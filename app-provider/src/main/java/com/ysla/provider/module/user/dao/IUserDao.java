package com.ysla.provider.module.user.dao;

import com.ysla.api.auto.mapper.UserMapper;
import com.ysla.api.auto.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

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

}
