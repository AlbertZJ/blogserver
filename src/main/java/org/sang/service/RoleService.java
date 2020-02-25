package org.sang.service;

import org.sang.bean.Role;
import org.sang.mapper.CategoryMapper;
import org.sang.mapper.RolesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by albert on 2019/12/19.
 */
@Service
@Transactional
public class RoleService {
    @Autowired
    RolesMapper roleMapper;

    public List<Role> getAllRole() {
        return roleMapper.getAllRole();
    }

    public boolean deleteRoleByIds(String ids) {
        String[] split = ids.split(",");
        int result = roleMapper.deleteRoleByIds(split);
        return result == split.length;
    }

    public int updateRoleById(Role role) {
        return roleMapper.updateRoleById(role);
    }

    public int addRole(Role role) {
        role.setDate(new Timestamp(System.currentTimeMillis()));
        return roleMapper.addRole(role);
    }
}
