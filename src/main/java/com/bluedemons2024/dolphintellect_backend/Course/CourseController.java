package com.bluedemons2024.dolphintellect_backend.Course;


import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseRepository courseRepository;

    public CourseController(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
    }


    //Get all courses
    @GetMapping
    public List<Course> findAll(){
        return courseRepository.findAll();
    }

    //Get all course by subject
    @GetMapping(params = {"subject"})
    public List<Course> findCourseBySubject(@RequestParam String subject){
        return courseRepository.findCoursesBySubject(subject);
    }

    //Get all course by number
    @GetMapping(params = {"number"})
    public List<Course> findCourseByNumber(@RequestParam int number){
        return courseRepository.findCoursesByNumber(number);
    }


    //get single course by subject and number
    @GetMapping(params = {"subject", "number"})
    public Course findCourseBySubjectAndNumber(@RequestParam String subject, @RequestParam int number){
        return courseRepository.findCourseBySubjectAndNumber(subject, number);
    }


    //Get single course by ID
    @GetMapping("{id}")
    public Course getCourseByID(@PathVariable String id) {
        return courseRepository.getCourseById(id);
    }


    //create a new course
    @PostMapping
    public void addCourse(@RequestBody Course newCourse){
        courseRepository.save(newCourse);
    }

    @PostMapping("bulk")
    public void addCourseBulk(@RequestBody List<Course> newCourses){
        courseRepository.saveAll(newCourses);
    }


    @PutMapping()
    public void updateCourse(@RequestBody CourseDTO courseDTO){
        String courseID = courseDTO.getCourseID().get();

        Course course = courseRepository.getCourseById(courseID);

        Optional<String> subject = courseDTO.getSubject();
        Optional<Integer> number = courseDTO.getNumber();
        Optional<String> title = courseDTO.getTitle();
        Optional<String> description = courseDTO.getDescription();


        if(subject != null){
            course.setSubject(subject.get());
        }

        if(number != null){
            course.setNumber(number.get());
        }

        if(title != null){
            course.setTitle(title.get());
        }

        if(description != null){
            course.setDescription(description.get());
        }



    }



    //delete a course
    @DeleteMapping("{id}")
    public void deleteCourse(@PathVariable String id){
        courseRepository.deleteById(id);
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

//    @DeleteMapping(params = {"subject", "number"})
//    public void deleteCourse(@RequestParam String subject, @RequestParam int number){
//        courseRepository.deleteCourseBySubjectAndNumber(subject, number);
//    }

}
