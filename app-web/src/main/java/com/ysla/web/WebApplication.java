package com.ysla.web;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 注：为了避免扫描路径不一致，请将启动类放在RootPackage 即 com.ysla.web
 * @author konghang
 */
@Slf4j
@DubboComponentScan(basePackages={
        "com.ysla.web.controller",
        "com.ysla.web.task",
        "com.ysla.web.annotation"
})
@SpringBootApplication
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class,args);
        log.info("### Spring boot webApplication starter ...");
    }

}
