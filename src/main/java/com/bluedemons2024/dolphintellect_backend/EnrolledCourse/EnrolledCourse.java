package com.bluedemons2024.dolphintellect_backend.EnrolledCourse;

import com.bluedemons2024.dolphintellect_backend.Course.Course;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

@RelationshipProperties
public class EnrolledCourse {

//    @Id @GeneratedValue(UUIDStringGenerator.class)
//    private String id;


    @Id @GeneratedValue
    private Long id;




    private String term;
    private int year;
    private int credits;
    private String finalGrade;

    @TargetNode
    private Course course;

//    @TargetNode
//    private Student student;

    public void setYear(int year) {
        this.year = year;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public void setFinalGrade(String finalGrade) {
        this.finalGrade = finalGrade;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public Course getCourse() {
        return course;
    }

//    public Student getStudent() {return student;}

    public Long getId() {
        return id;
    }

    public int getYear() {
        return year;
    }

    public int getCredits() {
        return credits;
    }

    public String getFinalGrade() {
        return finalGrade;
    }
}
