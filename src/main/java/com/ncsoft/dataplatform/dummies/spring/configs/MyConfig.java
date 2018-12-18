package com.ncsoft.dataplatform.dummies.spring.configs;

import com.ncsoft.dataplatform.dummies.spring.services.MyService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {

    @Bean
    public MyService getService() {
        return new MyService();
    }
}
