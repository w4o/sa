package com.github.w4o.sa.repository;

import com.github.w4o.sa.domain.Admin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    /**
     * 分页查询，搜索条件：username
     *
     * @param username 搜索条件：Like查询
     * @param pageable pageable
     * @return 分页列表
     */
    Page<Admin> findAdminsByUsernameLike(String username, Pageable pageable);
}
