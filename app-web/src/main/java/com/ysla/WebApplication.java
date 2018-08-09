package com.ysla;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 注：为了避免扫描路径不一致，请将启动类放在RootPackage 即 com.ysla.web
 * @author konghang
 */
@SpringBootApplication
@DubboComponentScan(basePackages="com.ysla.web.controller")
public class WebApplication {

    private static final Logger logger = LoggerFactory.getLogger(WebApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class,args);
        logger.info("### Spring boot webApplication starter ...");
    }

}
