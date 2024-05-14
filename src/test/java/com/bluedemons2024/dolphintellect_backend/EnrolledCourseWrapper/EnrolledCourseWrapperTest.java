package com.bluedemons2024.dolphintellect_backend.EnrolledCourseWrapper;

import com.bluedemons2024.dolphintellect_backend.Course.Course;
import com.bluedemons2024.dolphintellect_backend.EnrolledCourse.EnrolledCourse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnrolledCourseWrapperTest {

    private EnrolledCourse enrolledCourse;
    private Course course;
    private EnrolledCourseWrapper enrolledCourseWrapper;

    @BeforeEach
    void setUp() {
        enrolledCourse = new EnrolledCourse();
        course = new Course();
        enrolledCourseWrapper=new EnrolledCourseWrapper();
        enrolledCourse.setTerm("W23");
        enrolledCourse.setCourse(course);
        enrolledCourse.setFinalGrade("B+");
        enrolledCourse.setYear(2005);
        enrolledCourse.setCalculatedGrade(88.5);
        enrolledCourse.setCredits(4);

        enrolledCourseWrapper.setEnrolledCourse(enrolledCourse);
        enrolledCourseWrapper.setCourseID("24");
        enrolledCourseWrapper.setStudentID("12");
    }

    @Test
    void getEnrolledCourse() {

        assertEquals(enrolledCourse,enrolledCourseWrapper.getEnrolledCourse());

    }

    @Test
    void setEnrolledCourse() {
        EnrolledCourse newEnrolledCourse = new EnrolledCourse();
        enrolledCourseWrapper.setEnrolledCourse(newEnrolledCourse);
        assertEquals(newEnrolledCourse,enrolledCourseWrapper.getEnrolledCourse());
    }

    @Test
    void getStudentID() {
        assertEquals("12",enrolledCourseWrapper.getStudentID());
    }

    @Test
    void setStudentID() {
        enrolledCourseWrapper.setStudentID("44");
        assertEquals("44",enrolledCourseWrapper.getStudentID());
    }

    @Test
    void getCourseID() {
        assertEquals("24",enrolledCourseWrapper.getCourseID());
    }

    @Test
    void setCourseID() {
        enrolledCourseWrapper.setCourseID("55");
        assertEquals("55",enrolledCourseWrapper.getCourseID());
    }
}