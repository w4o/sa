package com.github.w4o.sa.domain;

import com.github.w4o.sa.commons.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author frank
 * @date 2019-05-14
 */
@Entity
@Table(name = "admin")
@Data
@EqualsAndHashCode(callSuper = false)
public class Admin extends BaseEntity {

    /**
     * 用户名
     */
    @Column(name = "username")
    private String username;

    /**
     * 密码
     */
    @Column(name = "password")
    private String password;

    /**
     * 头像
     */
    @Column(name = "avatar")
    private String avatar;

    /**
     * 备注信息
     */
    @Column(name = "remark")
    private String remark;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 最后修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 删除标记
     */
    @Column(name = "deleted")
    private Integer deleted;

    public Admin preInsert() {
        this.deleted = 0;
        this.createTime = new Date();
        return this;
    }
}
