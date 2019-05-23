package com.github.w4o.sa.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author frank
 * @date 2019-05-15
 */
@Data
public class AuthInfoResult implements Serializable {
    private static final long serialVersionUID = 3881879290777080791L;
    private String username;
    private String[] perms;
    private String avatar;
    private String[] roles;
}
