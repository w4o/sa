package com.github.w4o.sa.controller;

import com.github.w4o.sa.commons.CommonResult;
import com.github.w4o.sa.component.AdminLogHelper;
import com.github.w4o.sa.dto.LoginParam;
import com.github.w4o.sa.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "后台授权")
public class AuthController {

    private final AuthService authService;
    private final AdminLogHelper adminLogHelper;

    @Autowired
    public AuthController(AuthService authService,
                          AdminLogHelper adminLogHelper) {
        this.authService = authService;
        this.adminLogHelper = adminLogHelper;
    }

    @ApiOperation(value = "管理员登陆")
    @PostMapping("/login")
    public CommonResult login(@RequestBody LoginParam loginParam) {
        return new CommonResult().ok(authService.login(loginParam));
    }

    /**
     * Admin 登出
     */
    @ApiOperation(value = "管理员登出")
    @PostMapping("/logout")
    public CommonResult logout() {
        adminLogHelper.logoutSucceed();
        return new CommonResult().ok(null);
    }

    /**
     * 获取当前登录用户信息
     */
    @ApiOperation(value = "获取登陆用户信息")
    @GetMapping("/info")
    @ResponseBody
    public CommonResult getAdminInfo(Principal principal) {
        return new CommonResult().ok(authService.getAdminInfo(principal.getName()));
    }
}
