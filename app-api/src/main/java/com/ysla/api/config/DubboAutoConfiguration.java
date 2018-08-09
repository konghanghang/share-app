package com.ysla.api.config;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.ysla.api.properties.DubboInfo;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * dubbo配置类
 * @EnableConfigurationProperties(DubboInfo.class): 开启属性注入,通过@autowired注入
 * @author konghang
 */
@Configuration
@ConditionalOnClass({ApplicationConfig.class, ProtocolConfig.class, RegistryConfig.class})
public class DubboAutoConfiguration {

    @Resource
    DubboInfo dubboInfo;

    /**
     * 应用相关配置
     * @ConditionalOnMissingBean(ApplicationConfig.class) //容器中如果没有这个类,那么自动配置这个类
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(ApplicationConfig.class)
    public ApplicationConfig applicationConfig() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName(dubboInfo.getApplication().getName());
        applicationConfig.setVersion(dubboInfo.getApplication().getVersion());
        applicationConfig.setOrganization(dubboInfo.getApplication().getOrganization());
        applicationConfig.setOwner(dubboInfo.getApplication().getOwner());
        return applicationConfig;
    }

    /**
     * 协议配置 基于dubbo
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(ProtocolConfig.class)
    public ProtocolConfig protocolConfig() {
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setName(dubboInfo.getProtocol().getName());
        protocolConfig.setPort(dubboInfo.getProtocol().getPort());
        return protocolConfig;
    }

    /**
     * 注册中心配置 基于zookeeper
     * @return
     */
    @Bean
    public RegistryConfig registryConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        //registryConfig.setProtocol("zookeeper");
        registryConfig.setAddress(dubboInfo.getRegistry().getAddress());
        registryConfig.setTimeout(30000);
        return registryConfig;
    }

}
