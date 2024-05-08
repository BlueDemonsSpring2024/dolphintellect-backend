package com.bluedemons2024.dolphintellect_backend.GradeItemWrapper;

import com.bluedemons2024.dolphintellect_backend.GradeItem.GradeItem;

public class GradeItemWrapper {

    private GradeItem gradeItem;
    private String studentID;
    private String courseID;

    public GradeItem getGradeItem() {
        return gradeItem;
    }

    public void setGradeItem(GradeItem gradeItem) {
        this.gradeItem = gradeItem;
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
