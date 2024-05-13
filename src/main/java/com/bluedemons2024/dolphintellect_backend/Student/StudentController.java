package com.bluedemons2024.dolphintellect_backend.Student;


import com.bluedemons2024.dolphintellect_backend.Course.Course;
import com.bluedemons2024.dolphintellect_backend.Course.CourseRepository;
import com.bluedemons2024.dolphintellect_backend.EnrolledCourse.EnrolledCourse;
import com.bluedemons2024.dolphintellect_backend.EnrolledCourse.EnrolledCourseRepository;
import com.bluedemons2024.dolphintellect_backend.EnrolledCourseWrapper.EnrolledCourseWrapper;
import com.bluedemons2024.dolphintellect_backend.GradeItem.GradeItem;
import com.bluedemons2024.dolphintellect_backend.GradeItemWrapper.GradeItemWrapper;
import com.bluedemons2024.dolphintellect_backend.config.CustomUserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")

@SuppressWarnings("deprecation")
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


    //TODO: DELETE THIS. ITS ONLY FOR DEVELOPMENT TESTING
    @GetMapping("/current")
    public String getCurrentStudent(Principal principal){
        return "Current " + principal.getName();
    }




    //Get Single Student
    @GetMapping("/id/{id}")
    public Student findById(@PathVariable String id){
        Optional<Student> data = studentRepository.findById(id);
        Student student = data.orElse(null);

        List<EnrolledCourse> enrolledCourses = student.getEnrolledCourses();

        for(EnrolledCourse enrolledCourse : enrolledCourses){
            //TEMP!! Move to a better location
            List<GradeItem> gradeItemList = this.getGradeItemsForStudentByCourse(id, enrolledCourse.getCourse().getId());
            enrolledCourse.setGradeItems(gradeItemList);

            double calculatedGrade = enrolledCourse.calculateCourseGrade();
            enrolledCourse.setCalculatedGrade(calculatedGrade);
        }

        return student;
    }



    // Enrolls a specific student in a specific course
    @PostMapping("/enroll")
    public void enrollStudentInCourse(@RequestBody EnrolledCourseWrapper enrolledCourseWrapper){

        System.out.println("CURRENT LOGGED IN STUDENT");


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
        List<EnrolledCourse> ec = studentRepository.findById(id).get().getEnrolledCourses();
        List<EnrolledCourse> courseList = new ArrayList<>();
        for(EnrolledCourse e: ec){
            if(e.getYear() == year){
                courseList.add(e);
            }
        }
        return courseList;
    }


    //Create Student
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





    //TODO: get grade items for a specific course
    public List<GradeItem> getGradeItemsForStudentByCourse(String studentID,String courseID){
        List<GradeItem> gradeItems = studentRepository.findById(studentID).get().getGradeItems();
        List<GradeItem> gradeItemsListForCourse = new ArrayList<>();

        for(GradeItem gradeItem : gradeItems){
            String courseIDForGradeItem = gradeItem.getCourse().getId();
            if(courseID.equals(courseIDForGradeItem)){
                gradeItemsListForCourse.add(gradeItem);
            }
        }

        return gradeItemsListForCourse;
    }


    //TODO: get all grade items for a student






    //Add a grade item for a course
    @PostMapping("/gradeItem")
    public void addGradeItem(@RequestBody GradeItemWrapper gradeItemWrapper){

        String studentID = gradeItemWrapper.getStudentID();
        String courseID = gradeItemWrapper.getCourseID();

        GradeItem gradeItem = gradeItemWrapper.getGradeItem();

        Optional<Student> student = studentRepository.findById(studentID);
        Optional<Course> course = courseRepository.findById(courseID);

        gradeItem.setCourse(course.get());

        student.get().setGradeItem(gradeItem);
        studentRepository.save(student.get());
    }


}
