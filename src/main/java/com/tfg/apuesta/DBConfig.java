package com.tfg.apuesta;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DBConfig{

       @Bean
       public DataSource getDataSource(){
          DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
          dataSourceBuilder.driverClassName("org.h2.Driver");
          dataSourceBuilder.url("jdbc:h2:file:C:/temp/test");
          dataSourceBuilder.username("sa");
          dataSourceBuilder.password("");
          return dataSourceBuilder.build();
       }
       
       @Bean(name = "mySqlDataSource")
       @Primary
       public DataSource mySqlDataSource(){
           DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
           dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
           dataSourceBuilder.url("jdbc:mysql://localhost:3306/TFG?useUnicode=true&characterEncoding=utf8&createDatabaseIfNotExist=true");
           dataSourceBuilder.username("root");
           dataSourceBuilder.password("1234");
           return dataSourceBuilder.build();
       }
}
