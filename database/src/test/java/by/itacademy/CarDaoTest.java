package by.itacademy;

import by.itacademy.model.Car;
import by.itacademy.dao.CarDao;
import org.junit.Assert;
import org.junit.Test;

public class CarDaoTest {

    private Car car = CarDao.getInstance().getDefaultCar();

    @Test
    public void checkDefaultCarModel() {
        String actualResult = car.getBrand();
        String expectedResult = "Volkswagen";
        Assert.assertEquals("Default car brand is wrong", actualResult, expectedResult);
    }
}
