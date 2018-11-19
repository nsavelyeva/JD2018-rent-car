package com.savelyeva.database.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;


@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Embeddable
public class Info {

    private String info;

}
