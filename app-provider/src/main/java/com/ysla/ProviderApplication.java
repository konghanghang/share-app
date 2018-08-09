package com.ysla;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

/**
 * 注：为了避免扫描路径不一致，请将启动类放在RootPackage 即 com.ysla.provider
 * @author konghang
 */
@SpringBootApplication
@DubboComponentScan(basePackages="com.ysla.provider")
public class ProviderApplication implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(ProviderApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class,args);
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run(String... arg0) throws Exception {
        logger.info("### Spring boot Dubbo provider start ok!");
    }

}
