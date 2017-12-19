package com.farian.rms.RMSAPI.repository;

import java.util.List;

import com.farian.rms.RMSAPI.employee.Employee;
import com.farian.rms.RMSAPI.employee.employmenthistory.EmploymentHistory;
import com.farian.rms.RMSAPI.employee.familymember.FamilyMember;
import com.farian.rms.RMSAPI.employee.gradehistory.GradeHistory;


public interface EmployeeRepositoryCustom {
	// Employee operation
	public boolean isEmployeeExist(String employeeId);
		
	
	public void addGrade(String employeeId, GradeHistory grade) ;
	public List<GradeHistory> getGradeAll(String employeeId);
	public GradeHistory getGradeById(String employeeId, String gradeId);
	public void updateGrade(String employeeId, GradeHistory grade);
	public void deleteGrade(String employeeId, String gradeId);
	public void addEmployment(String employeeId, EmploymentHistory employment) ;
	public List<EmploymentHistory> getEmploymentAll(String employeeId);
	public EmploymentHistory getEmploymentById(String employeeId, String employmentId);
	public void updateEmployment(String employeeId, EmploymentHistory employment);
	public void deleteEmployment(String employeeId, String employmentId);	
	public void addFamily(String employeeId, FamilyMember family);
	public List<FamilyMember> getFamilyAll(String employeeId);
	public void updateFamily(String employeeId, FamilyMember family);
	public void deleteFamily(String employeeId, String familyId);
}
