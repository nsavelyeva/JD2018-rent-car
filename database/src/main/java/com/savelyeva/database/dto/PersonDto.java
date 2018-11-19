package com.savelyeva.database.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;


@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Builder
public class PersonDto {

    private Long id;

    private String email;

    private String firstName;

    private String lastName;

    private Instant birthDate;

    private String passport;

    private String gender;

    private String foreigners;

}
