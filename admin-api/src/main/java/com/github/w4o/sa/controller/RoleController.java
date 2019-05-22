package com.github.w4o.sa.controller;

import com.github.w4o.sa.commons.CommonResult;
import com.github.w4o.sa.dto.CreateRoleParam;
import com.github.w4o.sa.dto.UpdateRoleParam;
import com.github.w4o.sa.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author frank
 * @date 2019-05-17
 */
@RestController
@RequestMapping("/role")
@Api(tags = "系统管理-角色管理")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    /**
     * 角色列表
     */
    @ApiOperation("获取角色列表")
    @GetMapping("/list")
    public CommonResult list(@RequestParam(value = "name", required = false) String name,
                             @RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "5") Integer size) {
        return new CommonResult().okPage(roleService.list(name, page, size));
    }

    /**
     * 创建角色
     */
    @ApiOperation("创建角色")
    @PutMapping("/create")
    public CommonResult create(@RequestBody CreateRoleParam createRoleParam) {
        return new CommonResult().ok(roleService.create(createRoleParam));
    }

    /**
     * 更新角色信息
     */
    @ApiOperation("更新角色信息")
    @PostMapping("/update/{id}")
    public CommonResult update(@PathVariable(value = "id") Integer id,
                               @RequestBody UpdateRoleParam updateRoleParam) {
        return new CommonResult().ok(roleService.updateById(id, updateRoleParam));
    }

    /**
     * 删除角色信息
     */
    @ApiOperation("删除角色信息")
    @DeleteMapping("/delete/{id}")
    public CommonResult delete(@PathVariable(value = "id") Integer id) {
        return new CommonResult().ok(roleService.deleteById(id));
    }

    /**
     * 获取权限和当前角色分配的权限
     *
     * @param id 角色id
     */
    @ApiOperation("获取所有权限和当前角色分配的权限")
    @GetMapping("/{id}/permissions")
    public CommonResult permissions(@PathVariable(value = "id") Integer id) {
        return new CommonResult().ok(roleService.getPermissions(id));
    }

    /**
     * 更新角色权限
     *
     * @param id 角色id
     */
    @ApiOperation("更新角色权限")
    @PostMapping("/{id}/permissions")
    public CommonResult updatePermissions(@PathVariable(value = "id") Integer id,
                                          @RequestBody Integer[] permissions) {
        roleService.updatePermissions(id, permissions);
        return new CommonResult().ok();
    }
}
