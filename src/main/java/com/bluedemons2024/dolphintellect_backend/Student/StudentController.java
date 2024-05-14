package com.bluedemons2024.dolphintellect_backend.Student;


import com.bluedemons2024.dolphintellect_backend.Account.UserEntity;
import com.bluedemons2024.dolphintellect_backend.Account.UserRepistory;
import com.bluedemons2024.dolphintellect_backend.Course.Course;
import com.bluedemons2024.dolphintellect_backend.Course.CourseRepository;
import com.bluedemons2024.dolphintellect_backend.EnrolledCourse.EnrolledCourse;
import com.bluedemons2024.dolphintellect_backend.EnrolledCourse.EnrolledCourseRepository;
import com.bluedemons2024.dolphintellect_backend.EnrolledCourseWrapper.EnrolledCourseWrapper;
import com.bluedemons2024.dolphintellect_backend.EnrolledCourseWrapper.UpdateEnrolledCourseDTO;
import com.bluedemons2024.dolphintellect_backend.GradeItem.GradeItem;
import com.bluedemons2024.dolphintellect_backend.GradeItem.GradeItemRepository;
import com.bluedemons2024.dolphintellect_backend.GradeItem.UpdateGradeItemDTO;
import com.bluedemons2024.dolphintellect_backend.GradeItemWrapper.GradeItemWrapper;
import com.bluedemons2024.dolphintellect_backend.config.SecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
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
    private final GradeItemRepository gradeItemRepository;

    public StudentController(StudentRepository studentRepository, EnrolledCourseRepository enrolledCourseRepository, CourseRepository courseRepository, UserRepistory userRepistory, GradeItemRepository gradeItemRepository){
        this.studentRepository = studentRepository;
        this.enrolledCourseRepository = enrolledCourseRepository;
        this.courseRepository = courseRepository;
        this.userRepistory = userRepistory;
        this.gradeItemRepository = gradeItemRepository;
    }


    //Get All Students
    @GetMapping
    public List<Student> findAll(){
        return studentRepository.findAll();
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






    ////////////////////////////
    //  ENROLLED COURSE       //
    ////////////////////////////

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


    //Successfully update enrollment
    @PutMapping("enroll-update-jwt")
    public void updateCourseEnrollment(@RequestHeader("Authorization") String authorizationHeader, @RequestBody UpdateEnrolledCourseDTO updateEnrolledCourseDTO ){

        String studentID = this.getStudentID(authorizationHeader);
        Optional<Student> student = studentRepository.findById(studentID);

        List<EnrolledCourse> enrolledCourses = student.get().getEnrolledCourses();

        for(EnrolledCourse enrolledCourse : enrolledCourses){
            if(enrolledCourse.getId().equals(updateEnrolledCourseDTO.getId())){

                Optional<String> finalGrade = updateEnrolledCourseDTO.getFinalGrade();
                Optional<String> term = updateEnrolledCourseDTO.getTerm();
                Optional<Integer> year = updateEnrolledCourseDTO.getYear();

                if(finalGrade !=null){
                    enrolledCourse.setFinalGrade(finalGrade.get());
                }

                if(term != null){
                    enrolledCourse.setTerm(term.get());
                }

                if(year != null){
                    enrolledCourse.setYear(year.get());
                }


            }
        }

        studentRepository.save(student.get());
    }


    @DeleteMapping("enroll-delete-jwt/{id}")
    public void deleteCourseEnrollment(@RequestHeader("Authorization") String authorizationHeader, @PathVariable Long id){
//        System.out.println("deleteCourseEnrollment Route");
        String studentID = this.getStudentID(authorizationHeader);
        Optional<Student> student = studentRepository.findById(studentID);
        List<EnrolledCourse> enrolledCourses = student.get().getEnrolledCourses();
        List<GradeItem> gradeItems = student.get().getGradeItems();

        String courseID = null;

        Iterator<EnrolledCourse> enrolledCourseIterator = enrolledCourses.iterator();
        while (enrolledCourseIterator.hasNext()) {
            EnrolledCourse enrolledCourse = enrolledCourseIterator.next();
            if (enrolledCourse.getId().equals(id)) {
                System.out.println("Deleting enrolled course");
                courseID = enrolledCourse.getCourse().getId();
                enrolledCourseIterator.remove();
            }
        }


        // Remove corresponding grade items
        Iterator<GradeItem> gradeItemIterator = gradeItems.iterator();
        while (gradeItemIterator.hasNext()) {
            GradeItem gradeItem = gradeItemIterator.next();
            System.out.println("++++++++++++++++++++++++++++++");
            System.out.println("CourseID: " + courseID);
            System.out.println("GradeItem CourseID: " + gradeItem.getCourse().getId());
            String gradeItemCourseId = gradeItem.getCourse().getId();

            if (gradeItemCourseId.equals(courseID)) {
                gradeItemIterator.remove();
            }
        }

        studentRepository.save(student.get());

    }


    ////////////////////////////
    //  GRADE ITEMS            //
    ////////////////////////////

    //SUCCESSFULLY WORKING WITH JWT
    @PostMapping("gradeitem-jwt")
    public void createGradeItem(@RequestHeader("Authorization") String authorizationHeader, @RequestBody GradeItemWrapper gradeItemWrapper){
        String studentID = this.getStudentID(authorizationHeader);

        String courseID = gradeItemWrapper.getCourseID();

        GradeItem gradeItem = gradeItemWrapper.getGradeItem();

        Optional<Student> student = studentRepository.findById(studentID);
        Optional<Course> course = courseRepository.findById(courseID);

        gradeItem.setCourse(course.get());

        student.get().setGradeItem(gradeItem);
        studentRepository.save(student.get());
    }


    //Update Grade Item
    @PutMapping("update-gradeitem-jwt")
    public void updateGradeItem(@RequestHeader("Authorization") String authorizationHeader, @RequestBody UpdateGradeItemDTO updateGradeItemDTO){
        String studentID = this.getStudentID(authorizationHeader);
        Optional<Student> student = studentRepository.findById(studentID);

        Long gradeItemID = updateGradeItemDTO.getId();

        List<GradeItem> gradeItems = student.get().getGradeItems();

        for(GradeItem gradeItem : gradeItems){
            if(gradeItem.getId().equals(gradeItemID)){
                    Optional<String> name = updateGradeItemDTO.getName();
                    Optional<Double> score = updateGradeItemDTO.getScore();
                    Optional<Double> weight = updateGradeItemDTO.getWeight();

                    if(name != null){
                        gradeItem.setName(name.get());
                    }

                    if(score != null){
                        gradeItem.setScore(score.get());
                    }

                    if(weight != null){
                        gradeItem.setWeight(weight.get());
                    }

            }
        }

        studentRepository.save(student.get());

    }


    //Delete a GradeItem
    @DeleteMapping("delete-gradeitem-jwt/{id}")
    public void deleteGradeItem(@RequestHeader("Authorization") String authorizationHeader, @PathVariable Long id ){
        String studentID = this.getStudentID(authorizationHeader);
        Optional<Student> student = studentRepository.findById(studentID);
        List<GradeItem> gradeItems = student.get().getGradeItems();
        for(GradeItem gradeItem : gradeItems){
            if(gradeItem.getId().equals(id)){
                gradeItems.remove(gradeItem);
                break;
            }
        }

        studentRepository.save(student.get());

    }

    ////////////////////////////
    //  Helpers               //
    ////////////////////////////

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
    }








    ////////////////////////////
    //  NO LONGER NEEDED       //
    ////////////////////////////

