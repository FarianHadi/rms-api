package com.farian.rms.RMSAPI.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.farian.rms.RMSAPI.employee.Employee;
import com.farian.rms.RMSAPI.employee.familymember.FamilyMember;

public interface EmployeeRepository extends MongoRepository<Employee, String>, EmployeeRepositoryCustom{
	
	
	
	
}
