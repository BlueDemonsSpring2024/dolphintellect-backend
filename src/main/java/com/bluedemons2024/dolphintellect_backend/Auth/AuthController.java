package com.bluedemons2024.dolphintellect_backend.Auth;


import com.bluedemons2024.dolphintellect_backend.Account.Role;
import com.bluedemons2024.dolphintellect_backend.Account.RoleRepository;
import com.bluedemons2024.dolphintellect_backend.Account.UserEntity;
import com.bluedemons2024.dolphintellect_backend.Account.UserRepistory;
import com.bluedemons2024.dolphintellect_backend.Student.Student;
import com.bluedemons2024.dolphintellect_backend.Student.StudentRepository;
import com.bluedemons2024.dolphintellect_backend.config.JWTGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final StudentRepository studentRepository;
    private AuthenticationManager authenticationManager;
    private UserRepistory userRepistory;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JWTGenerator jwtGenerator;




    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserRepistory userRepistory, RoleRepository roleRepository, PasswordEncoder passwordEncoder, JWTGenerator jwtGenerator, StudentRepository studentRepository) {
        this.authenticationManager = authenticationManager;
        this.userRepistory = userRepistory;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
        this.studentRepository = studentRepository;
    }


    @PostMapping("login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new ResponseEntity<>(new AuthResponseDTO(token), HttpStatus.OK);
    }



    //Used to Register A Student
    @PostMapping("registerAdmin")
    public ResponseEntity<String> registerAdmin(@RequestBody RegisterDto registerDto) {
        if(userRepistory.existsByUsername(registerDto.getUsername())) {
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        }

        UserEntity user = new UserEntity();
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        user.setStudentID(null);

        Role roles = roleRepository.findByName("ADMIN").get();
        user.setRoles(Collections.singletonList(roles));

        userRepistory.save(user);

        return new ResponseEntity<>("Admin User registered successfully!", HttpStatus.OK);
    }






    //Used to Register A Student
    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        if(userRepistory.existsByUsername(registerDto.getUsername())) {
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        }

        UserEntity user = new UserEntity();
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Student newStudent = new Student();
        newStudent.setName(registerDto.getStudentName());
        studentRepository.save(newStudent);
        user.setStudentID(newStudent.getId());

        Role roles = roleRepository.findByName("USER").get();
        user.setRoles(Collections.singletonList(roles));

        userRepistory.save(user);

        return new ResponseEntity<>("User registered successfully!", HttpStatus.OK);
    }


    //Delete a student
    @DeleteMapping("deleteStudent/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable long id) {

        if (!userRepistory.existsById(id)){
            return new ResponseEntity<>("User does not exist", HttpStatus.NOT_FOUND);
        }

        //get the user
        UserEntity user = userRepistory.findById(id).get();

        //get the student id
        String studentID = user.getStudentID();

        //delete the student id from neo4j
        studentRepository.deleteById(studentID);

        // delete the user roles
        userRepistory.deleteAllById(Collections.singleton(id));

        //delete the user from accounts
        userRepistory.deleteById(id);
        return new ResponseEntity<>("User Deleted successfully!", HttpStatus.OK);
    }


}
