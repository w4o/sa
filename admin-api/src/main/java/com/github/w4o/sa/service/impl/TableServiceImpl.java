package com.github.w4o.sa.service.impl;

import com.github.w4o.sa.domain.TableList;
import com.github.w4o.sa.repository.TableListRepository;
import com.github.w4o.sa.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author frank
 * @date 2019-05-15
 */
@Service
public class TableServiceImpl implements TableService {

    @Autowired
    private TableListRepository tableListRepository;

    @Override
    public Page<TableList> list(String title, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<TableList> result;
        if (!StringUtils.isEmpty(title)) {
            result = tableListRepository.findTableListsByTitleLike("%" + title + "%", pageable);
        } else {
            result = tableListRepository.findAll(pageable);
        }
        return result;
    }
}
