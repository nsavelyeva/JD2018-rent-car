package com.savelyeva.database.dao;

import com.querydsl.jpa.impl.JPAQuery;
import com.savelyeva.database.model.Role;
import com.savelyeva.database.model.QRole;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;


@Repository
public class RoleDaoImpl extends BaseDaoImpl<Integer, Role> implements RoleDao {

    @Override
    public Role findByRoleName(String roleName) {
        Session session = this.getSessionFactory().getCurrentSession();
        return new JPAQuery<Role>(session)
                .select(QRole.role1)
                .from(QRole.role1)
                .where(QRole.role1.role.eq(roleName))
                .fetchOne();
    }

}
