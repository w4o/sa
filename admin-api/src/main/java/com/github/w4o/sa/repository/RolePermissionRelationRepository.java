package com.github.w4o.sa.repository;

import com.github.w4o.sa.domain.RolePermissionRelation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author frank
 * @date 2019-05-22
 */
@Repository
public interface RolePermissionRelationRepository extends CrudRepository<RolePermissionRelation, Integer> {

    /**
     * 根据角色id查询
     *
     * @param roleId 角色id
     * @return 结果
     */
    Iterable<RolePermissionRelation> findByRoleId(Integer roleId);
}
