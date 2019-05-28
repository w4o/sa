package com.github.w4o.sa.config;

import com.github.w4o.sa.storage.config.StorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author frank
 * @date 2019-05-27
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final StorageProperties storageProperties;

    @Autowired
    public WebConfig(StorageProperties storageProperties) {
        this.storageProperties = storageProperties;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //映射图片保存地址
        registry.addResourceHandler("/static/**").addResourceLocations("file:" + storageProperties.getLocal().getStoragePath());
    }
}

