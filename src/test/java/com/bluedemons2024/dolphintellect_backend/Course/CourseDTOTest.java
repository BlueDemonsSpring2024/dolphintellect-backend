package com.bluedemons2024.dolphintellect_backend.Course;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CourseDTOTest {

    private CourseDTO courseDTO;

    @BeforeEach
    void setup(){
        courseDTO = new CourseDTO();

        courseDTO.setTitle(Optional.of("Comp Sci Class"));
        courseDTO.setCourseID(Optional.of("242"));
        courseDTO.setDescription(Optional.of("fake desc"));
        courseDTO.setSubject(Optional.of("Comp Sci"));
        courseDTO.setNumber(Optional.of(242));

    }

    @Test
    void getCourseID() {
        assertEquals("242",courseDTO.getCourseID().get());
    }

    @Test
    void setCourseID() {
        courseDTO.setCourseID(Optional.of("243"));
        assertEquals("243",courseDTO.getCourseID().get());
    }

    @Test
    void getSubject() {
        assertEquals("Comp Sci",courseDTO.getSubject().get());
    }

    @Test
    void setSubject() {
        courseDTO.setSubject(Optional.of("Math"));
        assertEquals("Math",courseDTO.getSubject().get());
    }

    @Test
    void getNumber() {
        assertEquals(242,courseDTO.getNumber().get());
    }

    @Test
    void setNumber() {
        courseDTO.setNumber(Optional.of(243));
        assertEquals(243,courseDTO.getNumber().get());
    }

    @Test
    void getTitle() {
        assertEquals("Comp Sci Class",courseDTO.getTitle().get());
    }

    @Test
    void setTitle() {
        courseDTO.setTitle(Optional.of("Math Class"));
        assertEquals("Math Class",courseDTO.getTitle().get());
    }

    @Test
    void getDescription() {
        assertEquals("fake desc",courseDTO.getDescription().get());
    }

    @Test
    void setDescription() {
        courseDTO.setDescription(Optional.of("new desc"));
        assertEquals("new desc",courseDTO.getDescription().get());
    }
}