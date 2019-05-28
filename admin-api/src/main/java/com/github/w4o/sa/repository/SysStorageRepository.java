package com.github.w4o.sa.repository;

import com.github.w4o.sa.domain.SysStorage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author frank
 * @date 2019-05-27
 */
@Repository
public interface SysStorageRepository extends CrudRepository<SysStorage, Integer> {

    /**
     * 根据Key获取
     *
     * @param key key
     * @return storage实体
     */
    SysStorage findByKey(String key);
}
