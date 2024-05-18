package com.bluedemons2024.dolphintellect_backend.Student;

import com.bluedemons2024.dolphintellect_backend.Course.Course;
import com.bluedemons2024.dolphintellect_backend.EnrolledCourse.EnrolledCourse;
import com.bluedemons2024.dolphintellect_backend.GradeItem.GradeItem;
import com.bluedemons2024.dolphintellect_backend.GradeItem.GradeItemDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    private Student student;
    private EnrolledCourse enrolledCourse;
    private ArrayList<EnrolledCourse> enrolledCourses;
    private GradeItem gradeItem;
    private ArrayList<GradeItem> gradeItems;
    private Course course;

    @Test
    void isEnrolledIn() {
        student = new Student("1","fakename");
        enrolledCourse = new EnrolledCourse();
        //enrolledCourses = new ArrayList<>();
        gradeItems = new ArrayList<>();
        gradeItem = new GradeItem();
        course = new Course();

        course.setDescription("fake desc");
        course.setNumber(242);
        course.setSubject("Computer Science");
        course.setTitle("Comp Sci 1");

        gradeItem.setName("Homework");
        gradeItem.setScore(90.5);
        gradeItem.setWeight(15.0);
        gradeItem.setCourse(course);
        gradeItem.setId(350L);

        gradeItems.add(gradeItem);

        enrolledCourse.setTerm("W23");
        enrolledCourse.setCourse(course);
        enrolledCourse.setFinalGrade("B+");
        enrolledCourse.setYear(2005);
        enrolledCourse.setCalculatedGrade(88.5);
        enrolledCourse.setCredits(4);
        enrolledCourse.setGradeItems(gradeItems);
        enrolledCourses = new ArrayList<>();
        System.out.println(enrolledCourse);
        //enrolledCourses.add(enrolledCourse);
        System.out.println(enrolledCourses);
        System.out.println(gradeItems);
        student.setGradeItems(gradeItems);
        student.setGradeItem(gradeItem);
        //student.isEnrolledIn(enrolledCourse);
        //assertEquals(1,enrolledCourses.size());

    }

    @Test
    void setGradeItem() {
        student = new Student("1","fakename");
        gradeItems = new ArrayList<>();
        gradeItem = new GradeItem();
        course = new Course();

        course.setDescription("fake desc");
        course.setNumber(242);
        course.setSubject("Computer Science");
        course.setTitle("Comp Sci 1");

        gradeItem.setName("Homework");
        gradeItem.setScore(90.5);
        gradeItem.setWeight(15.0);
        gradeItem.setCourse(course);
        gradeItem.setId(350L);

        gradeItems.add(gradeItem);


        student.setGradeItems(gradeItems);
        student.setGradeItem(gradeItem);
        assertEquals(gradeItems,student.getGradeItems());

    }

    @Test
    void getGpa() {
        student = new Student("1","fakename");
        gradeItems = new ArrayList<>();
        gradeItem = new GradeItem();
        course = new Course();

        course.setDescription("fake desc");
        course.setNumber(242);
        course.setSubject("Computer Science");
        course.setTitle("Comp Sci 1");

        gradeItem.setName("Homework");
        gradeItem.setScore(90.5);
        gradeItem.setWeight(15.0);
        gradeItem.setCourse(course);
        gradeItem.setId(350L);

        gradeItems.add(gradeItem);


        student.setGradeItems(gradeItems);
        student.setGradeItem(gradeItem);

        assertEquals(4.0,student.getGpa());
    }

    @Test
    void setId(){
        student=new Student("1","fakename");
        student.setId("123445");
        assertEquals("123445",student.getId());
    }

    @Test
    void getId(){
        student=new Student("1","fakename");
        assertEquals("1",student.getId());

    }
}
