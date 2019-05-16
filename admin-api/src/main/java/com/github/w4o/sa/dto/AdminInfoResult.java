package com.github.w4o.sa.dto;

import lombok.Data;

/**
 * @author frank
 * @date 2019-05-15
 */
@Data
public class AdminInfoResult {
    private String username;
    private String[] perms;
    private String avatar;
}
