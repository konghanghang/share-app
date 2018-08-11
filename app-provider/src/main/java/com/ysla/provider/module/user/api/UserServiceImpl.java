package com.ysla.provider.module.user.api;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONObject;
import com.ysla.api.auto.model.User;
import com.ysla.api.module.user.IUserService;
import com.ysla.provider.module.user.dao.IUserDao;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 用户api接口
 * @author konghang
 */
@Component
@Service(version = "${dubbo.service.version}")
public class UserServiceImpl implements IUserService {

    @Resource
    private IUserDao userDao;

    @Override
    public User selectUserBy(User user) {
        return userDao.selectUserBy(user);
    }

    @Override
    public JSONObject simpleInfo(String userId) {
        return userDao.simpleInfo(userId);
    }

    @Override
    public JSONObject simpleInfo2(String userId) {
        return userDao.simpleInfo2(userId);
    }
}
