package com.mintlolly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.mintlolly")
public class StudentSelectionAnalysisWebApplication {
	public static void main(String[] args) {
		SpringApplication.run(StudentSelectionAnalysisWebApplication.class, args);
	}
}
