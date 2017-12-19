package com.farian.rms.RMSAPI.employee.employmenthistory;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farian.rms.RMSAPI.repository.EmployeeRepository;


@Service
public class EmployementHistoryService {
	
	@Autowired
	EmployeeRepository repo;
	
	public List<EmploymentHistory> addEmploymentHistory(String employeeId, EmploymentHistory employment) {
		System.out.println("service");
		employment.setId(UUID.randomUUID().toString());
		repo.addEmployment(employeeId, employment);
		return getEmploymentAll(employeeId);
	}
	
	public List<EmploymentHistory> getEmploymentAll(String employeeId) {		
		List<EmploymentHistory> results = repo.getEmploymentAll(employeeId);
		return results;
	}	

	public List<EmploymentHistory> updateEmployment(String employeeId, EmploymentHistory employment) {
		repo.updateEmployment(employeeId, employment);
		return getEmploymentAll(employeeId);
	}
	
	public List<EmploymentHistory> deleteEmployment(String employeeId, String employmentId){
		repo.deleteEmployment(employeeId, employmentId);
		return getEmploymentAll(employeeId);
	}
}
