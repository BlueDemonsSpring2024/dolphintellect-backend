package com.bluedemons2024.dolphintellect_backend.Security;

import com.bluedemons2024.dolphintellect_backend.Account.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//import static org.springframework.security.config.Customizer.*;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private JwtAuthEntryPoint authEntryPoint;
    private CustomUserDetailsService userDetailsService;

    @Autowired
    public SecurityConfig(CustomUserDetailsService userDetailsService, JwtAuthEntryPoint authEntryPoint) {
        this.userDetailsService = userDetailsService;
        this.authEntryPoint = authEntryPoint;
    }



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
         http
                 .csrf(AbstractHttpConfigurer::disable)
                 .exceptionHandling((exception)-> exception.authenticationEntryPoint(authEntryPoint))
                 .sessionManagement(sessionManagementCustomizer -> sessionManagementCustomizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorizeHttpRequests-> authorizeHttpRequests
                        .requestMatchers(HttpMethod.GET,"/api/health").permitAll()
                        .requestMatchers(HttpMethod.POST,"/api/auth/login").permitAll() //login
                        .requestMatchers(HttpMethod.POST,"/api/auth/register-admin").hasAuthority("ADMIN") //register-admin
                        .requestMatchers(HttpMethod.POST,"/api/auth/register-student").hasAuthority("ADMIN") //register-student
                        .requestMatchers(HttpMethod.DELETE,"/api/auth/delete-student/").hasAuthority("ADMIN") //delete-student


                        .requestMatchers(HttpMethod.GET, "/api/courses/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/courses/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/courses/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/courses/**").hasAuthority("ADMIN")

                        .requestMatchers("/student/all").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET,"/student").hasAuthority("USER")

                        .requestMatchers(HttpMethod.POST,"/student/enrolled-course").hasAuthority("USER")
                        .requestMatchers(HttpMethod.PUT,"/student/enrolled-course").hasAuthority("USER")
                        .requestMatchers(HttpMethod.DELETE,"/student/enrolled-course/**").hasAuthority("USER")

                        .requestMatchers(HttpMethod.POST,"/student/grade-item").hasAuthority("USER")
                        .requestMatchers(HttpMethod.PUT,"/student/grade-item").hasAuthority("USER")
                        .requestMatchers(HttpMethod.DELETE,"/student/grade-item/**").hasAuthority("USER")

                )
                .httpBasic(Customizer.withDefaults());

        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JWTAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        return new JWTAuthenticationFilter();
    }


}
