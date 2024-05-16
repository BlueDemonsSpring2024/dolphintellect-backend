package com.bluedemons2024.dolphintellect_backend.Account;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.expression.spel.ast.LongLiteral;

import static org.junit.jupiter.api.Assertions.*;

class RoleTest {

    private Role role;


    @BeforeEach
    void setUp() {
        role = new Role();
        role.setId(345L);
        role.setName("Admin");

    }

    @Test
    void getName() {
        assertEquals("Admin",role.getName());
    }

    @Test
    void setName() {
        role.setName("Student");
        assertEquals("Student",role.getName());
    }

    @Test
    void setId() {
        role.setId(546L);
        assertEquals(546L,role.getId());
    }

    @Test
    void setId2() {
        Long newId = 400L;
        role.setId(newId);
        assertEquals(newId,role.getId());
    }

    @Test
    void getId() {
        assertEquals(345L,role.getId());
    }
}