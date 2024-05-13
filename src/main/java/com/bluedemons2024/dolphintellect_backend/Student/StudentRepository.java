package com.bluedemons2024.dolphintellect_backend.Student;

import com.bluedemons2024.dolphintellect_backend.GradeItem.GradeItem;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface StudentRepository extends Neo4jRepository<Student, String> {
    void deleteByName(String name);

    GradeItem findGradeItemById(Long id);

//    GradeItem findGradeItemByIdAndId(Long id, String studentID);

//    Student findStudentByStudentID(String studentID);
}
