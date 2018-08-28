package com.ysla.provider;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * 注：为了避免扫描路径不一致，请将启动类放在RootPackage 即 com.ysla.provider
 * @author konghang
 */
@Slf4j
@SpringBootApplication
@MapperScan(value = {"com.ysla.provider.module"})
@DubboComponentScan(basePackages="com.ysla.provider.module")
public class ProviderApplication{

    public static void main(String[] args) {
        new SpringApplicationBuilder(ProviderApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
        log.info("### Spring boot Dubbo provider start ok!");
    }
}
