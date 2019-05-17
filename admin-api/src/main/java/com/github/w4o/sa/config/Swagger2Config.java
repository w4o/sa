package com.github.w4o.sa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author frank
 * @date 2019-05-17
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    private Properties properties;

    @Autowired
    public Swagger2Config(Properties properties) {
        this.properties = properties;
    }

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                // 是否开启
                .enable(properties.getSwagger2().getEnabled()).select()
                // 扫描的路径包
                .apis(RequestHandlerSelectors.basePackage("com.github.w4o.sa.controller"))
                // 指定路径处理PathSelectors.any()代表所有的路径
                .paths(PathSelectors.any()).build().pathMapping("/");
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("sa后台管理脚手架")
                .description("一个后台管理脚手架，后端模块接口文档")
                .version(properties.getVersion())
                .build();
    }
}
