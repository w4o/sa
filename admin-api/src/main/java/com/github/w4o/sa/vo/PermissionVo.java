package com.github.w4o.sa.vo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author frank
 * @date 2019-05-21
 */
@Data
@Builder
public class PermissionVo {
    private Integer id;
    private String label;
    private String permission;
    private Integer type;
    private Integer parentId;
    private List<PermissionVo> children;
}
