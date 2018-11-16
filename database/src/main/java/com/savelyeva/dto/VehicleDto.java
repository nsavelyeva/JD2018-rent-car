package com.savelyeva.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Builder
public class VehicleDto {

    private Long id;

    private String model;

    private String transmission;

    private String color;

    private String producedYear;

    private String fullPrice;

    private String dayPrice;

    private String available;

}
