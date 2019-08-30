package com.github.w4o.sa.service.impl;

import com.github.w4o.sa.domain.Admin;
import com.github.w4o.sa.dto.AdminReadResult;
import com.github.w4o.sa.dto.CreateAdminParam;
import com.github.w4o.sa.dto.UpdateAdminParam;
import com.github.w4o.sa.repository.AdminRepository;
import com.github.w4o.sa.service.AdminService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author frank
 * @date 2019-05-14
 */
@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
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
        BeanUtils.copyProperties(admin, result);
        return result;
    }

    @Override
    public Admin create(CreateAdminParam createAdminParam) {

        Admin existing = adminRepository.findByUsername(createAdminParam.getUsername());
        Assert.isNull(existing, "管理员已存在: " + createAdminParam.getUsername());

        String rawPassword = createAdminParam.getPassword();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(rawPassword);

        Admin admin = new Admin();
        BeanUtils.copyProperties(createAdminParam, admin);
        admin.preInsert();
        admin.setPassword(encodedPassword);

        return adminRepository.save(admin);
    }

    @Override
    public Admin updateById(Integer id, UpdateAdminParam updateAdminParam) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
        admin.preUpdate();
        BeanUtils.copyProperties(updateAdminParam, admin);
        adminRepository.save(admin);
        return admin;
    }

    @Override
    public void deleteById(Integer id) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
        admin.preDelete();
        adminRepository.save(admin);
    }
}
