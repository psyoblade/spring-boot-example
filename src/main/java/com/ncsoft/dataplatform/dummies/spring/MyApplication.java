package com.ncsoft.dataplatform.dummies.spring;

import com.ncsoft.dataplatform.dummies.spring.configs.MyConfig;
import com.ncsoft.dataplatform.dummies.spring.services.MyService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MyApplication {
    public static void main(String[] args) {
        ApplicationContext appContext = new AnnotationConfigApplicationContext(MyConfig.class);
        MyService myService = appContext.getBean(MyService.class);
        myService.printServiceName();
    }
}
