package com.github.w4o.sa.controller;

import com.github.w4o.sa.commons.CommonResult;
import com.github.w4o.sa.dto.LoginParam;
import com.github.w4o.sa.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

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
        return new CommonResult().ok(adminService.login(loginParam));
    }

    /**
     * Admin 登出
     */
    @PostMapping("/logout")
    @ResponseBody
    public CommonResult logout() {
        return new CommonResult().ok(null);
    }

    /**
     * 获取当前登录用户信息
     */
    @GetMapping("/info")
    @ResponseBody
    public CommonResult getAdminInfo(Principal principal) {
        return new CommonResult().ok(adminService.getAdminInfo(principal.getName()));
    }
}
