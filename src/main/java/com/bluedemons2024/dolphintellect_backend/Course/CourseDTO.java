package com.bluedemons2024.dolphintellect_backend.Course;

import java.util.Optional;

public class CourseDTO {
    private Optional<String> courseID;
    private Optional<String> subject;
    private Optional<Integer> number;
    private Optional<String> title;
    private Optional<String> description;


    public Optional<String> getCourseID() {
        return courseID;
    }

    public void setCourseID(Optional<String> courseID) {
        this.courseID = courseID;
    }

    public Optional<String> getSubject() {
        return subject;
    }

    public void setSubject(Optional<String> subject) {
        this.subject = subject;
    }

    public Optional<Integer> getNumber() {
        return number;
    }

    public void setNumber(Optional<Integer> number) {
        this.number = number;
    }

    public Optional<String> getTitle() {
        return title;
    }

    public void setTitle(Optional<String> title) {
        this.title = title;
    }

    public Optional<String> getDescription() {
        return description;
    }

    public void setDescription(Optional<String> description) {
        this.description = description;
    }
}
