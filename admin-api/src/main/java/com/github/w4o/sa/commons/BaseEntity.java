package com.github.w4o.sa.commons;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author frank
 * @date 2019-05-14
 */
@MappedSuperclass
@Data
public abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
}
