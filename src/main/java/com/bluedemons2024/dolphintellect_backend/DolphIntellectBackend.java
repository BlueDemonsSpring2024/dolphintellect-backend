package com.bluedemons2024.dolphintellect_backend;

import com.bluedemons2024.dolphintellect_backend.Account.Account;
import com.bluedemons2024.dolphintellect_backend.Account.AccountRepository;
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
//    CommandLineRunner init(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
//        return args -> {
//            accountRepository.save(new Account("user", "user@sample.com",passwordEncoder.encode("password"),"5gggoin22rgnoin", "USER"));
//            accountRepository.save(new Account("admin", "admin@sample.com",passwordEncoder.encode("password"),null, "ADMIN"));
//
//        };
//    }
}

