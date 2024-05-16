package com.bluedemons2024.dolphintellect_backend.GradeItem;

import com.bluedemons2024.dolphintellect_backend.EnrolledCourse.UpdateEnrolledCourseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UpdateGradeItemDTOTest {

    private UpdateGradeItemDTO updateGradeItemDTO;

    @BeforeEach
    void setup(){
        updateGradeItemDTO = new UpdateGradeItemDTO();

        updateGradeItemDTO.setId(345L);
        updateGradeItemDTO.setName(Optional.of("Homework"));
        updateGradeItemDTO.setScore(Optional.of(98.0));
        updateGradeItemDTO.setWeight(Optional.of(25.0));
    }




    @Test
    void getId() {
        assertEquals(345L,updateGradeItemDTO.getId());
    }



    @Test
    void setId() {
        updateGradeItemDTO.setId(500L);
        assertEquals(500L,updateGradeItemDTO.getId());
    }


    @Test
    void getName() {
        assertEquals("Homework",updateGradeItemDTO.getName().get());

    }

    @Test
    void setName() {
        updateGradeItemDTO.setName(Optional.of("Quizzes"));
        assertEquals("Quizzes",updateGradeItemDTO.getName().get());
    }

    @Test
    void getScore() {
        assertEquals(98.0,updateGradeItemDTO.getScore().get());
    }

    @Test
    void setScore() {
        updateGradeItemDTO.setScore(Optional.of(50.0));
        assertEquals(50.0,updateGradeItemDTO.getScore().get());
    }

    @Test
    void getWeight() {
        assertEquals(25.0,updateGradeItemDTO.getWeight().get());
    }

    @Test
    void setWeight() {
        updateGradeItemDTO.setWeight(Optional.of(45.5));
        assertEquals(45.5,updateGradeItemDTO.getWeight().get());
    }
}