package com.github.w4o.sa.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @author frank
 * @date 2019-05-16
 */
@Data
public class CreateAdminParam {
    @NotBlank
    @Length(min = 6, max = 20)
    private String username;
    @NotBlank
    @Length(min = 6, max = 40)
    private String password;
    @Length(max = 255)
    private String avatar;
    @Length(max = 255)
    private String remark;
}
