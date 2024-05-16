package com.bluedemons2024.dolphintellect_backend.EnrolledCourse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UpdateEnrolledCourseDTOTest {

    private UpdateEnrolledCourseDTO updateEnrolledCourseDTO;

    @BeforeEach
    void setup(){
        updateEnrolledCourseDTO = new UpdateEnrolledCourseDTO();

        updateEnrolledCourseDTO.setId(345L);
        updateEnrolledCourseDTO.setTerm(Optional.of("Winter"));
        updateEnrolledCourseDTO.setYear(Optional.of(2020));
        updateEnrolledCourseDTO.setFinalGrade(Optional.of("A+"));


    }

    @Test
    void getId() {
        assertEquals(345L,updateEnrolledCourseDTO.getId());
    }

    @Test
    void setId() {
        updateEnrolledCourseDTO.setId(500L);
        assertEquals(500L,updateEnrolledCourseDTO.getId());
    }

    @Test
    void getFinalGrade() {
        assertEquals("A+",updateEnrolledCourseDTO.getFinalGrade().get());
    }

    @Test
    void setFinalGrade() {
        updateEnrolledCourseDTO.setFinalGrade(Optional.of("C+"));
        assertEquals("C+",updateEnrolledCourseDTO.getFinalGrade().get());
    }

    @Test
    void getTerm() {
        assertEquals("Winter",updateEnrolledCourseDTO.getTerm().get());
    }

    @Test
    void setTerm() {
        updateEnrolledCourseDTO.setTerm(Optional.of("Spring"));
        assertEquals("Spring",updateEnrolledCourseDTO.getTerm().get());
    }

    @Test
    void getYear() {
        assertEquals(2020,updateEnrolledCourseDTO.getYear().get());
    }

    @Test
    void setYear() {
        updateEnrolledCourseDTO.setYear(Optional.of(2023));
        assertEquals(2023,updateEnrolledCourseDTO.getYear().get());
    }
}