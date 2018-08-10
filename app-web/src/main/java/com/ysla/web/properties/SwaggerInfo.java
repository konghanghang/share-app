package com.ysla.web.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * swagger属性文件
 * @author konghang
 */
@Component
@ConfigurationProperties(prefix = "com.ysla.swagger")
@Data
public class SwaggerInfo {

    private String basePackage;

    private String title;

    private String description;

    private String termsOfServiceUrl;

    private String contactName;

    private String contactUrl;

    private String contactMail;

    private String version;
}
