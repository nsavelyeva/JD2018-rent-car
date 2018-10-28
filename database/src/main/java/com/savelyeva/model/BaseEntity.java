package com.savelyeva.model;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public interface BaseEntity<PK extends Serializable> {

    PK getId();
}

