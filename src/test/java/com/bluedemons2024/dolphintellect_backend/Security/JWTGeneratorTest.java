package com.bluedemons2024.dolphintellect_backend.Security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class JWTGeneratorTest {

    private JWTGenerator jwtGenerator;
    @Mock
    private Authentication authentication;


    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
        jwtGenerator= new JWTGenerator();


    }

    @Test
    void generateToken() {
        String username = "testUser";
        when(authentication.getName()).thenReturn(username);

        String token =jwtGenerator.generateToken(authentication);
        String extractedUsername =jwtGenerator.getUsernameFromToken(token);

        assertEquals(username,extractedUsername);

    }

    @Test
    void getUsernameFromToken() {
        String username = "testUser";
        Date currentDate = new Date();
        Date expirationDate = new Date(currentDate.getTime() + SecurityConstants.JWT_EXPIRATION);

        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(currentDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.JWT_SECRET)
                .compact();

        assertEquals(username,jwtGenerator.getUsernameFromToken(token));
    }

    @Test
    void validateToken() {
        String username = "testUser";
        Date currentDate = new Date();
        Date expirationDate = new Date(currentDate.getTime() + SecurityConstants.JWT_EXPIRATION);

        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(currentDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.JWT_SECRET)
                .compact();

        assertTrue(jwtGenerator.validateToken(token));

        String badToken=token+"123456";
        assertThrows(AuthenticationCredentialsNotFoundException.class,()->jwtGenerator.validateToken(badToken));

    }
}