package com.github.w4o.sa.controller;

import com.github.w4o.sa.commons.CommonResult;
import com.github.w4o.sa.service.AdminLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author frank
 * @date 2019-05-17
 */
@RestController
@RequestMapping("/log")
@Api(tags = "系统管理-日志管理")
public class AdminLogController {

    private final AdminLogService adminLogService;

    @Autowired
    public AdminLogController(AdminLogService adminLogService) {
        this.adminLogService = adminLogService;
    }

    /**
     * 日志列表
     */
    @ApiOperation("获取日志列表")
    @GetMapping
    public CommonResult list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "5") Integer size) {
        return new CommonResult().okPage(adminLogService.list(page, size));
    }
}
