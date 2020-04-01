package com.shanzuwang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.shanzuwang")
@SpringBootApplication
@EnableCaching
public class ShanzuwangApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(ShanzuwangApplication.class, args);
    }

}
