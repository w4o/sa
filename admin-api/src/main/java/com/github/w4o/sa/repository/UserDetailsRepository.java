package com.github.w4o.sa.repository;

import com.github.w4o.sa.domain.LoginAdmin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author frank
 * @date 2019-05-16
 */
@Repository
public interface UserDetailsRepository extends CrudRepository<LoginAdmin, Integer> {

    /**
     * 根据用户名查询系统管理员
     *
     * @param username 用户名
     * @return 系统管理员
     */
    LoginAdmin findByUsername(String username);
}
