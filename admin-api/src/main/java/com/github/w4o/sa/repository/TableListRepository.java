package com.github.w4o.sa.repository;

import com.github.w4o.sa.domain.TableList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author frank
 * @date 2019-05-15
 */
public interface TableListRepository extends PagingAndSortingRepository<TableList, Integer> {

    /**
     * 分页查询，搜索条件：title
     *
     * @param title    搜索条件：Like查询
     * @param pageable pageable
     * @return 分页列表
     */
    Page<TableList> findTableListsByTitleLike(String title, Pageable pageable);
}
