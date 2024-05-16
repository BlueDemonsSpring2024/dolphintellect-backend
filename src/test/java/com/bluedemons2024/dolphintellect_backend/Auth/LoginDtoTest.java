package com.bluedemons2024.dolphintellect_backend.Auth;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginDtoTest {

    private LoginDto loginDto;

    @BeforeEach
    void setup(){
        loginDto = new LoginDto();

        loginDto.setPassword("fakePassword");
        loginDto.setUsername("fakeUsername");
    }

    @Test
    void getUsername() {
        assertEquals("fakeUsername",loginDto.getUsername());
    }

    @Test
    void setUsername() {
        loginDto.setUsername("newUsername");
        assertEquals("newUsername",loginDto.getUsername());
    }

    @Test
    void getPassword() {
        assertEquals("fakePassword",loginDto.getPassword());
    }

    @Test
    void setPassword() {
        loginDto.setPassword("newPassword");
        assertEquals("newPassword",loginDto.getPassword());
    }
}