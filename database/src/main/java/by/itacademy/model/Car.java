package by.itacademy.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Car {

    private Long id;
    private String brand;

    public String getBrand() {
        return brand;
    }
}
