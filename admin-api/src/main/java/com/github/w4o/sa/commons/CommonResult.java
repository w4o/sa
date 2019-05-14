package com.github.w4o.sa.commons;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @author frank
 * @date 2019-05-14
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonResult {

    private Integer code;
    private String message;
    private Object data;

    public CommonResult ok(Object data) {
        this.code = 0;
        this.data = data;
        return this;
    }

    public CommonResult failed(Integer code, String message) {
        this.code = code;
        this.message = message;
        return this;
    }

    public final static Integer PASS_ERR = 1;

}
