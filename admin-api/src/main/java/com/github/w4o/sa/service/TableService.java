package com.github.w4o.sa.service;

import com.github.w4o.sa.domain.TableList;
import org.springframework.data.domain.Page;

/**
 * @author frank
 * @date 2019-05-15
 */
public interface TableService {

    /**
     * 列表查询
     *
     * @param title 搜索条件：title
     * @param page  当前页码
     * @param size  分页条数
     * @return 分页列表
     */
    Page<TableList> list(String title, Integer page, Integer size);
}
