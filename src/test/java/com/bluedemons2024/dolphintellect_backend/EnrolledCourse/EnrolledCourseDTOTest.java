package com.bluedemons2024.dolphintellect_backend.EnrolledCourse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class EnrolledCourseDTOTest {

    private EnrolledCourseDTO enrolledCourseDTO;

    @BeforeEach
    void setup(){
        enrolledCourseDTO = new EnrolledCourseDTO();

        enrolledCourseDTO.setCourseID(Optional.of("242"));
        enrolledCourseDTO.setCredits(Optional.of(4));
        enrolledCourseDTO.setYear(Optional.of(2020));
        enrolledCourseDTO.setTerm(Optional.of("Winter"));
        enrolledCourseDTO.setStudentID(Optional.of("123456"));
        enrolledCourseDTO.setFinalGrade(Optional.of("A+"));
    }

    @Test
    void getStudentID() {
        assertEquals("123456",enrolledCourseDTO.getStudentID().get());
    }

    @Test
    void setStudentID() {
        enrolledCourseDTO.setStudentID(Optional.of("098765"));
        assertEquals("098765",enrolledCourseDTO.getStudentID().get());
    }

    @Test
    void getCourseID() {
        assertEquals("242",enrolledCourseDTO.getCourseID().get());
    }

    @Test
    void setCourseID() {
        enrolledCourseDTO.setCourseID(Optional.of("243"));
        assertEquals("243",enrolledCourseDTO.getCourseID().get());
    }

    @Test
    void getTerm() {
        assertEquals("Winter",enrolledCourseDTO.getTerm().get());
    }

    @Test
    void setTerm() {
        enrolledCourseDTO.setTerm(Optional.of("Spring"));
        assertEquals("Spring",enrolledCourseDTO.getTerm().get());
    }

    @Test
    void getYear() {
        assertEquals(2020,enrolledCourseDTO.getYear().get());
    }

    @Test
    void setYear() {
        enrolledCourseDTO.setYear(Optional.of(2023));
        assertEquals(2023,enrolledCourseDTO.getYear().get());
    }

    @Test
    void getCredits() {
        assertEquals(4,enrolledCourseDTO.getCredits().get());
    }

    @Test
    void setCredits() {
        enrolledCourseDTO.setCredits(Optional.of(6));
        assertEquals(6,enrolledCourseDTO.getCredits().get());
    }

    @Test
    void getFinalGrade() {
        assertEquals("A+",enrolledCourseDTO.getFinalGrade().get());
    }

    @Test
    void setFinalGrade() {
        enrolledCourseDTO.setFinalGrade(Optional.of("B+"));
        assertEquals("B+",enrolledCourseDTO.getFinalGrade().get());
    }
}