package com.example.db;


import javax.annotation.sql.DataSourceDefinition;

@DataSourceDefinition(
        className = "com.mysql.cj.jdbc.MysqlConnectionPoolDataSource",
        name = "java:/jdbc/JPAentity",
        serverName = "localhost",
        databaseName = "quevedodb",
        portNumber = 3308,
        user = "quevedo",
        password = "quevedo",
        initialPoolSize = 1,
        minPoolSize = 1,
        maxPoolSize = 5,
        maxIdleTime = 300
)
public class DataSourceConfiguration {
}