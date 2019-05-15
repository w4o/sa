package com.github.w4o.sa.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author frank
 * @date 2019-05-14
 */
@ConfigurationProperties(prefix = "sa")
@Validated
@Data
public class Properties {

    @NotNull
    private Jwt jwt;

    @Data
    public static class Jwt {
        @NotNull
        private String secret;
        @NotNull
        private Integer expire;
        @NotBlank
        private String header;
    }
}
