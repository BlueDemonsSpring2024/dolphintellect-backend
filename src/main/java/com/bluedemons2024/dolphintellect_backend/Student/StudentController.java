package com.bluedemons2024.dolphintellect_backend.Student;


import com.bluedemons2024.dolphintellect_backend.Account.UserEntity;
import com.bluedemons2024.dolphintellect_backend.Account.UserRepistory;
import com.bluedemons2024.dolphintellect_backend.Course.Course;
import com.bluedemons2024.dolphintellect_backend.Course.CourseRepository;
import com.bluedemons2024.dolphintellect_backend.EnrolledCourse.EnrolledCourse;
import com.bluedemons2024.dolphintellect_backend.EnrolledCourse.EnrolledCourseRepository;
import com.bluedemons2024.dolphintellect_backend.EnrolledCourseWrapper.EnrolledCourseWrapper;
import com.bluedemons2024.dolphintellect_backend.GradeItem.GradeItem;
import com.bluedemons2024.dolphintellect_backend.GradeItemWrapper.GradeItemWrapper;
import com.bluedemons2024.dolphintellect_backend.config.CustomUserDetailsService;
import com.bluedemons2024.dolphintellect_backend.config.SecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final UserRepistory userRepistory;

    public StudentController(StudentRepository studentRepository, EnrolledCourseRepository enrolledCourseRepository, CourseRepository courseRepository, UserRepistory userRepistory){
        this.studentRepository = studentRepository;
        this.enrolledCourseRepository = enrolledCourseRepository;
        this.courseRepository = courseRepository;
        this.userRepistory = userRepistory;
    }


    //Get All Students
    @GetMapping
    public List<Student> findAll(){
        return studentRepository.findAll();
    }



//        @GetMapping("/current-user")
//        public String getCurrentUser(@RequestHeader("Authorization") String authorizationHeader) {
//            String studentId = null;
//
//            String jwtToken = authorizationHeader.substring(7);
//
//            Claims claims = Jwts.parser()
//                    .setSigningKey(SecurityConstants.JWT_SECRET)
//                    .parseClaimsJws(jwtToken)
//                    .getBody();
//
//            String username = claims.getSubject();
//
//             Optional<UserEntity> user = userRepistory.findByUsername(username);
//
//             if (user.isPresent()) {
//                 UserEntity userEntity = user.get();
//                 studentId = userEntity.getStudentID();
//             }
//
//            return "Current user: " + username + " with id: " + studentId;
//        }



    private String getStudentID(String authorizationHeader) {
        String studentId = null;

        String jwtToken = authorizationHeader.substring(7);

        Claims claims = Jwts.parser()
                .setSigningKey(SecurityConstants.JWT_SECRET)
                .parseClaimsJws(jwtToken)
                .getBody();

        String username = claims.getSubject();

        Optional<UserEntity> user = userRepistory.findByUsername(username);

        if (user.isPresent()) {
            UserEntity userEntity = user.get();
            studentId = userEntity.getStudentID();
        }

        return studentId;
//        return "Current user: " + username + " with id: " + studentId;
    }



    //USE THIS ROLE FOR CURRENT STUDENT INFO
    @GetMapping("info")
    public Student getStudentInfo(@RequestHeader("Authorization") String authorizationHeader) {
        String studentID = this.getStudentID(authorizationHeader);

        Optional<Student> data = studentRepository.findById(studentID);
        Student student = data.orElse(null);

        List<EnrolledCourse> enrolledCourses = student.getEnrolledCourses();

        for(EnrolledCourse enrolledCourse : enrolledCourses){
            //TEMP!! Move to a better location
            List<GradeItem> gradeItemList = this.getGradeItemsForStudentByCourse(studentID, enrolledCourse.getCourse().getId());
            enrolledCourse.setGradeItems(gradeItemList);

            double calculatedGrade = enrolledCourse.calculateCourseGrade();
            enrolledCourse.setCalculatedGrade(calculatedGrade);
        }

        return student;


    }


    //TODO: DELETE THIS. NOT NEEDED
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



    //Successfully enrolling student with jwt auth
    @PostMapping("enroll-jwt")
    public void addCourseToEnrollment(@RequestHeader("Authorization") String authorizationHeader, @RequestBody EnrolledCourseWrapper enrolledCourseWrapper ){

        String studentID = this.getStudentID(authorizationHeader);

        String courseID = enrolledCourseWrapper.getCourseID();
        EnrolledCourse enrolledCourse = enrolledCourseWrapper.getEnrolledCourse();

        Optional<Student> student = studentRepository.findById(studentID);
        Optional<Course> course = courseRepository.findById(courseID);

        enrolledCourse.setCourse(course.get());
        student.get().getEnrolledCourses().add(enrolledCourse);
        studentRepository.save(student.get());

    }



    //TODO: DELETE THIS. No Longer needed.
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







    @PostMapping("gradeitem-jwt")
    public void createGradeItem(@RequestHeader("Authorization") String authorizationHeader, @RequestBody GradeItemWrapper gradeItemWrapper){
        String studentID = this.getStudentID(authorizationHeader);

//        String studentID = gradeItemWrapper.getStudentID();
        String courseID = gradeItemWrapper.getCourseID();

        GradeItem gradeItem = gradeItemWrapper.getGradeItem();

        Optional<Student> student = studentRepository.findById(studentID);
        Optional<Course> course = courseRepository.findById(courseID);

        gradeItem.setCourse(course.get());

        student.get().setGradeItem(gradeItem);
        studentRepository.save(student.get());


    }


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
