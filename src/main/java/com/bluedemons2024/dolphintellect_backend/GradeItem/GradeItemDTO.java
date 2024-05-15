package com.bluedemons2024.dolphintellect_backend.GradeItem;

import java.util.Optional;

public class GradeItemDTO {
    private Optional<String> courseID;
    private Optional<String> name;
    private Optional<Double> score;
    private Optional<Double> weight;


    public Optional<String> getCourseID() {
        return courseID;
    }

    public void setCourseID(Optional<String> courseID) {
        this.courseID = courseID;
    }

    public Optional<String> getName() {
        return name;
    }

    public void setName(Optional<String> name) {
        this.name = name;
    }

    public Optional<Double> getScore() {
        return score;
    }

    public void setScore(Optional<Double> score) {
        this.score = score;
    }

    public Optional<Double> getWeight() {
        return weight;
    }

    public void setWeight(Optional<Double> weight) {
        this.weight = weight;
    }
}
