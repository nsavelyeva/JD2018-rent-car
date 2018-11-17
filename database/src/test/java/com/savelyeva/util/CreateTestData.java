package com.savelyeva.util;

import com.savelyeva.configuration.ApplicationConfiguration;
import com.savelyeva.dao.AddressDao;
import com.savelyeva.dao.CarDao;
import com.savelyeva.dao.CityDao;
import com.savelyeva.dao.ColorDao;
import com.savelyeva.dao.CountryDao;
import com.savelyeva.dao.DrivingLicenseDao;
import com.savelyeva.dao.LorryDao;
import com.savelyeva.dao.ManufacturerDao;
import com.savelyeva.dao.ModelDao;
import com.savelyeva.dao.PersonDao;
import com.savelyeva.dao.PersonDataDao;
import com.savelyeva.dao.RoleDao;
import com.savelyeva.dao.StreetDao;
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
import lombok.NoArgsConstructor;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@NoArgsConstructor //(access = AccessLevel.PRIVATE)
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {ApplicationConfiguration.class})
@Transactional
public class CreateTestData {

    /*private static CreateTestData INSTANCE = new CreateTestData();

    public static CreateTestData getInstance() {
        return INSTANCE;
    }*/
    @Value("classpath:database.properties")
    private Resource resource;

    @Autowired
    private PersonDao personDao;

    @Autowired
    private PersonDataDao personDataDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private CarDao carDao;

    @Autowired
    private LorryDao lorryDao;

    @Autowired
    private ModelDao modelDao;

    @Autowired
    private ManufacturerDao manufacturerDao;

    @Autowired
    private ColorDao colorDao;

    @Autowired
    private DrivingLicenseDao drivingLicenseDao;

    @Autowired
    private CountryDao countryDao;

    @Autowired
    private CityDao cityDao;

    @Autowired
    private StreetDao streetDao;

    @Autowired
    private AddressDao addressDao;

    public void importTestData() {
        Country country = Country.builder().country("The Netherlands").build();
        countryDao.save(country);

        City city = City.builder().country(country).city("Amsterdam").build();
        cityDao.save(city);

        Street street = Street.builder().city(city).street("Kalverstraat").build();
        streetDao.save(street);

        Address address = Address.builder().street(street).building("105").flat("88").build();
        addressDao.save(address);

        Audit audit =  Audit.builder().createdDate(Instant.now()).build();
        Audit period = Audit.builder().createdDate(Instant.now()).updatedDate(Instant.now()).build();

        Role role1 = Role.builder().role("Пользователь").build();
        roleDao.save(role1);
        Role role2 = Role.builder().role("Заказчик").build();
        roleDao.save(role2);

        Person person1 = Person.builder()
                .role(role1)
                .login("person")
                .password("secret")
                .email("person@example.com")
                .audit(audit)
                .build();
        personDao.save(person1);
        Person person2 = Person.builder()
                .role(role2)
                .login("natallia")
                .password("secret")
                .email("natallia@example.com")
                .audit(audit)
                .build();
        personDao.save(person2);

        PersonData personData1 = PersonData.builder()
                .person(person1)
                .firstName("Natallia")
                .lastName("Savelyeva")
                .birthDate(Instant.now())
                .gender(Gender.FEMALE)
                .passport("XZ123456")
                .build();
        personData1.setAddress(address);
        personDataDao.save(personData1);
        PersonData personData2 = PersonData.builder()
                .person(person2)
                .firstName("Natallia")
                .lastName("Savelyeva")
                .birthDate(Instant.now())
                .gender(Gender.FEMALE)
                .passport("ZX123456")
                .build();
        personData2.setAddress(address);
        personDataDao.save(personData2);

        Color color = Color.builder().color("жёлтый").build();
        colorDao.save(color);

        Manufacturer manufacturerCar = Manufacturer.builder().manufacturer("Audi").build();
        manufacturerDao.save(manufacturerCar);
        Manufacturer manufacturerLorry = Manufacturer.builder().manufacturer("БелАЗ").build();
        manufacturerDao.save(manufacturerLorry);

        Model modelCar = Model.builder().manufacturer(manufacturerCar).model("A5").build();
        modelDao.save(modelCar);
        Model modelLorry = Model.builder().manufacturer(manufacturerLorry).model("БелАЗ-7521").build();
        modelDao.save(modelLorry);

        DrivingLicense drivingLicense = DrivingLicense.builder()
                .serialNumber("XZ123456")
                .drivingPeriod(period)
                .category(Category.B)
                .build();
        drivingLicenseDao.save(drivingLicense);
    }
}
