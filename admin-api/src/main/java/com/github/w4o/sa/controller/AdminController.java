package com.github.w4o.sa.controller;

import com.github.w4o.sa.commons.CommonResult;
import com.github.w4o.sa.domain.Admin;
import com.github.w4o.sa.dto.CreateAdminParam;
import com.github.w4o.sa.dto.UpdateAdminParam;
import com.github.w4o.sa.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author frank
 * @date 2019-05-16
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    /**
     * 管理员列表
     */
    @GetMapping("/list")
    public CommonResult list(@RequestParam(value = "username", required = false) String username,
                             @RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "5") Integer size) {
        return new CommonResult().okPage(adminService.list(username, page, size));
    }

    /**
     * 管理员信息
     */
    @GetMapping("/read/{id}")
    public CommonResult read(@PathVariable("id") Integer id) {
        return new CommonResult().ok(adminService.info(id));
    }

    /**
     * 创建管理员
     */
    @PutMapping("/create")
    public CommonResult create(@RequestBody CreateAdminParam createAdminParam) {
        Admin admin = adminService.create(createAdminParam);
        return new CommonResult().ok(admin);
    }

    /**
     * 更新管理员
     */
    @PostMapping("/update/{id}")
    public CommonResult update(@PathVariable("id") Integer id,
                               @RequestBody UpdateAdminParam updateAdminParam) {
        Admin admin = adminService.updateById(id, updateAdminParam);
        return new CommonResult().ok(admin);
    }

    /**
     * 删除管理员
     */
    @DeleteMapping("/delete/{id}")
    public CommonResult delete(@PathVariable("id") Integer id) {
        adminService.deleteById(id);
        return new CommonResult().ok();
    }
}
