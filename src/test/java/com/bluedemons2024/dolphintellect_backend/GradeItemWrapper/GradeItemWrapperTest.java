//package com.bluedemons2024.dolphintellect_backend.GradeItemWrapper;
//
//import com.bluedemons2024.dolphintellect_backend.Course.Course;
//import com.bluedemons2024.dolphintellect_backend.GradeItem.GradeItem;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class GradeItemWrapperTest {
//    private GradeItemWrapper gradeItemWrapper;
//    private GradeItem gradeItem;
//    private Course course;
//
//    @BeforeEach
//    void setUp() {
//        gradeItemWrapper=new GradeItemWrapper();
//        gradeItem=new GradeItem();
//        course=new Course();
//
//        gradeItem.setCourse(course);
//        gradeItem.setId(245L);
//        gradeItem.setName("fakeName");
//        gradeItem.setScore(78.9);
//        gradeItem.setWeight(50.0);
//
//        gradeItemWrapper.setGradeItem(gradeItem);
//        gradeItemWrapper.setCourseID("300");
//        gradeItemWrapper.setStudentID("24543");
//    }
//
//    @Test
//    void getGradeItem() {
//        assertEquals(gradeItem,gradeItemWrapper.getGradeItem());
//    }
//
//    @Test
//    void setGradeItem() {
//        GradeItem newGradeItem = new GradeItem();
//        Course newCourse = new Course();
//
//        gradeItem.setCourse(newCourse);
//        gradeItem.setId(400L);
//        gradeItem.setName("newFakeName");
//        gradeItem.setScore(55.6);
//        gradeItem.setWeight(15);
//
//        gradeItemWrapper.setGradeItem(newGradeItem);
//        assertEquals(newGradeItem,gradeItemWrapper.getGradeItem());
//    }
//
//    @Test
//    void getStudentID() {
//        assertEquals("24543",gradeItemWrapper.getStudentID());
//    }
//
//    @Test
//    void setStudentID() {
//        gradeItemWrapper.setStudentID("56432");
//        assertEquals("56432",gradeItemWrapper.getStudentID());
//    }
//
//    @Test
//    void getCourseID() {
//        assertEquals("300",gradeItemWrapper.getCourseID());
//    }
//
//    @Test
//    void setCourseID() {
//        gradeItemWrapper.setCourseID("242");
//        assertEquals("242",gradeItemWrapper.getCourseID());
//    }
//}