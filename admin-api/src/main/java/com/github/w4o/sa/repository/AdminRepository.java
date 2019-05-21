package com.github.w4o.sa.repository;

import com.github.w4o.sa.domain.Admin;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author frank
 * @date 2019-05-14
 */
@Repository
public interface AdminRepository extends PagingAndSortingRepository<Admin, Integer>, JpaSpecificationExecutor<Admin> {

    /**
     * 根据用户名查询系统管理员
     *
     * @param username 用户名
     * @return 系统管理员
     */
    Admin findByUsername(String username);
}
