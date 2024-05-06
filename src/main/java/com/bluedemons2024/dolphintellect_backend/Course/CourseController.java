package com.bluedemons2024.dolphintellect_backend.Course;


//import jakarta.persistence.Id;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ConfigurationProperties(prefix = "spring.neo4j")
@RestController
@RequestMapping("/courses")
public class CourseController {

//    private final CourseRepository courseRepository;
//
//    public CourseController(CourseRepository courseRepository){
//        this.courseRepository = courseRepository;
//    }

    @Autowired
    CourseRepository courseRepository;

    @GetMapping
    public List<Course> findAll(){
        return courseRepository.findAll();
    }

    @GetMapping("/id/{id}")
    public Course getCourseByID(@PathVariable String id) {
        System.out.println("Getting Course By ID");
        return courseRepository.getCourseById(id);
    }

    @GetMapping(params = "subject")
    public List<Course> findCoursesBySubject(@RequestParam String subject){
        return courseRepository.findCoursesBySubject(subject);
    }

    @PostMapping
    public void addCourse(@RequestBody Course newCourse){

        System.out.println("adding course");
        System.out.println(newCourse.getTitle());
        courseRepository.save(newCourse);
    }


    //Update a course description
//    @PutMapping(params = {"subject", "number"})
//    public void updateCourseDescription(@RequestParam String subject, @RequestParam int number, @RequestBody String description){
//        courseRepository.updateCourseBySubjectAndNumber(subject, number);
//    }



//    @DeleteMapping
//    public void deleteCourse(@RequestParam Long courseID){
//        courseRepository.deleteById(courseID);
//    }

    @DeleteMapping(params = {"subject", "number"})
    public void deleteCourse(@RequestParam String subject, @RequestParam int number){
//        courseRepository.deleteById(courseID);
        courseRepository.deleteCourseBySubjectAndNumber(subject, number);
    }

}
