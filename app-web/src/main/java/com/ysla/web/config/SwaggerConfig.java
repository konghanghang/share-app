package com.ysla.web.config;

import com.ysla.web.properties.SwaggerInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.Resource;

/**
 * swagger配置
 * url: /swagger-ui.html
 * @author konghang
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Resource
    SwaggerInfo swagger;

    @Bean
    public Docket buildDocket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(buildApiInf())
                .select()
                //controller路径
                .apis(RequestHandlerSelectors.basePackage(swagger.getBasePackage()))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo buildApiInf(){
        return new ApiInfoBuilder()
                .title(swagger.getTitle())
                .description(swagger.getDescription())
                .termsOfServiceUrl(swagger.getTermsOfServiceUrl())
                .contact(new Contact(swagger.getContactName(), swagger.getContactUrl(), swagger.getContactMail()))
                .version(swagger.getVersion())
                .build();

    }

}
