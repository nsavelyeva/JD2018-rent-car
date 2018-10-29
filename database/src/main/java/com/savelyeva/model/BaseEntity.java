package com.savelyeva.model;

import java.io.Serializable;

public interface BaseEntity<PK extends Serializable> {

    PK getId();
}

