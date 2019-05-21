package com.github.w4o.sa.repository;

import com.github.w4o.sa.domain.Role;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author frank
 * @date 2019-05-20
 */
public interface RoleRepository extends PagingAndSortingRepository<Role, Integer>, JpaSpecificationExecutor<Role> {

    /**
     * 根据名称查找角色信息
     *
     * @param name 角色名
     * @return 角色实体
     */
    Role findByName(String name);
}
