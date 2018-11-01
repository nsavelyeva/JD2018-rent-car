package com.savelyeva.util;

import com.savelyeva.model.Audit;
import com.savelyeva.model.Category;
import com.savelyeva.model.City;
import com.savelyeva.model.Color;
import com.savelyeva.model.Country;
import com.savelyeva.model.DrivingLicense;
import com.savelyeva.model.Manufacturer;
import com.savelyeva.model.Model;
import com.savelyeva.model.Person;
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

        Audit audit =  Audit.builder().createdDate(Instant.now()).build();
        Audit period = Audit.builder().createdDate(Instant.now()).updatedDate(Instant.now()).build();

        Role role = Role.builder().role("Пользователь").build();
        session.save(role);

        Person person1 = Person.builder()
                .role(role)
                .login("person")
                .password("secret")
                .email("person@example.com")
                .audit(audit)
                .build();
        session.save(person1);
        Person person2 = Person.builder()
                .role(role)
                .login("natallia")
                .password("secret")
                .email("natallia@example.com")
                .audit(audit)
                .build();
        session.save(person2);

        Country country = Country.builder().country("The Netherlands").build();
        session.save(country);

        City city = City.builder().country(country).city("Amsterdam").build();
        session.save(city);

        Street street = Street.builder().city(city).street("Kalverstraat").build();
        session.save(street);

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
    }
}
