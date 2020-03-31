package org.sang.controller;

import org.sang.bean.Role;
import org.sang.bean.RespBean;
import org.sang.bean.User;
import org.sang.service.CategoryService;
import org.sang.service.RoleService;
import org.sang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 超级管理员专属Controller
 */
/**
 * Created by albert on 2019/12/19.
 */

@RestController
@RequestMapping("/admin/role")
public class RoleController {

    @Autowired
    RoleService roleService;
    @Autowired
    UserService userService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Role> getAllRole() {
        return roleService.getAllRole();
    }

    @RequestMapping(value = "/delete{ids}", method = RequestMethod.DELETE)
    public RespBean deleteById(@PathVariable String ids) {
        boolean result = roleService.deleteRoleByIds(ids);
        if (result) {
            return new RespBean("success", "删除成功!");
        }
        return new RespBean("error", "删除失败!");
    }

    @RequestMapping(value = "/addRole", method = RequestMethod.POST)
    public RespBean addNewCate(Role role) {
        if ("".equals(role.getName()) || role.getName() == null) {
            return new RespBean("error", "请输入角色名称!");
        }
        int result = roleService.addRole(role);
        if (result == 1) {
            return new RespBean("success", "添加成功!");
        }
        return new RespBean("error", "添加失败!");
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public RespBean updateCate(Role role) {
        int i = roleService.updateRoleById(role);
        if (i == 1) {
            return new RespBean("success", "修改成功!");
        }
        return new RespBean("error", "修改失败!");
    }
}
