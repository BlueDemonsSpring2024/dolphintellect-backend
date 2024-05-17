package com.bluedemons2024.dolphintellect_backend.Security;

import com.bluedemons2024.dolphintellect_backend.Account.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.InjectMocks;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.junit.jupiter.api.Assertions.*;

class JWTAuthenticationFilterTest {

    @Mock
    private JWTGenerator tokenGenerator;
    @Mock
    private CustomUserDetailsService customUserDetailsService;
    @InjectMocks
    private JWTAuthenticationFilter jwtAuthenticationFilter;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private FilterChain filterChain;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        SecurityContextHolder.clearContext();
    }



    @Test
    void doFilterInternal() {
        String username = "fakeuser";
        String validToken = "validToken";

    }
}