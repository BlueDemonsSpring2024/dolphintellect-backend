package com.bluedemons2024.dolphintellect_backend.Student;


import com.bluedemons2024.dolphintellect_backend.Course.Course;
import com.bluedemons2024.dolphintellect_backend.Course.CourseRepository;
import com.bluedemons2024.dolphintellect_backend.EnrolledCourse.EnrolledCourse;
import com.bluedemons2024.dolphintellect_backend.EnrolledCourse.EnrolledCourseRepository;
import com.bluedemons2024.dolphintellect_backend.EnrolledCourseWrapper.EnrolledCourseWrapper;
import com.bluedemons2024.dolphintellect_backend.GradeItem.GradeItem;
import com.bluedemons2024.dolphintellect_backend.GradeItemWrapper.GradeItemWrapper;
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
        Optional<Student> data = studentRepository.findById(id);

        ArrayList<String> gradeList = new ArrayList<>();

        List<EnrolledCourse> enrolledCourses = data.get().getEnrolledCourses();

        for(EnrolledCourse enrolledCourse : enrolledCourses){
            String courseGrade = enrolledCourse.getFinalGrade();
            gradeList.add(courseGrade);

            //TEMP!! Move to a better location
            double calculatedGrade = this.calculateCourseGrade(id, enrolledCourse.getCourse().getId());
            enrolledCourse.setCalculatedGrade(calculatedGrade);
            enrolledCourseRepository.save(enrolledCourse);

        }

        double gradeSums = 0;

        for(String grade : gradeList){
            switch (grade){
                case "A": gradeSums += 4; break;
                case "A-": gradeSums += 3.7; break;
                case "B+": gradeSums += 3.3; break;
                case "B": gradeSums += 3; break;
                case "B-": gradeSums += 2.7; break;
                case "C+": gradeSums += 2.3; break;
                case "C": gradeSums += 2; break;
                case "C-": gradeSums += 1.7; break;
                case "D+": gradeSums += 1.3; break;
                case "D": gradeSums += 1; break;
                case "D-": gradeSums += 0.7; break;
                case "F": gradeSums += 0; break;
            }
        }

        double gpa = gradeSums / gradeList.size();

        gpa = Math.ceil(gpa * 100) / 100;
        data.get().setGpa(gpa);
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






    //TODO: implement calculator for a course grade
    public double calculateCourseGrade(String studentID, String courseID){
        double weightTotal = 0;
        double scoreTotal = 0;

        // find the student
        Optional<Student> student = studentRepository.findById(studentID);

        //Get the student gradeItems
        List<GradeItem> gradeItems = student.get().getGradeItems();

        for(GradeItem gradeItem : gradeItems){
            String courseIDForGradeItem = gradeItem.getCourse().getId();

            if(courseID.equals(courseIDForGradeItem)){
                double scoreWeightProduct = gradeItem.getScore() * gradeItem.getWeight();
                System.out.println("scoreWeightProduct: " + scoreWeightProduct);
                scoreTotal += scoreWeightProduct;
                weightTotal += gradeItem.getWeight();
            }

        }

        double calculatedGrade = scoreTotal / weightTotal;


        System.out.println("Calculated Grade: " + calculatedGrade);
        System.out.println(String.format("Calculated Grade for %s: %f", courseID, calculatedGrade));

        return calculatedGrade;

    }




    //TODO: get grade items for a specific course


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
