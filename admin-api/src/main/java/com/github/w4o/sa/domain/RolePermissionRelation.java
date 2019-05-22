package com.github.w4o.sa.domain;

import com.github.w4o.sa.commons.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author frank
 * @date 2019-05-20
 */
@Entity
@Table(name = "role_permission_relation")
@Data
@EqualsAndHashCode(callSuper = false)
public class RolePermissionRelation extends BaseEntity {

    @Column(name = "role_id")
    private Integer roleId;
    @Column(name = "permission_id")
    private Integer permissionId;

}
