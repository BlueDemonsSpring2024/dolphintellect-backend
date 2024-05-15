package com.bluedemons2024.dolphintellect_backend;

import com.bluedemons2024.dolphintellect_backend.Security.RSAKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RSAKeyProperties.class)
public class DolphIntellectBackend {

    public static void main(String[] args) {
        SpringApplication.run(DolphIntellectBackend.class, args);
    }


//    @Bean
//    CommandLineRunner init(UserRepistory accountRepository) {
//        return args -> {
//            accountRepository.save(new UserEntity("user", "user@sample.com","password","5gggoin22rgnoin"));
//            accountRepository.save(new UserEntity("admin", "admin@sample.com","password",null));
//
//        };
//    }
}

