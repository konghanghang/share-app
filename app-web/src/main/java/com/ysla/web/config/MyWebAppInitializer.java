package com.ysla.web.config;

import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * 自定义Initializer
 * https://blog.csdn.net/pomer_huang/article/details/79083728
 * @author konghang
 */
@Configuration
public class MyWebAppInitializer implements ServletContextInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        System.err.println("--------------");
        WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
    }
}
