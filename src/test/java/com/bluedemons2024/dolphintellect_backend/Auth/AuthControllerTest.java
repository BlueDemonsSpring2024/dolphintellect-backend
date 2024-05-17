package com.bluedemons2024.dolphintellect_backend.Auth;

import com.bluedemons2024.dolphintellect_backend.Account.RoleRepository;
import com.bluedemons2024.dolphintellect_backend.Account.UserEntity;
import com.bluedemons2024.dolphintellect_backend.Account.UserRepistory;
import com.bluedemons2024.dolphintellect_backend.Security.JWTGenerator;
import com.bluedemons2024.dolphintellect_backend.Student.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthControllerTest {

    @Mock
    private StudentRepository studentRepository;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private UserRepistory userRepistory;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private JWTGenerator jwtGenerator;

    private AuthController authController;
    private LoginDto loginDto;



    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
        authController= new AuthController(authenticationManager,userRepistory,roleRepository,passwordEncoder,jwtGenerator,studentRepository);

        loginDto = new LoginDto();
        loginDto.setPassword("testpassword");
        loginDto.setUsername("testusername");
    }

    @Test
    void login() {
        Authentication authentication=mock(Authentication.class);
        String mockToken="fakeToken";

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);
        when(jwtGenerator.generateToken(authentication)).thenReturn(mockToken);

        ResponseEntity<AuthResponseDTO> resp=authController.login(loginDto);

        ArgumentCaptor<UsernamePasswordAuthenticationToken> authTokenCaptor=ArgumentCaptor.forClass(UsernamePasswordAuthenticationToken.class);
        verify(authenticationManager).authenticate(authTokenCaptor.capture());
        UsernamePasswordAuthenticationToken capturedAuthToken=authTokenCaptor.getValue();

        assertEquals(loginDto.getUsername(),capturedAuthToken.getPrincipal());
        assertEquals(loginDto.getPassword(),capturedAuthToken.getCredentials());



    }

    @Test
    void registerAdmin() {
    }

    @Test
    void register() {
    }

    @Test
    void deleteStudent() {
        long userId=1L;
        UserEntity userEntity= new UserEntity();
        userEntity.setId(userId);
        userEntity.setStudentID("fakeid");

        when(userRepistory.existsById(userId)).thenReturn(true);
        when(userRepistory.findById(userId)).thenReturn(Optional.of(userEntity));

        ResponseEntity<String> resp= authController.deleteStudent(userId);

        verify(studentRepository,times(1)).deleteById("fakeid");
        verify(userRepistory,times(1)).deleteById(userId);
        assertEquals(HttpStatus.OK,resp.getStatusCode());
    }

}