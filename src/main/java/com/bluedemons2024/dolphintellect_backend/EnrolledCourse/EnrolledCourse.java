package com.bluedemons2024.dolphintellect_backend.EnrolledCourse;

import com.bluedemons2024.dolphintellect_backend.Course.Course;
import com.bluedemons2024.dolphintellect_backend.GradeItem.GradeItem;
import com.bluedemons2024.dolphintellect_backend.Student.Student;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("deprecation")
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
        System.out.println("BEGIN+++++++++++++++++++++++++++++");

        double weightTotal = 0;
        double scoreTotal = 0;

        List<GradeItem> gradeItems = this.gradeItems;

        for(GradeItem gradeItem : gradeItems){
//            String courseIDForGradeItem = gradeItem.getCourse().getId();
            System.out.println("================================");
            System.out.println(this.course.getTitle());
            System.out.println(gradeItem.getName());


//            if(courseID.equals(courseIDForGradeItem)){
                double scoreWeightProduct = gradeItem.getScore() * gradeItem.getWeight();
//                System.out.println("scoreWeightProduct: " + scoreWeightProduct);
                scoreTotal += scoreWeightProduct;
                weightTotal += gradeItem.getWeight();
//            }

            System.out.println("================================");
        }

        double calculatedGrade = scoreTotal / weightTotal;


        System.out.println("INTERNAL Calculated Grade : " + calculatedGrade);
        System.out.println("END+++++++++++++++++++++++++++++");

        return calculatedGrade;

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
