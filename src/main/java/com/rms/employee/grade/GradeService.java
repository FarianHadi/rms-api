package com.rms.employee.grade;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rms.repository.EmployeeRepository;

@Service
public class GradeService {
	private UUID uuid = UUID.randomUUID();
	@Autowired
	EmployeeRepository repo;
	
	public void addGradeHistory(String employeeId, Grade grade) {
		grade.setId(uuid.toString());
		repo.addGrade(employeeId, grade);
	}
	
	public List<Grade> getGradeAll(String employeeId) {		
		List<Grade> results = repo.getGradeAll(employeeId);
		return results;
	}	
}
