package com.github.w4o.sa.service;


import com.github.w4o.sa.domain.SysStorage;

/**
 * @author frank
 * @date 2019-05-27
 */
public interface SysStorageService {

    /**
     * 添加storage
     *
     * @param storage storage实体
     * @return entity
     */
    SysStorage add(SysStorage storage);

    /**
     * 根据key查找
     *
     * @param key key
     * @return entity
     */
    SysStorage findByKey(String key);
}
