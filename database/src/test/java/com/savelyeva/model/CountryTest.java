package com.savelyeva.model;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

public class CountryTest {
/*
    private static SessionFactory sessionFactory;

    @BeforeClass
    public static void initDb() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        CreateTestData.getInstance().importTestData(sessionFactory);
    }

    @AfterClass
    public static void closeFactory() {
        sessionFactory.close();
    }

    @Test
    public void checkSaveEntity() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        Country sessionCountry = Country.builder()
                .country("Египет")
                .build();
        Serializable savedId = session.save(sessionCountry);

        session.getTransaction().commit();

        assertNotNull(savedId);
    }

    @Test
    public void checkGetById() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        Country sessionCountry = Country.builder()
                .country("Беларусь")
                .build();
        session.save(sessionCountry);

        session.evict(sessionCountry);

        Country databaseCountry = session.find(Country.class, sessionCountry.getId());

        session.getTransaction().commit();

        assertEquals(sessionCountry, databaseCountry);
    }*/
}