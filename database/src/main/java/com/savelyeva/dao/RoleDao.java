package com.savelyeva.dao;

import com.savelyeva.model.Role;

public interface RoleDao extends BaseDao<Integer, Role> {

    Role findByRoleName(String roleName);
}
