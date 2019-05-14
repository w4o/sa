package com.github.w4o.sa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author frank
 * @date 2019-05-14
 */
@Data
@AllArgsConstructor
public class LoginResult {
    private String token;
    private String tokenHead;
}
