package com.github.w4o.sa.service.impl;

import com.github.w4o.sa.domain.Permission;
import com.github.w4o.sa.domain.Role;
import com.github.w4o.sa.dto.CreateRoleParam;
import com.github.w4o.sa.dto.PermissionsResult;
import com.github.w4o.sa.dto.UpdateRoleParam;
import com.github.w4o.sa.repository.PermissionRepository;
import com.github.w4o.sa.repository.RoleRepository;
import com.github.w4o.sa.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author frank
 * @date 2019-05-17
 */
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository,
                           PermissionRepository permissionRepository) {
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
    }

    @Override
    public Page<Role> list(String name, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return roleRepository.findAll((Specification<Role>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();

            list.add(criteriaBuilder.equal(root.get("deleted").as(Integer.class), 0));

            if (!StringUtils.isEmpty(name)) {
                list.add(criteriaBuilder.like(root.get("name").as(String.class), "%" + name + "%"));
            }
            Predicate[] p = new Predicate[list.size()];
            return criteriaBuilder.and(list.toArray(p));
        }, pageable);
    }

    @Override
    public Role create(CreateRoleParam createRoleParam) {
        Role existing = roleRepository.findByName(createRoleParam.getName());
        Assert.isNull(existing, "角色已存在: " + createRoleParam.getName());

        Role role = new Role();
        role.preInsert();
        role.setName(createRoleParam.getName());
        role.setDescription(createRoleParam.getDescription());
        roleRepository.save(role);
        return role;
    }

    @Override
    public Role updateById(Integer id, UpdateRoleParam updateRoleParam) {
        Role role = roleRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);

        // 如果修改角色名，需要检查修改后的角色名在数据库中是否存在
        if (!role.getName().equals(updateRoleParam.getName())) {
            Role existing = roleRepository.findByName(updateRoleParam.getName());
            Assert.isNull(existing, "角色已存在: " + updateRoleParam.getName());
        }

        role.setName(updateRoleParam.getName());
        role.setDescription(updateRoleParam.getDescription());
        role.setUpdateTime(new Date());

        roleRepository.save(role);
        return role;
    }

    @Override
    public Role deleteById(Integer id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);

        role.setDeleted(1);
        role.setUpdateTime(new Date());
        roleRepository.save(role);
        return role;
    }

    @Override
    public PermissionsResult getPermissions(Integer roleId) {
        Iterable<Permission> allPermission = permissionRepository.findAll();

        return null;
    }
}
