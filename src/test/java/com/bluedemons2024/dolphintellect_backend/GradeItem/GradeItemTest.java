package com.bluedemons2024.dolphintellect_backend.GradeItem;

import com.bluedemons2024.dolphintellect_backend.Course.Course;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GradeItemTest {
    GradeItem gradeItem;
    Course course;

    @BeforeEach
    void setUp() {
        gradeItem=new GradeItem();
        course=new Course();

        gradeItem.setCourse(course);
        gradeItem.setId(245L);
        gradeItem.setName("fakeName");
        gradeItem.setScore(78.9);
        gradeItem.setWeight(50.0);
    }

    @Test
    void getCourse() {
        assertEquals(course,gradeItem.getCourse());
    }

    @Test
    void setCourse() {
        Course newCourse=new Course();
        gradeItem.setCourse(newCourse);
        assertEquals(newCourse,gradeItem.getCourse());

    }

    @Test
    void getId() {
        assertEquals(245L,gradeItem.getId());
    }

    @Test
    void setId() {
        gradeItem.setId(300L);
        assertEquals(300L,gradeItem.getId());
    }

    @Test
    void getWeight() {
        assertEquals(50.0,gradeItem.getWeight());
    }

    @Test
    void setWeight() {
        gradeItem.setWeight(35.5);
        assertEquals(35.5,gradeItem.getWeight());

    }

    @Test
    void getScore() {
        assertEquals(78.9,gradeItem.getScore());
    }

    @Test
    void setScore() {
        gradeItem.setScore(90.6);
        assertEquals(90.6,gradeItem.getScore());
    }

    @Test
    void getName() {
        assertEquals("fakeName",gradeItem.getName());
    }

    @Test
    void setName() {
        gradeItem.setName("newName");
        assertEquals("newName",gradeItem.getName());
    }
}