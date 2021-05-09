package me.suhyuk.springboot.configs;

import me.suhyuk.springboot.services.MyService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {

    @Bean
    public MyService getService() {
        return new MyService();
    }
}
