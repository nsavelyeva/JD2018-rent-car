package com.savelyeva.dao;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.savelyeva.dto.PaginationDto;
import com.savelyeva.dto.PersonDto;
import com.savelyeva.model.QAddress;
import com.savelyeva.model.QCity;
import com.savelyeva.model.QCountry;
import com.savelyeva.model.QPerson;
import com.savelyeva.model.Person;
import com.savelyeva.model.QPersonData;
import com.savelyeva.model.QStreet;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonDaoImpl extends BaseDaoImpl<Long, Person> implements PersonDao {

    @Override
    public List<Person> findByAttributes(PersonDto personDto, PaginationDto paginationDto) {
        Session session = this.getSessionFactory().getCurrentSession();

        BooleanBuilder predicate = new BooleanBuilder();
        if (!StringUtils.isEmpty(personDto.getEmail())) {
            predicate = predicate.and(QPerson.person.email.eq(personDto.getEmail()));
        }
        if (!StringUtils.isEmpty(personDto.getGender())) {
            predicate = predicate.and(QPersonData.personData.gender.stringValue().eq(personDto.getGender()));
        }
        if (!StringUtils.isEmpty(personDto.getForeigners())) {
            predicate = predicate.and(QCountry.country1.country.ne("Belarus"));
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
                .limit(paginationDto.getLimit())
                .offset(paginationDto.getLimit() * (paginationDto.getPage() - 1))
                .fetch();
    }

}
