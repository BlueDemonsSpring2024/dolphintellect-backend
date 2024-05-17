package com.bluedemons2024.dolphintellect_backend.Course;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CourseControllerTest {

    @Mock
    private CourseRepository courseRepository;
    @InjectMocks
    private CourseController courseController;
    private Course course;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
        course=new Course();
        course.setDescription("this is a fake description");
        course.setTitle("fake title");
        course.setNumber(234);
        course.setSubject("Computer Science");
    }

    @Test
    void findAll() {
        when(courseRepository.findAll()).thenReturn(Collections.singletonList(course));
        List<Course> courses= courseController.findAll();
        assertEquals(1,courses.size());
        verify(courseRepository,times(1)).findAll();
    }

    @Test
    void findCourseBySubject() {
        when(courseRepository.findCoursesBySubject("Computer Science")).thenReturn(Arrays.asList(course));
        List<Course> courses=courseController.findCourseBySubject("Computer Science");
        assertEquals(1,courses.size());
        verify(courseRepository,times(1)).findCoursesBySubject("Computer Science");
        System.out.println(courses);

    }

    @Test
    void findCourseByNumber() {
        when(courseRepository.findCoursesByNumber(234)).thenReturn(Arrays.asList(course));
        List<Course> courses=courseController.findCourseByNumber(234);
        assertEquals(1,courses.size());
        verify(courseRepository,times(1)).findCoursesByNumber(234);

    }

    @Test
    void findCourseBySubjectAndNumber() {
        when(courseRepository.findCourseBySubjectAndNumber("Computer Science",234)).thenReturn(course);
        Course found =courseController.findCourseBySubjectAndNumber("Computer Science",234);
        assertEquals(found,course);
    }

    @Test
    void getCourseByID() {
    }

    @Test
    void addCourse() {
        courseController.addCourse(course);
        verify(courseRepository,times(1)).save(course);
    }

    @Test
    void updateCourse() {
    }

    @Test
    void deleteCourse() {
    }
}