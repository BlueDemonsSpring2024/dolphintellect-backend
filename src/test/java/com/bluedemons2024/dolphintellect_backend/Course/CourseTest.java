package com.bluedemons2024.dolphintellect_backend.Course;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {



    @Test
    void getId() {
        Course course = new Course();
        course.setSubject("Fake Subject");
        course.setSubject("Some Subject");
        course.setNumber(1);
        course.setTitle("Fake Title");
        assertNull(course.getId());

    }

    @Test
    void getSubject() {
        Course course = new Course();
        course.setSubject("Fake Subject");
        assertEquals("Fake Subject",course.getSubject());
    }

    @Test
    void setSubject() {
        Course course = new Course();
        course.setSubject("Some Subject");
        assertEquals("Some Subject",course.getSubject());

    }

    @Test
    void getNumber() {
        Course course = new Course();
        course.setNumber(1);
        assertEquals(1,course.getNumber());

    }

    @Test
    void setNumber() {
        Course course = new Course();
        course.setNumber(100);
        assertEquals(100,course.getNumber());
    }

    @Test
    void getTitle() {
        Course course = new Course();
        course.setTitle("Fake Title");
        assertEquals("Fake Title",course.getTitle());
    }

    @Test
    void setTitle() {
        Course course = new Course();
        course.setTitle("Test Title");
        assertEquals("Test Title",course.getTitle());
    }

    @Test
    void getDescription() {
        Course course = new Course();
        course.setDescription("This is a fake desc");
        assertEquals("This is a fake desc",course.getDescription());
    }

    @Test
    void setDescription() {
        Course course = new Course();
        course.setDescription("This is a test desc");
        assertEquals("This is a test desc",course.getDescription());
    }
}