package com.example.HarareProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HarareProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(HarareProjectApplication.class, args);
	}

}

/*
* spring.application.name=HarareProject
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/HarareDB
spring.datasource.username=root
spring.datasource.password=Absam401$
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQLDialect
server.port=8080
spring.security.user.password=user22
spring.security.user.name=user

*
* */