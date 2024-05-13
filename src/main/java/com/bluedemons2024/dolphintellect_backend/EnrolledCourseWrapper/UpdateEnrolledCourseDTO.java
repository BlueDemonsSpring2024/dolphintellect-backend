package com.bluedemons2024.dolphintellect_backend.EnrolledCourseWrapper;

import java.util.Optional;

public class UpdateEnrolledCourseDTO {

    private Long id;
    private Optional<String> finalGrade;
    private Optional<String> term;
    private Optional<Integer> year;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Optional<String> getFinalGrade() {
        return finalGrade;
    }

    public void setFinalGrade(Optional<String> finalGrade) {
        this.finalGrade = finalGrade;
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
}
