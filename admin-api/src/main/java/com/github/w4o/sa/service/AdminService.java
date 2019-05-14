package com.github.w4o.sa.service;

import com.github.w4o.sa.dto.LoginParam;
import com.github.w4o.sa.dto.LoginResult;

/**
 * @author frank
 * @date 2019-05-14
 */
public interface AdminService {

    /**
     * 管理员登陆
     *
     * @param loginParam 登陆参数，包含username和password
     * @return 登陆结果，包含token和tokenHead
     */
    LoginResult login(LoginParam loginParam);
}
