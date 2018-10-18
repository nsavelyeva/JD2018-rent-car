package by.itacademy.dao;

import by.itacademy.model.Car;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CarDao {

    private static final CarDao INSTANCE = new CarDao();

    public Car getDefaultCar() {
        return Car.builder()
                .brand("Volkswagen")
                .build();
    }

    public static CarDao getInstance() {
        return INSTANCE;
    }
}
