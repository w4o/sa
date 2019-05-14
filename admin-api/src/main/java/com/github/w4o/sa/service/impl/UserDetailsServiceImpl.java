package com.github.w4o.sa.service.impl;

import com.github.w4o.sa.domain.Admin;
import com.github.w4o.sa.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author frank
 * @date 2019-05-14
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AdminRepository adminRepository;

    @Autowired
    public UserDetailsServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Admin admin = adminRepository.findByUsername(username);

        if (admin == null) {
            throw new UsernameNotFoundException(username);
        }
        return admin;
    }

}
