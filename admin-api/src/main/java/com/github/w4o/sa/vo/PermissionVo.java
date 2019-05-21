package com.github.w4o.sa.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author frank
 * @date 2019-05-21
 */
@Data
public class PermissionVo {
    private Integer id;
    private String label;
    private String permission;
    private List<PermissionVo> children = new ArrayList<>();
}
