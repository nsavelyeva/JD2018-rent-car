package com.savelyeva.util;

import com.savelyeva.model.Address;
import com.savelyeva.model.Audit;
import com.savelyeva.model.Category;
import com.savelyeva.model.City;
import com.savelyeva.model.Color;
import com.savelyeva.model.Country;
import com.savelyeva.model.DrivingLicense;
import com.savelyeva.model.Gender;
import com.savelyeva.model.Manufacturer;
import com.savelyeva.model.Model;
import com.savelyeva.model.Person;
import com.savelyeva.model.PersonData;
import com.savelyeva.model.Role;
import com.savelyeva.model.Street;
import lombok.AccessLevel;
import lombok.Cleanup;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.time.Instant;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CreateTestData {

    private static CreateTestData INSTANCE = new CreateTestData();

    public static CreateTestData getInstance() {
        return INSTANCE;
    }

    public void importTestData(SessionFactory sessionFactory) {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        Country country = Country.builder().country("The Netherlands").build();
        session.save(country);

        City city = City.builder().country(country).city("Amsterdam").build();
        session.save(city);

        Street street = Street.builder().city(city).street("Kalverstraat").build();
        session.save(street);

        Address address = Address.builder().street(street).building("105").flat("88").build();
        session.save(address);

        Audit audit =  Audit.builder().createdDate(Instant.now()).build();
        Audit period = Audit.builder().createdDate(Instant.now()).updatedDate(Instant.now()).build();

        Role role1 = Role.builder().role("Пользователь").build();
        session.save(role1);
        Role role2 = Role.builder().role("Заказчик").build();
        session.save(role2);

        Person person1 = Person.builder()
                .role(role1)
                .login("person")
                .password("secret")
                .email("person@example.com")
                .audit(audit)
                .build();
        session.save(person1);
        Person person2 = Person.builder()
                .role(role2)
                .login("natallia")
                .password("secret")
                .email("natallia@example.com")
                .audit(audit)
                .build();
        session.save(person2);

        PersonData personData1 = PersonData.builder()
                .person(person1)
                .firstName("Natallia")
                .lastName("Savelyeva")
                .birthDate(Instant.now())
                .gender(Gender.FEMALE)
                .passport("XZ123456")
                .build();
        personData1.setAddress(address);
        session.save(personData1);
        PersonData personData2 = PersonData.builder()
                .person(person2)
                .firstName("Natallia")
                .lastName("Savelyeva")
                .birthDate(Instant.now())
                .gender(Gender.FEMALE)
                .passport("ZX123456")
                .build();
        personData2.setAddress(address);
        session.save(personData2);

        Color color = Color.builder().color("жёлтый").build();
        session.save(color);

        Manufacturer manufacturerCar = Manufacturer.builder().manufacturer("Audi").build();
        session.save(manufacturerCar);
        Manufacturer manufacturerLorry = Manufacturer.builder().manufacturer("БелАЗ").build();
        session.save(manufacturerLorry);

        Model modelCar = Model.builder().manufacturer(manufacturerCar).model("A5").build();
        session.save(modelCar);
        Model modelLorry = Model.builder().manufacturer(manufacturerLorry).model("БелАЗ-7521").build();
        session.save(modelLorry);

        DrivingLicense drivingLicense = DrivingLicense.builder()
                .serialNumber("XZ123456")
                .drivingPeriod(period)
                .category(Category.B)
                .build();
        session.save(drivingLicense);

        session.getTransaction().commit();
    }
}
