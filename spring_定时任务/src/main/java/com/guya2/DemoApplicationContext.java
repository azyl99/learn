package com.guya2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("config.xml")
public class DemoApplicationContext {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplicationContext.class, args);
    }
}
