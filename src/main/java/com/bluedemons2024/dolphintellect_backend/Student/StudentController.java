package com.bluedemons2024.dolphintellect_backend.Student;


import com.bluedemons2024.dolphintellect_backend.Course.Course;
import com.bluedemons2024.dolphintellect_backend.Course.CourseRepository;
import com.bluedemons2024.dolphintellect_backend.EnrolledCourse.EnrolledCourse;
import com.bluedemons2024.dolphintellect_backend.EnrolledCourse.EnrolledCourseRepository;
import com.bluedemons2024.dolphintellect_backend.EnrolledCourseWrapper.EnrolledCourseWrapper;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")

public class StudentController {

    private final StudentRepository studentRepository;
    private final EnrolledCourseRepository enrolledCourseRepository;
    private final CourseRepository courseRepository;

    public StudentController(StudentRepository studentRepository, EnrolledCourseRepository enrolledCourseRepository, CourseRepository courseRepository){
        this.studentRepository = studentRepository;
        this.enrolledCourseRepository = enrolledCourseRepository;
        this.courseRepository = courseRepository;
    }


    //Get All Students
    @GetMapping
    public List<Student> findAll(){
        return studentRepository.findAll();
    }



    //Get Single Student
    @GetMapping("/id/{id}")
    public Optional<Student> findById(@PathVariable String id){
        System.out.println("ID=" +id);
        Optional<Student> data = studentRepository.findById(id);

        System.out.println(data);
        return data;
    }



    // Enrolls a specific student in a specific course
    @PostMapping("/enroll")
    public void enrollStudentInCourse(@RequestBody EnrolledCourseWrapper enrolledCourseWrapper){
        String studentID = enrolledCourseWrapper.getStudentID();
        String courseID = enrolledCourseWrapper.getCourseID();
        EnrolledCourse enrolledCourse = enrolledCourseWrapper.getEnrolledCourse();

        Optional<Student> student = studentRepository.findById(studentID);
        Optional<Course> course = courseRepository.findById(courseID);

        enrolledCourse.setCourse(course.get());
        student.get().getEnrolledCourses().add(enrolledCourse);
        studentRepository.save(student.get());
    }







    //SingleStudent get enrolled courses
    @GetMapping("/{id}/enrolledcourses")
    public List<EnrolledCourse> findEnrolledCoursesByStudentID(@PathVariable String id){
        System.out.println("Looking for ENROLLED COURSE FOR STUDENT TEST");

        return studentRepository.findById(id).get().getEnrolledCourses();

    }


    @GetMapping(value = "/{id}/enrolledcourses", params = {"year"})
    public List<EnrolledCourse>findEnrolledCoursesForYearByStudentID(@PathVariable String id, @RequestParam(required = false) int year){
//        System.out.println("Looking for ENROLLED COURSE FOR STUDENT");

        List<EnrolledCourse> ec = studentRepository.findById(id).get().getEnrolledCourses();

        List<EnrolledCourse> courseList = new ArrayList<>();

        for(EnrolledCourse e: ec){
            if(e.getYear() == year){
                courseList.add(e);
            }
        }

        return courseList;

    }





    //SingleStudent get specific course
//    @GetMapping("/{id}/enrolledcourse/{course_id}")
//    public Optional<EnrolledCourse> findById(@PathVariable String id, @PathVariable Long course_id){
//        Optional<Student> student = studentRepository.findById(id);
//
//       EnrolledCourse ec = null;
//
//        for(EnrolledCourse c:  student.get().getEnrolledCourses()){
//            System.out.println(c);
//            if(c.getId() == course_id){
//                ec = c;
//            }
//        }
//
//        return Optional.ofNullable(ec);
//    }


    @PostMapping
    public void addStudent(@RequestBody Student newStudent){
        System.out.println(newStudent.getName());
        studentRepository.save(newStudent);
    }


    //Delete A Student
    @DeleteMapping
    public void deleteStudent(@RequestParam String id){
        studentRepository.deleteById(id);
    }


    @DeleteMapping("/id/{id}")
    public void getStudentByID(@PathVariable String id) {
//        System.out.println("Getting Course By ID");
        studentRepository.deleteById(id);
    }




}
