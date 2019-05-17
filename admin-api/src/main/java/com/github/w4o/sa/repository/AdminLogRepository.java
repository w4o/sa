package com.github.w4o.sa.repository;

import com.github.w4o.sa.domain.AdminLog;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author frank
 * @date 2019-05-17
 */
public interface AdminLogRepository extends PagingAndSortingRepository<AdminLog, Integer> {
}
