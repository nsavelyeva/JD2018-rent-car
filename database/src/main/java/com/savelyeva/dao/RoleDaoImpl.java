package com.savelyeva.dao;

import com.querydsl.jpa.impl.JPAQuery;
import com.savelyeva.model.Role;
import com.savelyeva.model.QRole;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
