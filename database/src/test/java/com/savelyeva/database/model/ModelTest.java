package com.savelyeva.database.model;

import com.savelyeva.database.configuration.DatabaseConfiguration;
import com.savelyeva.database.dao.ModelDaoImpl;
import com.savelyeva.database.util.ApplicationContextHolder;
import com.savelyeva.database.data.CreateTestData;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {DatabaseConfiguration.class})
@Transactional
public class ModelTest {

    @Autowired
    private static CreateTestData testData;

    private static SessionFactory sessionFactory;

    private static Session session;

    @BeforeClass
    public static void initDb() {
        ModelDaoImpl modelDaoImpl = ApplicationContextHolder.getBean("modelDaoImpl", ModelDaoImpl.class);
        sessionFactory = modelDaoImpl.getSessionFactory();
        testData = ApplicationContextHolder.getBean("createTestData", CreateTestData.class);
        testData.importTestData();
    }

    @AfterClass
    public static void closeFactory() {
        //sessionFactory.close();
        //testData.removeTestData(sessionFactory);
    }

    @Test
    public void checkSaveEntity() {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Manufacturer manufacturer = session.find(Manufacturer.class, 1);

        Model sessionModel = Model.builder().manufacturer(manufacturer).model("Q5").build();
        Serializable savedId = session.save(sessionModel);

        session.getTransaction().commit();

        assertNotNull(savedId);
    }

    @Test
    public void checkGetById() {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Manufacturer manufacturer = session.find(Manufacturer.class, 1);

        Model sessionModel = Model.builder().manufacturer(manufacturer).model("Q6").build();
        session.save(sessionModel);

        session.evict(sessionModel);

        Model databaseModel = session.find(Model.class, sessionModel.getId());

        session.getTransaction().commit();

        assertEquals(sessionModel, databaseModel);
    }
}