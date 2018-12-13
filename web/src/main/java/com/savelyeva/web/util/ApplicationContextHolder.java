package com.savelyeva.web.util;

import com.savelyeva.database.configuration.DatabaseConfiguration;
import com.savelyeva.service.configuration.ServiceConfiguration;
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

    public String[] getBeans() {
        return CONTEXT.getBeanDefinitionNames();
    }

}