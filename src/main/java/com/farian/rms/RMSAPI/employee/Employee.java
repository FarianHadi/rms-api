package com.farian.rms.RMSAPI.employee;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;

import com.farian.rms.RMSAPI.employee.employmenthistory.EmployementHistoryService;
import com.farian.rms.RMSAPI.employee.employmenthistory.EmploymentHistory;
import com.farian.rms.RMSAPI.employee.familymember.FamilyMember;
import com.farian.rms.RMSAPI.employee.gradehistory.GradeHistory;

@EntityScan
public class Employee {
	@Id
	private String id;	
	@NotNull
	private String firstName;
	private String lastName;
	private String gender;
	private String dateOfBirth;
	private String nationality;
	private String maritalStatus;
	private String phone;
	private String division;
	private String subDivision;
	private String status;
	private String suspendDate;
	private String hiredDate;
	private String email;
	private String grade;	
	
	private List<GradeHistory> gradeHistory = new ArrayList<GradeHistory>();
	private List<FamilyMember> familyMember = new ArrayList<FamilyMember>();
	private List<EmploymentHistory> employmentHistory = new ArrayList<EmploymentHistory>();
	
	
	public Employee() {
		
	}	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getSubDivision() {
		return subDivision;
	}

	public void setSubDivision(String subDivision) {
		this.subDivision = subDivision;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSuspendDate() {
		return suspendDate;
	}

	public void setSuspendDate(String suspendDate) {
		this.suspendDate = suspendDate;
	}

	public String getHiredDate() {
		return hiredDate;
	}

	public void setHiredDate(String hiredDate) {
		this.hiredDate = hiredDate;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public List<GradeHistory> getGradeHistory() {
		return gradeHistory;
	}


	public void setGradeHistory(List<GradeHistory> grade) {
		this.gradeHistory = grade;
	}

	public List<FamilyMember> getFamilyMember() {
		return familyMember;
	}

	public void setFamilyMember(List<FamilyMember> familyMember) {
		this.familyMember = familyMember;
	}

	public List<EmploymentHistory> getEmploymentHistory() {
		return employmentHistory;
	}

	public void setEmploymentHistory(List<EmploymentHistory> employmentHistory) {
		this.employmentHistory = employmentHistory;
	}
	
	

	
}
