package com.github.w4o.sa.service.impl;

import com.github.w4o.sa.domain.LoginAdmin;
import com.github.w4o.sa.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * spring security 授权使用
 *
 * @author frank
 * @date 2019-05-14
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDetailsRepository userDetailsRepository;

    @Autowired
    public UserDetailsServiceImpl(UserDetailsRepository userDetailsRepository) {
        this.userDetailsRepository = userDetailsRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        LoginAdmin admin = userDetailsRepository.findByUsername(username);

        if (admin == null) {
            throw new UsernameNotFoundException(username);
        }
        return admin;
    }

}
