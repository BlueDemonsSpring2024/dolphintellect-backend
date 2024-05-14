package com.bluedemons2024.dolphintellect_backend.Course;

import com.bluedemons2024.dolphintellect_backend.EnrolledCourse.EnrolledCourse;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

import java.util.List;

@Node
public class Course {
    @Id
    @GeneratedValue(UUIDStringGenerator.class)
    private String id;

    private String subject;
    private int number;
    private String title;
    private String description;


    @Relationship(type = "IS_ENROLLED_IN",direction = Relationship.Direction.INCOMING)
    private List<EnrolledCourse> student;


    public String getId(){return id;}

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
