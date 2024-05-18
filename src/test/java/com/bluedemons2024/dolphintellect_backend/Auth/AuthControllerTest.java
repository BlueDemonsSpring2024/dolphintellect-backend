package com.bluedemons2024.dolphintellect_backend.Auth;

import com.bluedemons2024.dolphintellect_backend.Account.Role;
import com.bluedemons2024.dolphintellect_backend.Account.RoleRepository;
import com.bluedemons2024.dolphintellect_backend.Account.UserEntity;
import com.bluedemons2024.dolphintellect_backend.Account.UserRepistory;
import com.bluedemons2024.dolphintellect_backend.Security.JWTGenerator;
import com.bluedemons2024.dolphintellect_backend.Student.Student;
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
import org.springframework.security.core.userdetails.User;
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

        loginDto=new LoginDto();
        loginDto.setPassword("testpassword");
        loginDto.setUsername("testusername");
    }

    @Test
    void login() {
        Authentication authentication=mock(Authentication.class);
        String mockToken="fakeToken";

        ResponseEntity<AuthResponseDTO> resp=authController.login(loginDto);

        ArgumentCaptor<UsernamePasswordAuthenticationToken> authTokenCaptor=ArgumentCaptor.forClass(UsernamePasswordAuthenticationToken.class);
        verify(authenticationManager).authenticate(authTokenCaptor.capture());
        UsernamePasswordAuthenticationToken capturedAuthToken=authTokenCaptor.getValue();

        assertEquals(loginDto.getUsername(),capturedAuthToken.getPrincipal());
        assertEquals(loginDto.getPassword(),capturedAuthToken.getCredentials());



    }

    @Test
    void registerAdmin() {
        RegisterDto registerDto =new RegisterDto();
        registerDto.setUsername("fakeuser");
        registerDto.setPassword("fakepassword");
        registerDto.setStudentName("fakename");

        when(passwordEncoder.encode(registerDto.getPassword())).thenReturn("password found!");
        Role userRole =new Role();
        userRole.setName("ADMIN");
        when(roleRepository.findByName("ADMIN")).thenReturn(Optional.of(userRole));

        ResponseEntity<String> resp=authController.registerAdmin(registerDto);

        assertEquals(HttpStatus.OK,resp.getStatusCode());
        assertEquals("Admin User registered successfully!",resp.getBody());

        ArgumentCaptor<UserEntity> captorUser= ArgumentCaptor.forClass(UserEntity.class);
        verify(userRepistory).save(captorUser.capture());
        UserEntity savedUser = captorUser.getValue();
        assertEquals("fakeuser",savedUser.getUsername());
        assertEquals("password found!",savedUser.getPassword());

    }

    @Test
    void register() {
        RegisterDto registerDto =new RegisterDto();
        registerDto.setUsername("fakeuser");
        registerDto.setPassword("fakepassword");
        registerDto.setStudentName("fakename");

        when(passwordEncoder.encode(registerDto.getPassword())).thenReturn("encodedPassword");
        Role userRole =new Role();
        userRole.setName("USER");
        when(roleRepository.findByName("USER")).thenReturn(Optional.of(userRole));

        ResponseEntity<String> resp=authController.register(registerDto);

        assertEquals(HttpStatus.OK,resp.getStatusCode());
        assertEquals("User registered successfully!",resp.getBody());

        ArgumentCaptor<Student> captorStudent= ArgumentCaptor.forClass(Student.class);
        verify(studentRepository).save(captorStudent.capture());
        Student savedStudent=captorStudent.getValue();
        assertEquals("fakename",savedStudent.getName());

        ArgumentCaptor<UserEntity> captorUser = ArgumentCaptor.forClass(UserEntity.class);
        verify(userRepistory).save(captorUser.capture());
        UserEntity savedUser =captorUser.getValue();
        assertEquals("fakeuser",savedUser.getUsername());
        assertEquals("encodedPassword",savedUser.getPassword());
        assertTrue(savedUser.getRoles().contains(userRole));
        assertEquals(savedStudent.getId(),savedUser.getStudentID());

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