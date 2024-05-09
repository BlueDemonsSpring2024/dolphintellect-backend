package com.bluedemons2024.dolphintellect_backend.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.bluedemons2024.dolphintellect_backend", transactionManagerRef = "mysqlTransactionManager")
public class MySQLConfig {
    @Bean(name = "mysql")
    @ConfigurationProperties(prefix = "spring.mysql")
    public DataSource mysqlDataSource() {
        return DataSourceBuilder.create().driverClassName("com.mysql.cj.jdbc.Driver").build();
    }

    @Bean(name="mysqlTransactionManager")
    public JpaTransactionManager mysqlTransactionManager(LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) throws Exception {
        return new JpaTransactionManager(entityManagerFactoryBean.getObject());
    }





}
