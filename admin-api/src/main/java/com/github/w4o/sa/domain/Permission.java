package com.github.w4o.sa.domain;

import com.github.w4o.sa.commons.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author frank
 * @date 2019-05-20
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Permission extends BaseEntity {
    @Column(name = "name")
    private String name;
    @Column(name = "permission")
    private String permission;
    @Column(name = "parent_id")
    private Integer parentId;
    @Column(name = "type")
    private Integer type;
}