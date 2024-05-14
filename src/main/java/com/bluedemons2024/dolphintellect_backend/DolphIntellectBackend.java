package com.bluedemons2024.dolphintellect_backend;

import com.bluedemons2024.dolphintellect_backend.Account.UserEntity;
import com.bluedemons2024.dolphintellect_backend.Account.UserRepistory;
import com.bluedemons2024.dolphintellect_backend.config.RSAKeyProperties;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

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

