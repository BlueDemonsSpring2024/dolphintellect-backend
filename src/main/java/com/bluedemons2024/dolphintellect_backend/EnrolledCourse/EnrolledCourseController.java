package com.bluedemons2024.dolphintellect_backend.EnrolledCourse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

public class EnrolledCourseController {

    @Autowired
    EnrolledCourseRepository enrolledCourseRepository;

    @PostMapping("/enroll")
    public void enrollStudent(){
    }
}
