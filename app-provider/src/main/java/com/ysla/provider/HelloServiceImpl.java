package com.ysla.provider;


import com.alibaba.dubbo.config.annotation.Service;
import com.ysla.api.module.IHelloService;
import org.springframework.stereotype.Component;

/**
 * 测试dubbo
 * @author konghang
 */
@Component
@Service(version="1.0.0")
public class HelloServiceImpl implements IHelloService {
    @Override
    public void say(String message) {
        System.out.println("provider call success, message:" + message);
    }
}
