package com.github.w4o.sa.service.impl;

import com.github.w4o.sa.domain.AdminLog;
import com.github.w4o.sa.repository.AdminLogRepository;
import com.github.w4o.sa.service.AdminLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * @author frank
 * @date 2019-05-17
 */
@Service
public class AdminLogServiceImpl implements AdminLogService {

    private final AdminLogRepository adminLogRepository;

    @Autowired
    public AdminLogServiceImpl(AdminLogRepository adminLogRepository) {
        this.adminLogRepository = adminLogRepository;
    }

    @Override
    public Page<AdminLog> list(Integer page, Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        return adminLogRepository.findAll(pageable);
    }
}
