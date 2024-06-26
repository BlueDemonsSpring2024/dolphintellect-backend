package com.bluedemons2024.dolphintellect_backend.Course;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface CourseRepository extends Neo4jRepository<Course, String> {
    Course getCourseBySubjectAndNumber(String subject, int number);

    Course getCourseById(String id);
    List<Course> findCoursesBySubject(String subject);
    List<Course> findCoursesByNumber(int number);
    Course findCourseBySubjectAndNumber(String subject, int number);

}
