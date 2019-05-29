package com.github.w4o.sa.domain;

import com.github.w4o.sa.commons.BaseDataEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

/**
 * @author frank
 * @date 2019-05-17
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Role extends BaseDataEntity {

    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "role_permission_relation",
            joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "permission_id", referencedColumnName = "id")})
    private List<Permission> permissions;

}
