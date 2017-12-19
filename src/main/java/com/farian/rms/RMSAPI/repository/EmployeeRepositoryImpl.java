package com.farian.rms.RMSAPI.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.farian.rms.RMSAPI.employee.Employee;
import com.farian.rms.RMSAPI.employee.employmenthistory.EmploymentHistory;
import com.farian.rms.RMSAPI.employee.familymember.FamilyMember;
import com.farian.rms.RMSAPI.employee.gradehistory.GradeHistory;
import com.farian.rms.RMSAPI.exception.EntityNotFoundException;
import com.farian.rms.RMSAPI.utils.GeneralQuery;
import com.farian.rms.RMSAPI.utils.RMSConstanst;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.WriteResult;

public class EmployeeRepositoryImpl implements EmployeeRepositoryCustom{
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	@Autowired
	GeneralQuery generalQuery;
	
	public boolean isEmployeeExist(String employeeId) {
		System.out.println("wwww");
		Query query = new Query(Criteria.where("id").is(employeeId));
		Employee data = mongoTemplate.findOne(query, Employee.class);
		System.out.println("lll");
		if(data == null) return false;		
		return true;
	}
	
	public void addGrade(String employeeId, GradeHistory grade) {		
		try {
			generalQuery.addEmployeeAttributeListMember(employeeId, RMSConstanst.GRADE_HISTORY_NAME, grade, RMSConstanst.GRADE_HISTORY_CLASS_NAME);
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<GradeHistory> getGradeAll(String employeeId)  {
		
		
		
		/*Query query = new Query(Criteria.where("id").is(employeeId));
		List<Employee> employees= mongoTemplate.find(query, Employee.class);
		List<GradeHistory> gradeList = employees.get(0).getGradeHistory();
		return gradeList;*/
		
		try {
			return generalQuery.getEmployeeAttributeList(employeeId, RMSConstanst.GRADE_HISTORY_NAME);
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return null;
		
	}

	@Override
	public void updateGrade(String employeeId, GradeHistory grade) {		
		generalQuery.UpdateEmployeeAttributeListMember(employeeId, RMSConstanst.GRADE_HISTORY_ID, grade.getId(), RMSConstanst.GRADE_HISTORY_ARRAY_QUERY, grade);
	}

	@Override
	public void deleteGrade(String employeeId, String gradeId) {
		generalQuery.DeleteEmployeeAttributeListMember(employeeId, RMSConstanst.GRADE_HISTORY_ID, gradeId, RMSConstanst.GRADE_HISTORY_NAME);
	}

//	@Override
//	public GradeHistory getGradeById(String employeeId, String gradeId) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	public void addEmploye(String employeeId, EmploymentHistory employment) {		
		try {
			System.out.println("Repo");
			generalQuery.addEmployeeAttributeListMember(employeeId, RMSConstanst.EMPLOYMENT_HISTORY_NAME, employment, RMSConstanst.EMPLOYMENT_HISTORY_CLASS_NAME);
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<EmploymentHistory> getEmploymentAll(String employeeId)  {
		try {
			return generalQuery.getEmployeeAttributeList(employeeId, RMSConstanst.EMPLOYMENT_HISTORY_NAME);
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return null;
		
	}

	@Override
	public void updateEmployment(String employeeId, EmploymentHistory employment) {		
		generalQuery.UpdateEmployeeAttributeListMember(employeeId, RMSConstanst.EMPLOYMENT_HISTORY_ID, employment.getId(), RMSConstanst.EMPLOYMENT_HISTORY_ARRAY_QUERY, employment);
	}

	@Override
	public void deleteEmployment(String employeeId, String employmentId) {
		generalQuery.DeleteEmployeeAttributeListMember(employeeId, RMSConstanst.EMPLOYMENT_HISTORY_ID, employmentId, RMSConstanst.EMPLOYMENT_HISTORY_NAME);
	}

	@Override
	public GradeHistory getGradeById(String employeeId, String gradeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addEmployment(String employeeId, EmploymentHistory employment) {
		try {
			generalQuery.addEmployeeAttributeListMember(employeeId, RMSConstanst.EMPLOYMENT_HISTORY_NAME, employment, RMSConstanst.EMPLOYMENT_HISTORY_CLASS_NAME);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public EmploymentHistory getEmploymentById(String employeeId, String employmentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addFamily(String employeeId, FamilyMember family) {
		try {
			generalQuery.addEmployeeAttributeListMember(employeeId, RMSConstanst.FAMILY_MEMBER_NAME, family, RMSConstanst.FAMILY_MEMBER_CLASS_NAME);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

	@Override
	public List<FamilyMember> getFamilyAll(String employeeId) {
		List<FamilyMember> result = new ArrayList();
		try {
			result = generalQuery.getEmployeeAttributeList(employeeId, RMSConstanst.FAMILY_MEMBER_NAME);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public void updateFamily(String employeeId, FamilyMember family) {
		generalQuery.UpdateEmployeeAttributeListMember(employeeId, RMSConstanst.FAMILY_MEMBER_ID,  family.getId(), RMSConstanst.FAMILY_MEMBER_ARRAY_QUERY, family);
	}

	@Override
	public void deleteFamily(String employeeId, String familyId) {
		generalQuery.DeleteEmployeeAttributeListMember(employeeId, RMSConstanst.FAMILY_MEMBER_ID, familyId, RMSConstanst.FAMILY_MEMBER_NAME);
	}
	
}
