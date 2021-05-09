package me.suhyuk.springboot;

import me.suhyuk.springboot.services.MyService;
import me.suhyuk.springboot.configs.MyConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MyApplication {
    public static void main(String[] args) {
        ApplicationContext appContext = new AnnotationConfigApplicationContext(MyConfig.class);
        MyService myService = appContext.getBean(MyService.class);
        myService.printServiceName();
    }
}
