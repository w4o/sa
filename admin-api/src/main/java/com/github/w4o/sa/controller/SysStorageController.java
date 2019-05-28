package com.github.w4o.sa.controller;

import com.github.w4o.sa.commons.CommonResult;
import com.github.w4o.sa.domain.SysStorage;
import com.github.w4o.sa.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author frank
 * @date 2019-05-27
 */
@RestController
@RequestMapping("/storage")
@Validated
public class SysStorageController {

    private final StorageService storageService;

    @Autowired
    public SysStorageController(StorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping("/create")
    public CommonResult create(@RequestParam("file") MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        SysStorage storage = storageService.store(file.getInputStream(), file.getSize(), file.getContentType(), originalFilename);
        return new CommonResult().ok(storage);
    }
}
