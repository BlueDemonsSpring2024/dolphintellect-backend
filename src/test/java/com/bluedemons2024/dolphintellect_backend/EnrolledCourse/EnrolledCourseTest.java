package com.bluedemons2024.dolphintellect_backend.EnrolledCourse;

import com.bluedemons2024.dolphintellect_backend.Course.Course;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnrolledCourseTest {


    @Test
    void getCalculatedGrade() {
        EnrolledCourse enrolledCourse = new EnrolledCourse();
        enrolledCourse.setCalculatedGrade(80.5);
        assertEquals(80.5,enrolledCourse.getCalculatedGrade());

    }

    @Test
    void setCalculatedGrade() {
        EnrolledCourse enrolledCourse = new EnrolledCourse();
        enrolledCourse.setCalculatedGrade(75.6);
        assertEquals(75.6,enrolledCourse.getCalculatedGrade());
    }

    @Test
    void setYear() {
        EnrolledCourse enrolledCourse = new EnrolledCourse();
        enrolledCourse.setYear(2020);
        assertEquals(2020,enrolledCourse.getYear());
    }

    @Test
    void setCredits() {
        EnrolledCourse enrolledCourse = new EnrolledCourse();
        enrolledCourse.setCredits(4);
        assertEquals(4,enrolledCourse.getCredits());

    }

    @Test
    void setFinalGrade() {
        EnrolledCourse enrolledCourse = new EnrolledCourse();
        enrolledCourse.setFinalGrade("A");
        assertEquals("A",enrolledCourse.getFinalGrade());
    }

    @Test
    void setCourse() {
        EnrolledCourse enrolledCourse = new EnrolledCourse();
        Course testCourse = new Course();
        enrolledCourse.setCourse(testCourse);
        assertEquals(testCourse,enrolledCourse.getCourse());

    }

    @Test
    void getTerm() {
        EnrolledCourse enrolledCourse = new EnrolledCourse();
        enrolledCourse.setTerm("W23");
        assertEquals("W23",enrolledCourse.getTerm());
    }

    @Test
    void setTerm() {
        EnrolledCourse enrolledCourse = new EnrolledCourse();
        enrolledCourse.setTerm("W14");
        assertEquals("W14",enrolledCourse.getTerm());
    }

    @Test
    void getCourse() {
        EnrolledCourse enrolledCourse = new EnrolledCourse();
        Course testCourse = new Course();
        enrolledCourse.setCourse(testCourse);
        assertEquals(testCourse,enrolledCourse.getCourse());

    }

    @Test
    void getId() {
    }

    @Test
    void getYear() {
        EnrolledCourse enrolledCourse = new EnrolledCourse();
        enrolledCourse.setYear(2015);
        assertEquals(2015,enrolledCourse.getYear());
    }

    @Test
    void getCredits() {
        EnrolledCourse enrolledCourse = new EnrolledCourse();
        enrolledCourse.setCredits(2);
        assertEquals(2,enrolledCourse.getCredits());
    }

    @Test
    void getFinalGrade() {
        EnrolledCourse enrolledCourse = new EnrolledCourse();
        enrolledCourse.setFinalGrade("C-");
        assertEquals("C-",enrolledCourse.getFinalGrade());

    }
}