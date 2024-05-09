package com.bluedemons2024.dolphintellect_backend.config;

import org.neo4j.driver.AuthToken;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.neo4j.Neo4jAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.core.transaction.Neo4jTransactionManager;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableNeo4jRepositories(basePackages = {"com.bluedemons2024.dolphintellect_backend"}, transactionManagerRef = "neo4jTransactionManager")
@EnableTransactionManagement
public class Neo4jConfig extends Neo4jAutoConfiguration {

    @Value("${spring.neo4j.uri}")
    private String neo4jUri;
    @Value("${spring.neo4j.authentication.username}")
    private String username;
    @Value("${spring.neo4j.authentication.password}")
    private String password;

    @Bean
    public Driver getConfiguration() {
        return GraphDatabase.driver(this.neo4jUri, AuthTokens.basic(this.username, this.password));
    }

    @Bean(name = "neo4jTransactionManager")
    public Neo4jTransactionManager transactionManager() {
        return new Neo4jTransactionManager(getConfiguration());
    }

}
