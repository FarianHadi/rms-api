package com.farian.rms.RMSAPI.employee.familymember;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farian.rms.RMSAPI.repository.EmployeeRepository;


@Service
public class FamilyMemberService {
	
	@Autowired
	EmployeeRepository repo;
	
	public List<FamilyMember> addFamily(String employeeId, FamilyMember family) {
		family.setId(UUID.randomUUID().toString());
		repo.addFamily(employeeId, family);
		return getFamilyAll(employeeId);
	}
	
	public List<FamilyMember> getFamilyAll(String employeeId) {		
		List<FamilyMember> results = repo.getFamilyAll(employeeId);
		return results;
	}	

	public List<FamilyMember> updateFamily(String employeeId, FamilyMember family) {
		repo.updateFamily(employeeId, family);
		return getFamilyAll(employeeId);
	}
	
	public List<FamilyMember> deleteFamily(String employeeId, String familyId){
		repo.deleteFamily(employeeId, familyId);
		return getFamilyAll(employeeId);
	}
}
