package com.ysla.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * 注：为了避免扫描路径不一致，请将启动类放在RootPackage 即 com.ysla.provider
 * @author konghang
 */
@SpringBootApplication
public class ProviderApplication implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(ProviderApplication.class);

    public static void main(String[] args) {
        new SpringApplicationBuilder(ProviderApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }

    @Override
    public void run(String... arg0) throws Exception {
        logger.info("### Spring boot Dubbo provider start ok!");
    }

}
