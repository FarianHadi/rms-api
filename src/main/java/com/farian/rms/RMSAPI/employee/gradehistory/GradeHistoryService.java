package com.farian.rms.RMSAPI.employee.gradehistory;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farian.rms.RMSAPI.repository.EmployeeRepository;


@Service
public class GradeHistoryService {
	@Autowired
	EmployeeRepository repo;
	
	public List<GradeHistory> addGradeHistory(String employeeId, GradeHistory grade) {
		grade.setId(UUID.randomUUID().toString());
		repo.addGrade(employeeId, grade);
		return getGradeAll(employeeId);
	}
	
	public List<GradeHistory> getGradeAll(String employeeId) {		
		List<GradeHistory> results = repo.getGradeAll(employeeId);
		return results;
	}	

	public List<GradeHistory> updateGrade(String employeeId, GradeHistory grade) {
		repo.updateGrade(employeeId, grade);
		return getGradeAll(employeeId);
	}
	
	public List<GradeHistory> deleteGrade(String employeeId, String gradeId){
		repo.deleteGrade(employeeId, gradeId);
		return getGradeAll(employeeId);
	}
}
