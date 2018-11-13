package com.savelyeva.dao;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.savelyeva.model.QAddress;
import com.savelyeva.model.QCity;
import com.savelyeva.model.QCountry;
import com.savelyeva.model.QPerson;
import com.savelyeva.model.Person;
import com.savelyeva.model.QPersonData;
import com.savelyeva.model.QStreet;
import lombok.Cleanup;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;

import java.util.List;

import static com.savelyeva.connection.ConnectionManager.getSession;

public class PersonDaoImpl extends BaseDaoImpl<Long, Person> implements PersonDao {

    private static final PersonDao INSTANCE = new PersonDaoImpl();

    public static PersonDao getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Person> findByAttributes(String email, String gender, String foreigners, Integer limit, Integer page) {
        @Cleanup Session session = getSession();

        BooleanBuilder predicate = new BooleanBuilder();
        if (!StringUtils.isEmpty(email)) {
            predicate = predicate.and(QPerson.person.email.eq(email));
        }
        if (!StringUtils.isEmpty(gender)) {
            predicate = predicate.and(QPersonData.personData.gender.stringValue().eq(gender));
        }
        if (!StringUtils.isEmpty(foreigners)) {
            predicate = predicate.and(QCountry.country1.country.eq("Belarus"));
        }
        return new JPAQuery<Person>(session)
                .select(QPerson.person)
                .from(QPerson.person)
                .join(QPerson.person.personData, QPersonData.personData)
                .join(QPersonData.personData.address, QAddress.address)
                .join(QAddress.address.street, QStreet.street1)
                .join(QStreet.street1.city, QCity.city1)
                .join(QCity.city1.country, QCountry.country1)
                .where(predicate)
                .limit(limit)
                .offset(limit * (page - 1))
                .fetch();
    }

}
