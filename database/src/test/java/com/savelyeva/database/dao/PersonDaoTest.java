package com.savelyeva.database.dao;

import com.savelyeva.database.configuration.DatabaseConfiguration;
import com.savelyeva.database.data.CreateTestData;
import com.savelyeva.database.dto.PaginationDto;
import com.savelyeva.database.dto.PersonDto;
import com.savelyeva.database.model.Audit;
import com.savelyeva.database.model.Person;
import com.savelyeva.database.model.Role;
import com.savelyeva.database.util.ApplicationContextHolder;
import org.apache.commons.lang3.StringUtils;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {DatabaseConfiguration.class})
@Transactional
//@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PersonDaoTest {

    @Autowired
    private static CreateTestData testData;

    @Autowired
    private PersonDao personDao;

    @Autowired
    private RoleDao roleDao;

    @BeforeClass
    public static void initDb() {
        testData = ApplicationContextHolder.getBean("createTestData", CreateTestData.class);
        testData.importTestData();
    }

    @AfterClass
    public static void closeFactory() {
        // sessionFactory.close();
    }

    @Test
    public void checkContext() {
        Assert.assertNotNull(personDao);
    }

    @Test
    public void checkSave() {
        Audit audit =  Audit.builder().createdDate(Instant.now()).build();
        Optional<Role> role = roleDao.find(1);
        Person person = Person.builder()
                .role(role.isPresent() ? role.get() : null)
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
        Optional<Person> personOptional = personDao.find(2L);
        Person person = personOptional.isPresent() ? personOptional.get() : null;
        String oldPassword = person.getPassword();
        String newPassword = oldPassword + "updated";
        person.setPassword(newPassword);

        personDao.update(person);
        Optional<Person> personOptionalUpdated = personDao.find(2L);
        Person personUpdated = personOptionalUpdated.isPresent() ? personOptionalUpdated.get() : null;

        assertEquals(personUpdated.getPassword(), newPassword);
    }

    @Test
    public void checkDelete() {
        Audit audit =  Audit.builder().createdDate(Instant.now()).build();
        Optional<Role> role = roleDao.find(1);
        Person person = Person.builder()
                .role(role.isPresent() ? role.get() : null)
                .login("user")
                .password("secret")
                .email("user@example.com")
                .audit(audit)
                .build();
        Serializable savedId = personDao.save(person);

        Optional<Person> personFound = personDao.find((Long) savedId);
        personDao.delete(personFound.get());

        Optional<Person> deletedPerson = personDao.find((Long) savedId);
        assertFalse(deletedPerson.isPresent());
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
