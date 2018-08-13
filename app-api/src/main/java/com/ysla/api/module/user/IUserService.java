package com.ysla.api.module.user;

import com.ysla.api.auto.model.User;
import com.ysla.api.model.exception.TxException;

/**
 * 用户api接口
 * @author konghang
 */
public interface IUserService {

    /**
     * 查询用户
     * @param user
     * @return
     */
    User selectUserBy(User user);

    /**
     * 注册用户
     * @param user
     * @return
     * @throws TxException
     */
    Integer saveUser(User user) throws TxException;

}
