package org.sang.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.sang.bean.Role;

import java.util.List;

/**
 * Created by albert on 2019/12/19.
 */
@Mapper
public interface RolesMapper {
    int addRoles(@Param("roles") String[] roles, @Param("uid") Long uid);

    List<Role> getRolesByUid(Long uid);

    List<Role> getAllRole();

    int deleteRoleByIds(@Param("ids") String[] ids);

    int updateRoleById(Role role);

    int addRole(Role role);
}
