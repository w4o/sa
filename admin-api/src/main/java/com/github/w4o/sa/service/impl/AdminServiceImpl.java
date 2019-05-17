package com.github.w4o.sa.service.impl;

import com.github.w4o.sa.component.JwtTokenUtil;
import com.github.w4o.sa.domain.Admin;
import com.github.w4o.sa.dto.*;
import com.github.w4o.sa.repository.AdminRepository;
import com.github.w4o.sa.repository.UserDetailsRepository;
import com.github.w4o.sa.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author frank
 * @date 2019-05-14
 */
@Service
public class AdminServiceImpl implements AdminService {

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;
    private final AdminRepository adminRepository;
    private final UserDetailsRepository userDetailsRepository;

    @Autowired
    public AdminServiceImpl(PasswordEncoder passwordEncoder,
                            JwtTokenUtil jwtTokenUtil,
                            AdminRepository adminRepository,
                            UserDetailsRepository userDetailsRepository) {
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenUtil = jwtTokenUtil;
        this.adminRepository = adminRepository;
        this.userDetailsRepository = userDetailsRepository;
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
        // TODO 之后增加登陆时间和登陆日志
        return new LoginResult(token);
    }

    @Override
    public AuthInfoResult getAdminInfo(String username) {
        Admin admin = adminRepository.findByUsername(username);
        AuthInfoResult result = new AuthInfoResult();
        result.setUsername(username);
        result.setAvatar(admin.getAvatar());
        result.setPerms(new String[]{"*"});
        return result;
    }

    @Override
    public Page<Admin> list(String username, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return adminRepository.findAll((Specification<Admin>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();

            list.add(criteriaBuilder.equal(root.get("deleted").as(Integer.class), 0));

            if (!StringUtils.isEmpty(username)) {
                list.add(criteriaBuilder.like(root.get("username").as(String.class), "%" + username + "%"));
            }
            Predicate[] p = new Predicate[list.size()];
            return criteriaBuilder.and(list.toArray(p));
        }, pageable);
    }

    @Override
    public AdminReadResult info(Integer id) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
        AdminReadResult result = new AdminReadResult();
        result.setId(admin.getId());
        result.setAvatar(admin.getAvatar());
        result.setUsername(admin.getUsername());
        result.setRemark(admin.getRemark());
        return result;
    }

    @Override
    public Admin create(CreateAdminParam createAdminParam) {

        Admin existing = adminRepository.findByUsername(createAdminParam.getUsername());
        Assert.isNull(existing, "管理员已存在: " + createAdminParam.getUsername());

        String rawPassword = createAdminParam.getPassword();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(rawPassword);

        Admin admin = new Admin().preInsert();
        admin.setAvatar(createAdminParam.getAvatar());
        admin.setUsername(createAdminParam.getUsername());
        admin.setRemark(createAdminParam.getRemark());
        admin.setPassword(encodedPassword);

        return adminRepository.save(admin);
    }

    @Override
    public Admin updateById(Integer id, UpdateAdminParam updateAdminParam) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);

        admin.setRemark(updateAdminParam.getRemark());
        admin.setAvatar(updateAdminParam.getAvatar());
        admin.setUpdateTime(new Date());
        adminRepository.save(admin);
        return admin;
    }

    @Override
    public void deleteById(Integer id) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);

        admin.setDeleted(1);
        adminRepository.save(admin);
    }
}
