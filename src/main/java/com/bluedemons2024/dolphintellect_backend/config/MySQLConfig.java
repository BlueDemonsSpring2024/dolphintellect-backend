package com.bluedemons2024.dolphintellect_backend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.sql.init.dependency.DependsOnDatabaseInitialization;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.bluedemons2024.dolphintellect_backend", transactionManagerRef = "mysqlTransactionManager")
public class MySQLConfig {

    @Value("${MYSQL_PASSWORD}")
    private String mysqlPassword;

    @Value("${MYSQL_USER}")
    private String mysqlUser;

    @Value("jdbc:mysql://mysql_container:3306/dolphintellect")
    private String mysqlURL;

    @Value("com.mysql.cj.jdbc.Driver")
    private String mysqlDriver;

    @Bean(name = "mysql")
    @ConfigurationProperties(prefix = "spring.mysql")
    @DependsOn("dotenv")
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .driverClassName(this.mysqlDriver)
                .url(this.mysqlURL)
                .username(this.mysqlUser)
                .password(this.mysqlPassword)
                .build();
    }

    @Bean(name="mysqlTransactionManager")
    public JpaTransactionManager mysqlTransactionManager(LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) throws Exception {
        return new JpaTransactionManager(entityManagerFactoryBean.getObject());
    }
}
