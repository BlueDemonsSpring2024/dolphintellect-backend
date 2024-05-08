package com.bluedemons2024.dolphintellect_backend.Student;

import com.bluedemons2024.dolphintellect_backend.Course.Course;
import com.bluedemons2024.dolphintellect_backend.EnrolledCourse.EnrolledCourse;
import com.bluedemons2024.dolphintellect_backend.EnrolledCourseWrapper.EnrolledCourseWrapper;
import com.bluedemons2024.dolphintellect_backend.GradeItem.GradeItem;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    private Student student;
    private EnrolledCourse enrolledCourse;
    private List<EnrolledCourse> enrolledCourses;
    private GradeItem gradeItem;
    private List<GradeItem> gradeItems;
    private Course course;


    @BeforeEach
    void setUp(){
        student = new Student("1","Fake Student");
        enrolledCourse = new EnrolledCourse();
        gradeItem = new GradeItem();
        course = new Course();
        enrolledCourses = new ArrayList<>();
        gradeItems = new ArrayList<>();

        course.setTitle("Data Structures 1");
        course.setDescription("This is a fake description");
        course.setNumber(1);
        course.setSubject("CSC300");

        gradeItem.setCourse(course);
        gradeItem.setId(245L);
        gradeItem.setName("fakeName");
        gradeItem.setScore(78.9);
        gradeItem.setWeight(50.0);


        enrolledCourse.setTerm("W23");
        enrolledCourse.setCourse(course);
        enrolledCourse.setFinalGrade("B+");
        enrolledCourse.setYear(2005);
        enrolledCourse.setCalculatedGrade(88.5);
        enrolledCourse.setCredits(4);


        student.setGpa(3.0);
        student.setGradeItems(gradeItems);
        student.isEnrolledIn(course,enrolledCourse);

    }

    @Test
    void getId() {
        assertEquals("1",student.getId());
    }

    @Test
    void isEnrolledIn() {
    }

    @Test
    void getGradeItems() {
    }

    @Test
    void setGradeItems() {
        assertEquals(1, student.getGradeItems().size());


    }

    @Test
    void setGradeItem() {


    }

    @Test
    void getGpa() {
        assertEquals(3.0,student.getGpa());
    }

    @Test
    void setGpa() {
        student.setGpa(4.0);
        assertEquals(4.0,student.getGpa());
    }

    @Test
    void setId() {
        student.setId("3");
        assertEquals("3",student.getId());
    }

    @Test
    void getName() {
        assertEquals("Fake Student",student.getName());
    }

    @Test
    void setName() {
        student.setName("New Name");
        assertEquals("New Name", student.getName());
    }

    @Test
    void getEnrolledCourses() {
        //NEED TO FIX
        assertEquals(1, student.getEnrolledCourses().size());
    }
}