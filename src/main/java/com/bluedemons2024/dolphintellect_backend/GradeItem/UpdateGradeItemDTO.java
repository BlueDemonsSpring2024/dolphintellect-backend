package com.bluedemons2024.dolphintellect_backend.GradeItem;

import java.util.Optional;

public class UpdateGradeItemDTO {
    private Long id;
    private Optional<String> name;
    private Optional<Double> score;
    private Optional<Double> weight;

//    private Optional<Double>


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    //    public void setScore(Double score) {
//        this.score = score;
//    }

    public Optional<Double> getWeight() {
        return weight;
    }

//    public void setWeight(Double weight) {
//        this.weight = weight;
//    }


    public void setWeight(Optional<Double> weight) {
        this.weight = weight;
    }
}
