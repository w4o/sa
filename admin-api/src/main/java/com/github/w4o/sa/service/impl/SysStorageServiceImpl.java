package com.github.w4o.sa.service.impl;

import com.github.w4o.sa.domain.SysStorage;
import com.github.w4o.sa.repository.SysStorageRepository;
import com.github.w4o.sa.service.SysStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author frank
 * @date 2019-05-27
 */
@Service
public class SysStorageServiceImpl implements SysStorageService {

    private final SysStorageRepository storageRepository;

    @Autowired
    public SysStorageServiceImpl(SysStorageRepository storageRepository) {
        this.storageRepository = storageRepository;
    }

    @Override
    public SysStorage add(SysStorage sysStorage) {
        return storageRepository.save(sysStorage);
    }

    @Override
    public SysStorage findByKey(String key) {
        return storageRepository.findByKey(key);
    }
}
