package com.bluedemons2024.dolphintellect_backend.Account;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CustomUserDetailsServiceTest {

    @Mock
    private UserRepistory userRepistory;

    private CustomUserDetailsService customUserDetailsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        customUserDetailsService = new CustomUserDetailsService(userRepistory);
    }

    @Test
    void loadUserByUsername_UserExists() {
        String username="testuser";
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username);
        userEntity.setPassword("password");
        Role role = new Role();
        role.setId(345L);
        role.setName("fakeRole");
        userEntity.setRoles(new ArrayList<>(Collections.singleton(role)));

        when(userRepistory.findByUsername(username)).thenReturn(Optional.of(userEntity));

        UserDetails userDetails =customUserDetailsService.loadUserByUsername(username);
        System.out.println(userDetails);
        assertNotNull(userDetails);

    }

    @Test
    void loadUserByUsername_UserNotFound() {
        String username ="fakeuser7890";
        when(userRepistory.findByUsername(username)).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class,() -> {
            customUserDetailsService.loadUserByUsername(username);
        });
    }
}