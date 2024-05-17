package com.bluedemons2024.dolphintellect_backend.Account;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class RoleGenerator implements CommandLineRunner {
    private final RoleRepository roleRepository;

    RoleGenerator(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if(!roleRepository.existsByName("ADMIN")){
            roleRepository.save(new Role("ADMIN"));
        }

        if(!roleRepository.existsByName("USER")){
            roleRepository.save(new Role("USER"));
        }

        if(!roleRepository.existsByName("STUDENT")){
            roleRepository.save(new Role("STUDENT"));
        }
    }
}
