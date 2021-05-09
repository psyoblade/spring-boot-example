package me.suhyuk.springboot.config;

import me.suhyuk.springboot.service.MyService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {

    @Bean
    public MyService getService() {
        return new MyService();
    }
}
