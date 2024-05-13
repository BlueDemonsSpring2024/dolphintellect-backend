package com.bluedemons2024.dolphintellect_backend.GradeItem;

import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface GradeItemRepository extends Neo4jRepository<GradeItem, Long> {
    GradeItem findGradeItemById(Long id);
}
