package com.bluedemons2024.dolphintellect_backend.Auth;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthResponseDTOTest {

    private AuthResponseDTO authResponseDTO;

    @BeforeEach
    void setup(){
        authResponseDTO = new AuthResponseDTO("fakeAccessToken");

        authResponseDTO.setTokenType("fakeTokenType");

    }

    @Test
    void getAccessToken() {
        assertEquals("fakeAccessToken",authResponseDTO.getAccessToken());
    }

    @Test
    void setAccessToken() {
        authResponseDTO.setAccessToken("newAccessToken");
        assertEquals("newAccessToken",authResponseDTO.getAccessToken());
    }

    @Test
    void getTokenType() {
        assertEquals("fakeTokenType",authResponseDTO.getTokenType());
    }

    @Test
    void setTokenType() {
        authResponseDTO.setTokenType("newTokenType");
        assertEquals("newTokenType",authResponseDTO.getTokenType());
    }
}