package com.ysla.provider.module;


import com.alibaba.dubbo.config.annotation.Service;
import com.ysla.api.auto.model.User;
import com.ysla.api.module.IHelloService;
import com.ysla.provider.module.user.IUserDao;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 测试dubbo
 * @author konghang
 */
@Component
@Service(version="${dubbo.service.version}")
public class HelloServiceImpl implements IHelloService {

    @Resource
    IUserDao userDao;

    @Override
    public void say(String message) {
        User user = userDao.selectByPrimaryKey(1);
        System.out.println(user.toString());
        System.out.println("provider call success, message:" + message);
    }
}
