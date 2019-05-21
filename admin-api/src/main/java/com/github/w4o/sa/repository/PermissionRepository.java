package com.github.w4o.sa.repository;

import com.github.w4o.sa.domain.Permission;
import org.springframework.data.repository.CrudRepository;

/**
 * @author frank
 * @date 2019-05-21
 */
public interface PermissionRepository extends CrudRepository<Permission, Integer> {
}
