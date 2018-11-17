package com.savelyeva.dao;

import com.savelyeva.configuration.ApplicationConfiguration;
import com.savelyeva.dto.PaginationDto;
import com.savelyeva.dto.PersonDto;
import com.savelyeva.model.Audit;
import com.savelyeva.model.Person;
import com.savelyeva.model.Role;
import com.savelyeva.util.CreateTestData;
import org.apache.commons.lang3.StringUtils;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertEquals;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {ApplicationConfiguration.class})
@Transactional
public class PersonDaoTest {

    @Autowired
    private PersonDao personDao;

    @Autowired
    private RoleDao roleDao;

    @Value("classpath:database.properties")
    private Resource resource;

    /*@Value("${db.username}")
    private String username;*/

    @BeforeClass
    public static void initDb() {
        //CreateTestData.getInstance().importTestData();
        //CreateTestData testData =
                new CreateTestData().importTestData();

    }

    @AfterClass
    public static void closeFactory() {
        //sessionFactory.close();
    }

    @Test
    public void checkContext() {
        Assert.assertNotNull(personDao);
        System.out.println();
    }

    @Test
    public void checkSave() {
        Audit audit =  Audit.builder().createdDate(Instant.now()).build();
        //Role role = roleDao.find(1).get();
        Role role = Role.builder().role("abc").build();
        roleDao.save(role);

        Person person = Person.builder()
                .role(role)
                .login("user")
                .password("secret")
                .email("user@example.com")
                .audit(audit)
                .build();
        Serializable savedId = personDao.save(person);

        Optional<Person> personFound = personDao.find((Long) savedId);
        personDao.delete(personFound.get());

        assertNotNull(savedId);
    }

    @Test
    public void checkUpdate() {
        Person person = personDao.find(1L).get();
        String oldPassword = person.getPassword();
        String newPassword = oldPassword + "updated";
        person.setPassword(newPassword);

        personDao.update(person);
        Person personUpdated = personDao.find(1L).get();

        assertEquals(personUpdated.getPassword(), newPassword);
    }

    @Test
    public void checkDelete() {
        Audit audit =  Audit.builder().createdDate(Instant.now()).build();
        Role role = roleDao.find(1).get();
        Person person = Person.builder()
                .role(role)
                .login("user")
                .password("secret")
                .email("user@example.com")
                .audit(audit)
                .build();
        Serializable savedId = personDao.save(person);

        Optional<Person> personFound = personDao.find((Long) savedId);
        personDao.delete(personFound.get());

        Optional<Person> deletedPerson = personDao.find((Long) savedId);
        assertNull(deletedPerson.get());
    }

    @Test
    public void checkFindById() {
        Optional<Person> person = personDao.find(1L);
        assertNotNull(person.get());
    }

    @Test
    public void checkFindAll() {
        List<Person> persons = personDao.findAll();
        assertEquals(2, persons.size());
    }

    @Test
    public void checkFindByAttributes() {
        PersonDto personDto = PersonDto.builder()
                .email(StringUtils.EMPTY)
                .gender("FEMALE")
                .foreigners(StringUtils.EMPTY)
                .build();
        PaginationDto paginationDto = PaginationDto.builder()
                .limit(5)
                .page(1)
                .build();
        List<Person> persons = personDao.findByAttributes(personDto, paginationDto);
        assertEquals(2, persons.size());
    }
}
