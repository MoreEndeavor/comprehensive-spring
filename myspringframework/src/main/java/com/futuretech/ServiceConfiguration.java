package com.futuretech;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {
    @Bean
    public Dao dao() {
        return new Dao();
    }
}
