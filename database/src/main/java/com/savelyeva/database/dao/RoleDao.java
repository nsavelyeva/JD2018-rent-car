package com.savelyeva.database.dao;

import com.savelyeva.database.model.Role;

public interface RoleDao extends BaseDao<Integer, Role> {

    Role findByRoleName(String roleName);
}
