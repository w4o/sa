package com.github.w4o.sa.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author frank
 * @date 2019-05-14
 */
@ConfigurationProperties(prefix = "sa")
@Component
@Validated
@Data
public class Properties {

    private String version;
    @NotNull
    private Jwt jwt;

    private Swagger2 swagger2;

    @Data
    public static class Jwt {
        @NotNull
        private String secret;
        @NotNull
        private Integer expire;
        @NotBlank
        private String header;
    }

    @Data
    public static class Swagger2 {
        private Boolean enabled = false;
    }
}
