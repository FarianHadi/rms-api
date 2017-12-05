package com.rms.repository;

import java.util.List;

import com.rms.employee.grade.Grade;

public interface EmployeeRepositoryCustom {
	public void addGrade(String employeeId, Grade grade);
	public List<Grade> getGradeAll(String employeeId);
	public Grade getGradeById(String employeeId, String gradeId);
	public void updateGrade(String employeeId, Grade grade);
	public void deleteGrade(String employeeId, String gradeId);
}
