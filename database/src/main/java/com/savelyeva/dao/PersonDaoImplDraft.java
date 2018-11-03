package com.savelyeva.dao;

import com.querydsl.jpa.impl.JPAQuery;
import com.savelyeva.model.Person;
import com.savelyeva.model.QPerson;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.Session;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PersonDaoImplDraft {

    private static final PersonDaoImplDraft INSTANCE = new PersonDaoImplDraft();

    public static PersonDaoImplDraft getInstance() {
        return INSTANCE;
    }

    public List<Person> findAll(Session session) {
        return new JPAQuery<Person>(session)
                .select(QPerson.person)
                .from(QPerson.person)
                .fetch();
    }

   public Person findById(Session session, Long id) {
        return new JPAQuery<Person>(session)
                .select(QPerson.person)
                .from(QPerson.person)
                .where(QPerson.person.id.eq(id))
                .fetchOne();
    }
/*
    public void save(Session session, PersonData personData) {
        Query<PersonData> query = session
                .createQuery("select e " +
                                "from PersonData e " +
                                "order by e.birthday",
                        PersonData.class);
        List<PersonData> list = query.list();
        //return list;
    }

    public void update(Session session, Long id, PersonData personData) {
        Query<PersonData> query = session
                .createQuery("select e " +
                                "from Employee e " +
                                "join e.organization o " +
                                "where o.name = :organizationName",
                        PersonData.class)
                .setParameter("id", id);
        List<PersonData> list = query.list();
        //return list;
    }

    public void delete(Session session, Long id) {
        Query<PersonData> query = session
                .createQuery("select p " +
                                "from Payment p " +
                                "join p.receiver e " +
                                "join e.organization o " +
                                "where o.name = :organizationName " +
                                "order by e.firstName, p.amount",
                        PersonData.class)
                .setParameter("id", id);
        List<PersonData> list = query.list();
        //return list;
    }*/
}
