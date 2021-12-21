package com.example.db;

import jakarta.annotation.sql.DataSourceDefinition;

@DataSourceDefinition(
        className = "com.mysql.cj.jdbc.MysqlConnectionPoolDataSource",
        name = "java:/jdbc/personalBudgetAnnotation",
        serverName = "localhost",
        databaseName = "quevedodb",
        portNumber = 3307,
        user = "quevedo",
        password = "quevedo",
        initialPoolSize = 1,
        minPoolSize = 1,
        maxPoolSize = 5,
        maxIdleTime = 300
)
public class DataSourceConfiguration {
}