package com.employeeservice.employeeserviceapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.employeeservice"})
public class EmployeeserviceapplicationApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeserviceapplicationApplication.class, args);
	}

}
