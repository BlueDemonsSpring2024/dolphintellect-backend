package com.bluedemons2024.dolphintellect_backend.EnrolledCourseWrapper;

import com.bluedemons2024.dolphintellect_backend.GradeItem.GradeItem;

import java.util.List;

public class EnrolledCourseUpdateDTO {
    private Long enrolledCourseId;
    private String term;
    private int year;
    private int credits;
    private String finalGrade;
    private double calculatedGrade;

    private List<GradeItem> gradeItems;


    public Long getEnrolledCourseId() {
        return enrolledCourseId;
    }

    public void setEnrolledCourseId(Long enrolledCourseId) {
        this.enrolledCourseId = enrolledCourseId;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getFinalGrade() {
        return finalGrade;
    }

    public void setFinalGrade(String finalGrade) {
        this.finalGrade = finalGrade;
    }

    public double getCalculatedGrade() {
        return calculatedGrade;
    }

    public void setCalculatedGrade(double calculatedGrade) {
        this.calculatedGrade = calculatedGrade;
    }

    public List<GradeItem> getGradeItems() {
        return gradeItems;
    }

    public void setGradeItems(List<GradeItem> gradeItems) {
        this.gradeItems = gradeItems;
    }
}
