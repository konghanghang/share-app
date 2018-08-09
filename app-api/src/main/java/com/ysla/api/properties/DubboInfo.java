package com.ysla.api.properties;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * dubbo属性文件
 * @author konghang
 */
@Component
@ConfigurationProperties(prefix = "dubbo")
@Data
public class DubboInfo {

    private String packageName;

    private ApplicationConfig application;

    private ProtocolConfig protocol;

    private RegistryConfig registry;

}
