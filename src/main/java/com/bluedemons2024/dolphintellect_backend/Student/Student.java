package com.bluedemons2024.dolphintellect_backend.Student;

import com.bluedemons2024.dolphintellect_backend.Course.Course;
import com.bluedemons2024.dolphintellect_backend.EnrolledCourse.EnrolledCourse;
import com.bluedemons2024.dolphintellect_backend.GradeItem.GradeItem;
import org.springframework.data.neo4j.core.schema.*;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

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

    @Relationship(type = "HAS_GRADE_ITEM_FOR", direction = Relationship.Direction.OUTGOING)
    private List<GradeItem> gradeItems;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }

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
       return this.gpa;
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


}
