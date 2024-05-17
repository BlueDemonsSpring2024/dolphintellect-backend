package com.bluedemons2024.dolphintellect_backend.Account;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@Component
@Order(2)
public class AdminGenerator implements CommandLineRunner {
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    AdminGenerator(RoleRepository roleRepository, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;

    }

    @Override
    @Transactional("mysqlTransactionManager")
    public void run(String... args) throws Exception {

        if(!userRepository.existsByUsername("admin")){
            String tempPassword = "tempPassword";
            UserEntity defaultAdminUser = new UserEntity();
            defaultAdminUser.setUsername("admin");
            defaultAdminUser.setPassword(passwordEncoder.encode(tempPassword));

            Optional<Role> optionalRole = roleRepository.findByName("ADMIN");
            if (optionalRole.isPresent()) {

                Role roles = optionalRole.get();

                defaultAdminUser.setRoles(Collections.singletonList(roles));
                userRepository.save(defaultAdminUser);

                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
                System.out.println("Created default admin: " + defaultAdminUser.getUsername());
                System.out.println("Password: " + tempPassword);
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
            }
            else{
                throw new RuntimeException("ADMIN role not found. Unable to create default admin.");
            }

        }

    }
}
