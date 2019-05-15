package com.github.w4o.sa.controller;

import com.github.w4o.sa.commons.CommonResult;
import com.github.w4o.sa.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author frank
 * @date 2019-05-15
 */
@RestController
@RequestMapping("/table")
public class TableController {

    @Autowired
    private TableService tableService;

    /**
     * table 列表
     */
    @GetMapping("/list")
    public CommonResult list(@RequestParam(value = "title", required = false) String title,
                             @RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "5") Integer size) {
        return new CommonResult().okPage(tableService.list(title, page, size));
    }
}
