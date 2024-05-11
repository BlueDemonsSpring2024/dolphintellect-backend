package com.bluedemons2024.dolphintellect_backend;

import com.bluedemons2024.dolphintellect_backend.config.RSAKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RSAKeyProperties.class)
public class DolphIntellectBackend {

    public static void main(String[] args) {
        SpringApplication.run(DolphIntellectBackend.class, args);
    }

}

