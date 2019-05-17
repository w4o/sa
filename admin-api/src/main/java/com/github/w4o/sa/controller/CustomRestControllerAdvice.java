package com.github.w4o.sa.controller;

import com.github.w4o.sa.commons.CommonResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author frank
 * @date 2019-05-17
 */
@RestControllerAdvice
public class CustomRestControllerAdvice {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public CommonResult exception(Exception e) {
        CommonResult result = new CommonResult();
        result.setCode(-1);
        result.setMessage(e.getMessage());
        return result;
    }
}
