package com.github.w4o.sa.service.impl;

import com.github.w4o.sa.component.AdminLogHelper;
import com.github.w4o.sa.component.JwtTokenUtil;
import com.github.w4o.sa.domain.Admin;
import com.github.w4o.sa.domain.Permission;
import com.github.w4o.sa.domain.Role;
import com.github.w4o.sa.dto.AuthInfoResult;
import com.github.w4o.sa.dto.LoginParam;
import com.github.w4o.sa.dto.LoginResult;
import com.github.w4o.sa.repository.AdminRepository;
import com.github.w4o.sa.repository.UserDetailsRepository;
import com.github.w4o.sa.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @author frank
 * @date 2019-05-20
 */
@Service
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailsRepository userDetailsRepository;
    private final AdminLogHelper adminLogHelper;
    private final AdminRepository adminRepository;

    private HashMap<String, String> permissionsMap = null;

    @Autowired
    public AuthServiceImpl(PasswordEncoder passwordEncoder,
                           JwtTokenUtil jwtTokenUtil,
                           UserDetailsRepository userDetailsRepository,
                           AdminLogHelper adminLogHelper,
                           AdminRepository adminRepository) {
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsRepository = userDetailsRepository;
        this.adminLogHelper = adminLogHelper;
        this.adminRepository = adminRepository;
    }

    @Override
    public LoginResult login(LoginParam loginParam) {
        UserDetails userDetails = userDetailsRepository.findByUsername(loginParam.getUsername());
        if (!passwordEncoder.matches(loginParam.getPassword(), userDetails.getPassword())) {
            throw new BadCredentialsException("密码不正确");
        }
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenUtil.generateToken(userDetails);
        adminLogHelper.loginSucceed();
        // TODO 之后增加登陆时间
        return new LoginResult(token);
    }

    @Override
    public AuthInfoResult getAdminInfo(String username) {
        Admin admin = adminRepository.findByUsername(username);
        AuthInfoResult result = new AuthInfoResult();
        result.setUsername(username);
        result.setAvatar(admin.getAvatar());
        Set<String> perms = new HashSet<>();
        Set<String> roles = new HashSet<>();
        for (Role role : admin.getRoles()) {
            roles.add(role.getName());
            for (Permission permission : role.getPermissions()) {
                perms.add(permission.getPermission());
            }
        }
        result.setRoles(roles.toArray(new String[0]));
        result.setPerms(perms.toArray(new String[0]));
        return result;
    }

}
