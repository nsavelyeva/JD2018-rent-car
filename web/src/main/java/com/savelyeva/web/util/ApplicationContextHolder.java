package com.savelyeva.web.util;

import com.savelyeva.configuration.DatabaseConfiguration;
import com.savelyeva.configuration.ServiceConfiguration;
import lombok.experimental.UtilityClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@UtilityClass
public class ApplicationContextHolder {

    private static final @Autowired
    AnnotationConfigApplicationContext CONTEXT = new AnnotationConfigApplicationContext(
            ServiceConfiguration.class,
            DatabaseConfiguration.class
    );

    public <T> T getBean(String beanName, Class<T> clazz) {
        return CONTEXT.getBean(beanName, clazz);
    }

}
