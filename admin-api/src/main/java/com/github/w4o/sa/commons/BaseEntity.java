package com.github.w4o.sa.commons;

import lombok.Data;

import javax.persistence.*;

/**
 * @author frank
 * @date 2019-05-14
 */
@MappedSuperclass
@Data
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
}
