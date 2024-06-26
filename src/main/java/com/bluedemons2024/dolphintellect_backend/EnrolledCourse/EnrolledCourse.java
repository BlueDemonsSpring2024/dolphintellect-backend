package com.bluedemons2024.dolphintellect_backend.EnrolledCourse;

import com.bluedemons2024.dolphintellect_backend.Course.Course;
import com.bluedemons2024.dolphintellect_backend.GradeItem.GradeItem;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

import java.util.List;

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
    private double calculatedGrade;

    private List<GradeItem> gradeItems;


    public double getCalculatedGrade() {
        return calculatedGrade;
    }

    public void setCalculatedGrade(double calculatedGrade) {
        this.calculatedGrade = calculatedGrade;
    }

    @TargetNode
    private Course course;

    public double calculateCourseGrade(){

        double weightTotal = 0;
        double scoreTotal = 0;

        List<GradeItem> gradeItems = this.gradeItems;

        for(GradeItem gradeItem : gradeItems){
                double scoreWeightProduct = gradeItem.getScore() * gradeItem.getWeight();
                scoreTotal += scoreWeightProduct;
                weightTotal += gradeItem.getWeight();

        }

        return scoreTotal / weightTotal;

    }


    public List<GradeItem> getGradeItems() {
        return gradeItems;
    }

    public void setGradeItems(List<GradeItem> gradeItems) {
        this.gradeItems = gradeItems;
    }

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
