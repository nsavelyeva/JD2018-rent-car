package com.savelyeva.util;

import com.savelyeva.configuration.ApplicationConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContext<T> {

    private static final ApplicationContext INSTANCE = new ApplicationContext();

    public static ApplicationContext getInstance() {
        return INSTANCE;
    }

    public T getBean(String beanName) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        System.out.println("LOADED BEANS:");
        System.out.println(context.getBeanDefinitionNames());
        T bean = (T) context.getBean(beanName);
        return bean;
    }
}
