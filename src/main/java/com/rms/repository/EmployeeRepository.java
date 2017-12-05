package com.rms.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.rms.employee.Employee;
import com.rms.employee.grade.Grade;

public interface EmployeeRepository extends MongoRepository<Employee, String>, EmployeeRepositoryCustom{
	
}
