package com.ysla.web.mybatis.mapper;

import com.ysla.web.mybatis.model.User;

public interface IUserMapper {

    User findUserById(Integer id);

}
