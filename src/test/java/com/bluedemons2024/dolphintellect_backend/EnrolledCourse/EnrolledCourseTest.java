package com.bluedemons2024.dolphintellect_backend.EnrolledCourse;

import com.bluedemons2024.dolphintellect_backend.Course.Course;
import com.bluedemons2024.dolphintellect_backend.GradeItem.GradeItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.core.schema.GeneratedValue;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class EnrolledCourseTest {

    @Autowired
    private EnrolledCourseRepository enrolledCourseRepository;

    private EnrolledCourse enrolledCourse;
    private Course course;
    private GradeItem gradeItem;
    private ArrayList<GradeItem> gradeItems;
    @GeneratedValue
    private long id ;

    @BeforeEach
    void setup(){
        enrolledCourse=new EnrolledCourse();
        course= new Course();
        gradeItem= new GradeItem();
        gradeItems= new ArrayList<>();

        course.setTitle("Data Structures");
        course.setSubject("Computer Science");
        course.setNumber(242);
        course.setDescription("This is a description");

        gradeItem.setId(350L);
        gradeItem.setWeight(30.0);
        gradeItem.setCourse(course);
        gradeItem.setName("Homework");
        gradeItem.setScore(90.0);
        gradeItems.add(gradeItem);



        enrolledCourse.setYear(2020);
        enrolledCourse.setFinalGrade("A+");
        enrolledCourse.setCredits(4);
        enrolledCourse.setCourse(course);
        enrolledCourse.setTerm("W23");
        enrolledCourse.setGradeItems(gradeItems);


    }


    @Test
    void getCalculatedGrade() {

        enrolledCourse.setCalculatedGrade(80.5);

        assertEquals(80.5,enrolledCourse.getCalculatedGrade());

    }

    @Test
    void setCalculatedGrade() {

        enrolledCourse.setCalculatedGrade(75.6);
        assertEquals(75.6,enrolledCourse.getCalculatedGrade());
    }

    @Test
    void setYear() {
        enrolledCourse.setYear(2022);
        assertEquals(2022,enrolledCourse.getYear());
    }

    @Test
    void setCredits() {
        enrolledCourse.setCredits(6);
        assertEquals(6,enrolledCourse.getCredits());

    }

    @Test
    void setFinalGrade() {
        enrolledCourse.setFinalGrade("B-");
        assertEquals("B-",enrolledCourse.getFinalGrade());
    }

    @Test
    void setCourse() {
        Course testCourse = new Course();
        testCourse.setTitle("Data Structures 2");
        testCourse.setSubject("Computer Science");
        testCourse.setNumber(243);
        testCourse.setDescription("This is another description");

        enrolledCourse.setCourse(testCourse);
        assertEquals(testCourse,enrolledCourse.getCourse());

    }

    @Test
    void getTerm() {
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
        assertEquals(course,enrolledCourse.getCourse());

    }

    @Test
    void getId() {
        assertNull(enrolledCourse.getId());
    }

    @Test
    void getYear() {

        assertEquals(2020,enrolledCourse.getYear());
    }

    @Test
    void getCredits() {

        assertEquals(4,enrolledCourse.getCredits());
    }

    @Test
    void getFinalGrade() {
        assertEquals("A+",enrolledCourse.getFinalGrade());

    }

    @Test
    void calculateGrade(){
        assertEquals(90,enrolledCourse.calculateCourseGrade());
    }

    @Test
    void getGradeItems(){
        assertEquals(gradeItems,enrolledCourse.getGradeItems());

    }
}