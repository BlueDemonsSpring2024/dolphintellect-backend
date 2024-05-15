package com.bluedemons2024.dolphintellect_backend.EnrolledCourseWrapper;

import java.util.Optional;

public class EnrolledCourseDTO {
    private Optional<String> studentID;
    private Optional<String> courseID;

    private Optional<String> term;
    private Optional<Integer> year;
    private Optional<Integer> credits;
    private Optional<String> finalGrade;


    public Optional<String> getStudentID() {
        return studentID;
    }

    public void setStudentID(Optional<String> studentID) {
        this.studentID = studentID;
    }

    public Optional<String> getCourseID() {
        return courseID;
    }

    public void setCourseID(Optional<String> courseID) {
        this.courseID = courseID;
    }

    public Optional<String> getTerm() {
        return term;
    }

    public void setTerm(Optional<String> term) {
        this.term = term;
    }

    public Optional<Integer> getYear() {
        return year;
    }

    public void setYear(Optional<Integer> year) {
        this.year = year;
    }

    public Optional<Integer> getCredits() {
        return credits;
    }

    public void setCredits(Optional<Integer> credits) {
        this.credits = credits;
    }

    public Optional<String> getFinalGrade() {
        return finalGrade;
    }

    public void setFinalGrade(Optional<String> finalGrade) {
        this.finalGrade = finalGrade;
    }
}
