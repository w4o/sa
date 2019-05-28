package com.github.w4o.sa.domain;

import com.github.w4o.sa.commons.BaseDataEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author frank
 * @date 2019-05-27
 */
@Entity
@Table(name = "sys_storage")
@Data
@EqualsAndHashCode(callSuper = false)
public class SysStorage extends BaseDataEntity {
    @Column(name = "`key`")
    private String key;
    @Column(name = "name")
    private String name;
    @Column(name = "type")
    private String type;
    @Column(name = "size")
    private Integer size;
    @Column(name = "url")
    private String url;
}
