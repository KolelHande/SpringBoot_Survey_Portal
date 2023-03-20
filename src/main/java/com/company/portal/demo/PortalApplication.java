package com.company.portal.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class PortalApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(PortalApplication.class, args);
    }


}
