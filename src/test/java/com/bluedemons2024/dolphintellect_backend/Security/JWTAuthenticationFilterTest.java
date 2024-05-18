package com.bluedemons2024.dolphintellect_backend.Security;

import com.bluedemons2024.dolphintellect_backend.Account.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;
import org.mockito.InjectMocks;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
    @Mock
    private UserDetails userDetails;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }



    @Test
    void doFilterInternal() throws RuntimeException, ServletException, IOException {
        String username = "fakeuser";
        String validToken = "validToken";

        when(request.getHeader("Authorization")).thenReturn("Bearer "+validToken);
        when(tokenGenerator.validateToken(validToken)).thenReturn(true);
        when(tokenGenerator.getUsernameFromToken(validToken)).thenReturn(username);
        when(customUserDetailsService.loadUserByUsername(username)).thenReturn(userDetails);

        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        assertNotNull(SecurityContextHolder.getContext().getAuthentication());
        assertEquals(userDetails,SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }



    }

