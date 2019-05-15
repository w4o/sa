package com.github.w4o.sa.domain;

import com.github.w4o.sa.commons.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author frank
 * @date 2019-05-14
 */
@Entity
@Table(name = "admin")
@Data
@EqualsAndHashCode(callSuper = false)
public class Admin extends BaseEntity implements UserDetails {

    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "avatar")
    private String avatar;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> auths = new ArrayList<>();

        return auths;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
