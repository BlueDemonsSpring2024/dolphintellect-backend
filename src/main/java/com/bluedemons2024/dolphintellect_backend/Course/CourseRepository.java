package com.bluedemons2024.dolphintellect_backend.Course;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface CourseRepository extends Neo4jRepository<Course, String> {
    Course getCourseBySubjectAndNumber(String subject, int number);

    Course getCourseById(String id);

    List<Course> findCoursesBySubject(String subject);
    void deleteCourseBySubjectAndNumber(String subject, int number);
//    Void updateCourseByDescription();
//    void updateCourseBySubjectAndNumber(String subject, int number);
//    void update
}
