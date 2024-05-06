package com.bluedemons2024.dolphintellect_backend.EnrolledCourse;

import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface EnrolledCourseRepository extends Neo4jRepository<EnrolledCourse, Long> {

}

