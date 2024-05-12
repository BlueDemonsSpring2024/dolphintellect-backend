package com.bluedemons2024.dolphintellect_backend.Auth;

import com.bluedemons2024.dolphintellect_backend.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);

    private final TokenService tokenService;

    public AuthController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/token")
    public String token(Authentication authentication){
        System.out.println("LOOK FOR ME");
        System.out.println(authentication.isAuthenticated());
        System.out.println(authentication.getCredentials());
        System.out.println(authentication.getPrincipal().toString());



        LOG.debug("Token requested for user: '{}'", authentication.getName());
        System.out.println("Token requested for user: " + authentication.getName());
        String token = tokenService.generateToken(authentication);
        LOG.debug("Token granted {}", token);
        return token;
    }

    @GetMapping
    public String home(){
        System.out.println("Home requested");
        return "Hello World!";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String admin(){
        System.out.println("Admin requested");
        return "Hello Admin!";
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user")
    public String user(){
        return "Hello User!";
    }





}
