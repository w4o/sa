package com.github.w4o.sa.service;

import com.github.w4o.sa.dto.AdminInfoResult;
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
     * @return token
     */
    LoginResult login(LoginParam loginParam);

    /**
     * 根据用户名获取管理员信息
     *
     * @param username 用户名
     * @return 管理信息
     */
    AdminInfoResult getAdminInfo(String username);
}
