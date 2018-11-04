package com.savelyeva.model;

import java.io.Serializable;

public interface BaseEntity<P extends Serializable> {

    P getId();
}

