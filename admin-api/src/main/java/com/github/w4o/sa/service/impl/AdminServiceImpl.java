package com.github.w4o.sa.service.impl;

import com.github.w4o.sa.component.JwtTokenUtil;
import com.github.w4o.sa.config.Properties;
import com.github.w4o.sa.dto.LoginParam;
import com.github.w4o.sa.dto.LoginResult;
import com.github.w4o.sa.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author frank
 * @date 2019-05-14
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private Properties properties;

    @Override
    public LoginResult login(LoginParam loginParam) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginParam.getUsername());
        if (!passwordEncoder.matches(loginParam.getPassword(), userDetails.getPassword())) {
            throw new BadCredentialsException("密码不正确");
        }
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenUtil.generateToken(userDetails);
        // TODO 之后增加登陆时间和登陆日志
        return new LoginResult(token, properties.getJwt().getToken().getHead());

    }
}
