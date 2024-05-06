package com.bluedemons2024.dolphintellect_backend.EnrolledCourseWrapper;

import com.bluedemons2024.dolphintellect_backend.EnrolledCourse.EnrolledCourse;

public class EnrolledCourseWrapper {

    private EnrolledCourse enrolledCourse;
    private String studentID;
    private String courseID;

    public EnrolledCourse getEnrolledCourse() {
        return enrolledCourse;
    }

    public void setEnrolledCourse(EnrolledCourse enrolledCourse) {
        this.enrolledCourse = enrolledCourse;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }
}
