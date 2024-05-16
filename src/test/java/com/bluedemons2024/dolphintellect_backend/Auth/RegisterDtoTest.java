package com.bluedemons2024.dolphintellect_backend.Auth;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegisterDtoTest {

    private RegisterDto registerDto;

    @BeforeEach
    void setup(){
        registerDto = new RegisterDto();

        registerDto.setEmail("fakeemail@email.com");
        registerDto.setUsername("fakeusername");
        registerDto.setPassword("fakepassword");
        registerDto.setStudentName("fakename");


    }

    @Test
    void getUsername() {
        assertEquals("fakeusername",registerDto.getUsername());
    }

    @Test
    void setUsername() {
        registerDto.setUsername("newusername");
        assertEquals("newusername",registerDto.getUsername());
    }

    @Test
    void getPassword() {
        assertEquals("fakepassword",registerDto.getPassword());
    }

    @Test
    void setPassword() {
        registerDto.setPassword("newpassword");
        assertEquals("newpassword",registerDto.getPassword());
    }

    @Test
    void getEmail() {
        assertEquals("fakeemail@email.com",registerDto.getEmail());
    }

    @Test
    void setEmail() {
        registerDto.setEmail("newemail@email.com");
        assertEquals("newemail@email.com",registerDto.getEmail());
    }

    @Test
    void getStudentName() {
        assertEquals("fakename",registerDto.getStudentName());
    }

    @Test
    void setStudentName() {
        registerDto.setStudentName("newStudentName");
        assertEquals("newStudentName",registerDto.getStudentName());
    }
}