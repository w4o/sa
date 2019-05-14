package com.github.w4o.sa.controller;

import com.github.w4o.sa.commons.CommonResult;
import com.github.w4o.sa.dto.LoginParam;
import com.github.w4o.sa.dto.LoginResult;
import com.github.w4o.sa.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author frank
 * @date 2019-05-14
 */
@RestController
@RequestMapping("/auth")
@Validated
public class AuthController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    public CommonResult login(@RequestBody LoginParam loginParam) {
        LoginResult loginResult = adminService.login(loginParam);
        return new CommonResult().ok(loginResult);
    }
}
