package com.bluedemons2024.dolphintellect_backend.Account;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserEntityTest {

    private UserEntity userEntity;
    private UserEntity userEntity2;
    private Role role;
    private Role role2;
    private ArrayList<Role> roleList;


    @BeforeEach
    void setUp() {
        userEntity = new UserEntity("fakeusername","thisemail@email.com","fakepassword123","123456");
        userEntity2=new UserEntity();
        role = new Role();
        role2 = new Role();
        roleList = new ArrayList<>();

        role.setName("Student");
        role.setId(345L);

        role2.setName("Admin");
        role2.setId(350L);

        roleList.add(role);
        roleList.add(role2);

        userEntity.setPassword("fakepassword123");
        userEntity.setRoles(roleList);
        userEntity.setEmail("thisemail@email.com");
        userEntity.setId(450L);
        userEntity.setStudentID("123456");
        userEntity.setUsername("fakeusername");

        userEntity2.setPassword("fakepassword123");
        userEntity2.setRoles(roleList);
        userEntity2.setEmail("thisemail@email.com");
        userEntity2.setId(450L);
        userEntity2.setStudentID("123456");
        userEntity2.setUsername("fakeusername");

    }

    @Test
    void getRoles() {
        List<Role> roles = userEntity.getRoles();
        assertTrue(roles.contains(role));
        assertTrue(roles.contains(role2));


    }

    @Test
    void setRoles() {

        Role newrole = new Role();
        newrole.setName("newRoleName");
        newrole.setId(600L);

        ArrayList<Role> newRoleList = new ArrayList<>();
        newRoleList.add(newrole);

        userEntity.setRoles(newRoleList);

        assertEquals(newRoleList,userEntity.getRoles());
    }

    @Test
    void getId() {
        assertEquals(450L,userEntity.getId());
    }

    @Test
    void setId() {
        userEntity.setId(780L);
        assertEquals(780L,userEntity.getId());
    }

    @Test
    void getEmail() {
        assertEquals("thisemail@email.com",userEntity.getEmail());
    }

    @Test
    void setEmail() {
        userEntity.setEmail("newemail@email.com");
        assertEquals("newemail@email.com",userEntity.getEmail());
    }

    @Test
    void getPassword() {
        assertEquals("fakepassword123",userEntity.getPassword());
    }

    @Test
    void setPassword() {
        userEntity.setPassword("newpassword");
        assertEquals("newpassword",userEntity.getPassword());

    }

    @Test
    void getStudentID() {
        assertEquals("123456",userEntity.getStudentID());
    }

    @Test
    void setStudentID() {
        userEntity.setStudentID("09876");
        assertEquals("09876",userEntity.getStudentID());
    }

    @Test
    void getUsername() {
        assertEquals("fakeusername",userEntity.getUsername());

    }

    @Test
    void setUsername() {
        userEntity.setUsername("newusername");
        assertEquals("newusername",userEntity.getUsername());
    }
}