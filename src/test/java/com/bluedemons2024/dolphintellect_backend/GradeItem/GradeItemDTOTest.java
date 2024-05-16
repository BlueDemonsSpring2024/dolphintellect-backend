package com.bluedemons2024.dolphintellect_backend.GradeItem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class GradeItemDTOTest {

    private GradeItemDTO gradeItemDTO;

    @BeforeEach
    void setup(){
        gradeItemDTO = new GradeItemDTO();

        gradeItemDTO.setWeight(Optional.of(25.0));
        gradeItemDTO.setName(Optional.of("Homework"));
        gradeItemDTO.setCourseID(Optional.of("242"));
        gradeItemDTO.setScore(Optional.of(98.0));
    }

    @Test
    void getCourseID() {
        assertEquals("242",gradeItemDTO.getCourseID().get());
    }

    @Test
    void setCourseID() {
        gradeItemDTO.setCourseID(Optional.of("243"));
        assertEquals("243",gradeItemDTO.getCourseID().get());
    }

    @Test
    void getName() {
        assertEquals("Homework",gradeItemDTO.getName().get());
    }

    @Test
    void setName() {
        gradeItemDTO.setName(Optional.of("Quizzes"));
        assertEquals("Quizzes",gradeItemDTO.getName().get());
    }

    @Test
    void getScore() {
        assertEquals(98.0,gradeItemDTO.getScore().get());
    }

    @Test
    void setScore() {
        gradeItemDTO.setScore(Optional.of(87.0));
        assertEquals(87.0,gradeItemDTO.getScore().get());
    }

    @Test
    void getWeight() {
        assertEquals(25.0,gradeItemDTO.getWeight().get());
    }

    @Test
    void setWeight() {
        gradeItemDTO.setWeight(Optional.of(15.0));
        assertEquals(15.0,gradeItemDTO.getWeight().get());
    }
}