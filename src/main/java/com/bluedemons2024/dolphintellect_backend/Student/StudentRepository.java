package com.bluedemons2024.dolphintellect_backend.Student;

import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface StudentRepository extends Neo4jRepository<Student, String> {
    void deleteByName(String name);

//    Student findStudentByStudentID(String studentID);
}
