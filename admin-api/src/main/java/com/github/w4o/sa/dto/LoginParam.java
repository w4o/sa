package com.github.w4o.sa.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author frank
 * @date 2019-05-14
 */
@Data
public class LoginParam {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