//
//    //TODO: DELETE NO Longer Needed
//    //Add a grade item for a course
//    @PostMapping("/gradeItem")
//    public void addGradeItem(@RequestBody GradeItemWrapper gradeItemWrapper){
//
//        String studentID = gradeItemWrapper.getStudentID();
//        String courseID = gradeItemWrapper.getCourseID();
//
//        GradeItem gradeItem = gradeItemWrapper.getGradeItem();
//
//        Optional<Student> student = studentRepository.findById(studentID);
//        Optional<Course> course = courseRepository.findById(courseID);
//
//        gradeItem.setCourse(course.get());
//
//        student.get().setGradeItem(gradeItem);
//        studentRepository.save(student.get());
//    }
//
//
//    //TODO: DELETE THIS. No Longer needed.
//    // Enrolls a specific student in a specific course
//    @PostMapping("/enroll")
//    public void enrollStudentInCourse(@RequestBody EnrolledCourseWrapper enrolledCourseWrapper){
//
//        System.out.println("CURRENT LOGGED IN STUDENT");
//
//
//        String studentID = enrolledCourseWrapper.getStudentID();
//        String courseID = enrolledCourseWrapper.getCourseID();
//        EnrolledCourse enrolledCourse = enrolledCourseWrapper.getEnrolledCourse();
//
//        Optional<Student> student = studentRepository.findById(studentID);
//        Optional<Course> course = courseRepository.findById(courseID);
//
//        enrolledCourse.setCourse(course.get());
//        student.get().getEnrolledCourses().add(enrolledCourse);
//        studentRepository.save(student.get());
//    }
//
//
//    //TODO: DELETE THIS. NOT NEEDED
//    //Get Single Student
//    @GetMapping("/id/{id}")
//    public Student findById(@PathVariable String id){
//        Optional<Student> data = studentRepository.findById(id);
//        Student student = data.orElse(null);
//
//        List<EnrolledCourse> enrolledCourses = student.getEnrolledCourses();
//
//        for(EnrolledCourse enrolledCourse : enrolledCourses){
//            //TEMP!! Move to a better location
//            List<GradeItem> gradeItemList = this.getGradeItemsForStudentByCourse(id, enrolledCourse.getCourse().getId());
//            enrolledCourse.setGradeItems(gradeItemList);
//
//            double calculatedGrade = enrolledCourse.calculateCourseGrade();
//            enrolledCourse.setCalculatedGrade(calculatedGrade);
//        }
//
//        return student;
//    }
//
//

    //SingleStudent get enrolled courses
//    @GetMapping("/{id}/enrolledcourses")
//    public List<EnrolledCourse> findEnrolledCoursesByStudentID(@PathVariable String id){
//        System.out.println("Looking for ENROLLED COURSE FOR STUDENT TEST");
//
//        return studentRepository.findById(id).get().getEnrolledCourses();
//
//    }


//    @GetMapping(value = "/{id}/enrolledcourses", params = {"year"})
//    public List<EnrolledCourse>findEnrolledCoursesForYearByStudentID(@PathVariable String id, @RequestParam(required = false) int year){
//        List<EnrolledCourse> ec = studentRepository.findById(id).get().getEnrolledCourses();
//        List<EnrolledCourse> courseList = new ArrayList<>();
//        for(EnrolledCourse e: ec){
//            if(e.getYear() == year){
//                courseList.add(e);
//            }
//        }
//        return courseList;
//    }





//    //Create Student
//    @PostMapping
//    public void addStudent(@RequestBody Student newStudent){
//        System.out.println(newStudent.getName());
//        studentRepository.save(newStudent);
//    }


//    //Delete A Student
//    @DeleteMapping
//    public void deleteStudent(@RequestParam String id){
//        studentRepository.deleteById(id);
//    }


//    @DeleteMapping("/id/{id}")
//    public void getStudentByID(@PathVariable String id) {
//        studentRepository.deleteById(id);
//    }






}
