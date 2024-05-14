package com.bluedemons2024.dolphintellect_backend.Student;

import com.bluedemons2024.dolphintellect_backend.Course.Course;
import com.bluedemons2024.dolphintellect_backend.EnrolledCourse.EnrolledCourse;
import com.bluedemons2024.dolphintellect_backend.GradeItem.GradeItem;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.neo4j.core.schema.*;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

import java.util.ArrayList;
import java.util.List;

@Node(value = "Student")
public class Student {

    @Id
    @GeneratedValue(UUIDStringGenerator.class)
    private String id;
    private String name;
    private double gpa;

    public String getId() {
        return id;
    }


    @Relationship(type="IS_ENROLLED_IN", direction = Relationship.Direction.OUTGOING)
    private List<EnrolledCourse> enrolledCourses;

    @JsonIgnore
    @Relationship(type = "HAS_GRADE_ITEM_FOR", direction = Relationship.Direction.OUTGOING)
    private List<GradeItem> gradeItems;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Student() {}

    public EnrolledCourse isEnrolledIn(Course course, EnrolledCourse enrolledCourse){
//        EnrolledCourse enrolledCourse
        this.enrolledCourses.add(enrolledCourse);

        return enrolledCourse;
    }

    public List<GradeItem> getGradeItems() {
        return gradeItems;
    }

    public void setGradeItems(List<GradeItem> gradeItems) {
        this.gradeItems = gradeItems;
    }

    public void setGradeItem(GradeItem gradeItem) {
        this.gradeItems.add(gradeItem);
    }

    public double getGpa(){
//       return this.gpa;
        return this.calculateGPA();
    }

    public void setGpa(double gpa){
        this.gpa = gpa;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<EnrolledCourse> getEnrolledCourses() {
        return enrolledCourses;
    }

    private double calculateGPA(){

        ArrayList<String> gradeList = new ArrayList<>();

        for(EnrolledCourse enrolledCourse : enrolledCourses){
            String finalCourseGrade = enrolledCourse.getFinalGrade();
            gradeList.add(finalCourseGrade);
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
        return gpa;

    }




}
