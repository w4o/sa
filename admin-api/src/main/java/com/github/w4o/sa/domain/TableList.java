package com.github.w4o.sa.domain;

import com.github.w4o.sa.commons.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

/**
 * TableList
 *
 * @author frank
 * @date 2019-05-15
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class TableList extends BaseEntity {

    /**
     * 标题
     */
    @Column(name = "title")
    private String title;
    /**
     * 作者
     */
    @Column(name = "author")
    private String author;
    /**
     * 阅读数
     */
    @Column(name = "views")
    private Integer views;
    /**
     * 状态
     */
    @Column(name = "status")
    private Integer status;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

}
