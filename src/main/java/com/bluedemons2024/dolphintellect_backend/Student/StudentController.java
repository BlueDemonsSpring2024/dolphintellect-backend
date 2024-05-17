package com.bluedemons2024.dolphintellect_backend.Student;

import com.bluedemons2024.dolphintellect_backend.Account.UserEntity;
import com.bluedemons2024.dolphintellect_backend.Account.UserRepository;
import com.bluedemons2024.dolphintellect_backend.Course.Course;
import com.bluedemons2024.dolphintellect_backend.Course.CourseRepository;
import com.bluedemons2024.dolphintellect_backend.EnrolledCourse.EnrolledCourse;
import com.bluedemons2024.dolphintellect_backend.EnrolledCourse.EnrolledCourseDTO;
import com.bluedemons2024.dolphintellect_backend.EnrolledCourse.UpdateEnrolledCourseDTO;
import com.bluedemons2024.dolphintellect_backend.GradeItem.GradeItem;
import com.bluedemons2024.dolphintellect_backend.GradeItem.GradeItemDTO;
import com.bluedemons2024.dolphintellect_backend.GradeItem.UpdateGradeItemDTO;
import com.bluedemons2024.dolphintellect_backend.Security.SecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public StudentController(StudentRepository studentRepository, CourseRepository courseRepository, UserRepository userRepository){
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("all")
    public List<Student> findAll(){
        return studentRepository.findAll();
    }


    //USE THIS ROLE FOR CURRENT STUDENT INFO
    @GetMapping()
    public Student getStudentInfo(@RequestHeader("Authorization") String authorizationHeader) {
        String studentID = this.getStudentID(authorizationHeader);

        Optional<Student> data = studentRepository.findById(studentID);
        Student student = data.orElse(null);

        List<EnrolledCourse> enrolledCourses = null;

        if (student != null) {
            enrolledCourses = student.getEnrolledCourses();
        }

        if (enrolledCourses != null) {
            for(EnrolledCourse enrolledCourse : enrolledCourses){
                List<GradeItem> gradeItemList = this.getGradeItemsForStudentByCourse(studentID, enrolledCourse.getCourse().getId());
                enrolledCourse.setGradeItems(gradeItemList);

                double calculatedGrade = enrolledCourse.calculateCourseGrade();
                enrolledCourse.setCalculatedGrade(calculatedGrade);
            }
        }

        return student;

    }

    ////////////////////////////
    //  ENROLLED COURSE       //
    ////////////////////////////


    @PostMapping("enrolled-course")
    public void addCourseToEnrollment(@RequestHeader("Authorization") String authorizationHeader, @RequestBody EnrolledCourseDTO enrolledCourseDTO ){
        EnrolledCourse enrolledCourse = new EnrolledCourse();
        Student student = null;
        Course course = null;

        String studentID = this.getStudentID(authorizationHeader);
        Optional<Student> studentOptional = studentRepository.findById(studentID);
        if(studentOptional.isPresent()){
            student = studentOptional.get();
        }


        Optional<String> courseIDOptional = enrolledCourseDTO.getCourseID();
        if(courseIDOptional.isPresent()){
            Optional<Course> courseOptional = courseRepository.findById(courseIDOptional.get());

            if(courseOptional.isPresent()){
                course = courseOptional.get();
            }
        }

        enrolledCourse.setCourse(course);

        Optional<String> term = enrolledCourseDTO.getTerm();
        term.ifPresent(enrolledCourse::setTerm);

        Optional<Integer> year = enrolledCourseDTO.getYear();
        year.ifPresent(enrolledCourse::setYear);

        Optional<Integer> credits = enrolledCourseDTO.getCredits();
        credits.ifPresent(enrolledCourse::setCredits);

        Optional<String> finalGrade = enrolledCourseDTO.getFinalGrade();
        finalGrade.ifPresent(enrolledCourse::setFinalGrade);


        if (student != null) {
            student.getEnrolledCourses().add(enrolledCourse);
            studentRepository.save(student);
        }

    }

    //Successfully update enrollment
    @PutMapping("enrolled-course")
    public void updateCourseEnrollment(@RequestHeader("Authorization") String authorizationHeader, @RequestBody UpdateEnrolledCourseDTO updateEnrolledCourseDTO ){

        String studentID = this.getStudentID(authorizationHeader);
        Optional<Student> studentOptional = studentRepository.findById(studentID);
        Student student = studentOptional.orElse(null);

        List<EnrolledCourse> enrolledCourses = null;
        if (student != null) {
            enrolledCourses = student.getEnrolledCourses();
        }

        if (enrolledCourses != null) {
            for(EnrolledCourse enrolledCourse : enrolledCourses){
                if(enrolledCourse.getId().equals(updateEnrolledCourseDTO.getId())){

                    Optional<String> finalGrade = updateEnrolledCourseDTO.getFinalGrade();
                    finalGrade.ifPresent(enrolledCourse::setFinalGrade);

                    Optional<String> term = updateEnrolledCourseDTO.getTerm();
                    term.ifPresent(enrolledCourse::setTerm);

                    Optional<Integer> year = updateEnrolledCourseDTO.getYear();
                    year.ifPresent(enrolledCourse::setYear);

                }
            }
        }

        if (student != null) {
            studentRepository.save(student);
        }
    }


    @DeleteMapping("enrolled-course/{id}")
    public void deleteCourseEnrollment(@RequestHeader("Authorization") String authorizationHeader, @PathVariable Long id){
        String studentID = this.getStudentID(authorizationHeader);
        Optional<Student> studentOptional = studentRepository.findById(studentID);
        Student student = studentOptional.orElse(null);

        List<EnrolledCourse> enrolledCourses = null;
        if (student != null) {
            enrolledCourses = student.getEnrolledCourses();
        }

        List<GradeItem> gradeItems = null;
        if (student != null) {
            gradeItems = student.getGradeItems();
        }

        String courseID = null;

        Iterator<EnrolledCourse> enrolledCourseIterator = null;
        if (enrolledCourses != null) {
            enrolledCourseIterator = enrolledCourses.iterator();
        }
        if (enrolledCourseIterator != null) {
            while (enrolledCourseIterator.hasNext()) {
                EnrolledCourse enrolledCourse = enrolledCourseIterator.next();
                if (enrolledCourse.getId().equals(id)) {
                    System.out.println("Deleting enrolled course");
                    courseID = enrolledCourse.getCourse().getId();
                    enrolledCourseIterator.remove();
                }
            }
        }


        // Remove corresponding grade items
        Iterator<GradeItem> gradeItemIterator = null;
        if (gradeItems != null) {
            gradeItemIterator = gradeItems.iterator();
        }
        if (gradeItemIterator != null) {
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
        }

        if (student != null) {
            studentRepository.save(student);
        }

    }


    ////////////////////////////
    //  GRADE ITEMS            //
    ////////////////////////////

    //SUCCESSFULLY WORKING WITH JWT
    @PostMapping("grade-item")
    public void createGradeItem(@RequestHeader("Authorization") String authorizationHeader, @RequestBody GradeItemDTO gradeItemDTO){
        GradeItem gradeItem = new GradeItem();

        String studentID = this.getStudentID(authorizationHeader);
        Student student = studentRepository.findById(studentID).orElse(null);

        Course course = null;
        Optional<String> courseIDOptional = gradeItemDTO.getCourseID();
        if (courseIDOptional.isPresent()) {
            Optional<Course> courseOptional = courseRepository.findById(courseIDOptional.get());
            course = courseOptional.orElse(null);
            System.out.println("Course Title: " + course.getTitle());
        }

        Optional<String> name = gradeItemDTO.getName();
        name.ifPresent(gradeItem::setName);

        Optional<Double> score = gradeItemDTO.getScore();
        score.ifPresent(gradeItem::setScore);

        Optional<Double> weight = gradeItemDTO.getWeight();
        weight.ifPresent(gradeItem::setWeight);

        gradeItem.setCourse(course);

        if (student != null) {
            student.setGradeItem(gradeItem);
            studentRepository.save(student);
        }

    }



    //Update Grade Item
    @PutMapping("grade-item")
    public void updateGradeItem(@RequestHeader("Authorization") String authorizationHeader, @RequestBody UpdateGradeItemDTO updateGradeItemDTO){
        String studentID = this.getStudentID(authorizationHeader);
        Optional<Student> studentOptional = studentRepository.findById(studentID);
        Student student = studentOptional.orElse(null);

        Long gradeItemID = updateGradeItemDTO.getId();

        List<GradeItem> gradeItems = null;
        if (student != null) {
            gradeItems = student.getGradeItems();
        }

        if (gradeItems != null) {
            for(GradeItem gradeItem : gradeItems){
                if(gradeItem.getId().equals(gradeItemID)){
                        Optional<String> name = updateGradeItemDTO.getName();
                        name.ifPresent(gradeItem::setName);

                        Optional<Double> score = updateGradeItemDTO.getScore();
                        score.ifPresent(gradeItem::setScore);

                        Optional<Double> weight = updateGradeItemDTO.getWeight();
                        weight.ifPresent(gradeItem::setWeight);

                }
            }
        }

        if (student != null) {
            studentRepository.save(student);
        }

    }


    //Delete a GradeItem
    @DeleteMapping("grade-item/{id}")
    public void deleteGradeItem(@RequestHeader("Authorization") String authorizationHeader, @PathVariable Long id ){
        String studentID = this.getStudentID(authorizationHeader);
        Optional<Student> studentOptional = studentRepository.findById(studentID);
        Student student = studentOptional.orElse(null);

        List<GradeItem> gradeItems = null;
        if (student != null) {
            gradeItems = student.getGradeItems();
        }

        if (gradeItems != null) {
            for(GradeItem gradeItem : gradeItems){
                if(gradeItem.getId().equals(id)){
                    gradeItems.remove(gradeItem);
                    break;
                }
            }
        }

        if (student != null) {
            studentRepository.save(student);
        }

    }

    ////////////////////////////
    //  Helpers               //
    ////////////////////////////


    public List<GradeItem> getGradeItemsForStudentByCourse(String studentID,String courseID){
        Student student = studentRepository.findById(studentID).orElse(null);

        List<GradeItem> gradeItems = null;
        if (student != null) {
            gradeItems = student.getGradeItems();
        }
        List<GradeItem> gradeItemsListForCourse = new ArrayList<>();

        if (gradeItems != null) {
            for(GradeItem gradeItem : gradeItems){
                String courseIDForGradeItem = gradeItem.getCourse().getId();
                if(courseID.equals(courseIDForGradeItem)){
                    gradeItemsListForCourse.add(gradeItem);
                }
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

        Optional<UserEntity> user = userRepository.findByUsername(username);

        if (user.isPresent()) {
            UserEntity userEntity = user.get();
            studentId = userEntity.getStudentID();
        }

        return studentId;
    }

}
