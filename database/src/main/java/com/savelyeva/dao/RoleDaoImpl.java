package com.savelyeva.dao;

import com.querydsl.jpa.impl.JPAQuery;
import com.savelyeva.model.Role;
import com.savelyeva.model.QRole;
import lombok.Cleanup;
import org.hibernate.Session;

import static com.savelyeva.connection.ConnectionManager.getSession;


public class RoleDaoImpl extends BaseDaoImpl<Integer, Role> implements RoleDao {

    private static final RoleDao INSTANCE = new RoleDaoImpl();

    public static RoleDao getInstance() {
        return INSTANCE;
    }

    @Override
    public Role findByRoleName(String roleName) {
        @Cleanup Session session = getSession();
        return new JPAQuery<Role>(session)
                .select(QRole.role1)
                .from(QRole.role1)
                .where(QRole.role1.role.eq(roleName))
                .fetchOne();
    }

}
