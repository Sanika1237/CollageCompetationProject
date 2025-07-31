package com.collageCompetation.collegeCompetationDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.collageCompetation.collegeCompetationDemo.entity")
@EnableJpaRepositories(basePackages = "com.collageCompetation.collegeCompetationDemo.repository")
@ComponentScan(basePackages = "com.collageCompetation.collegeCompetationDemo")
public class CollegeCompetationDemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(CollegeCompetationDemoApplication.class, args);
	}
}
