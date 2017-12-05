package com.rms.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.mongodb.BasicDBObject;
import com.mongodb.WriteResult;
import com.rms.employee.Employee;
import com.rms.employee.grade.Grade;

public class EmployeeRepositoryImpl implements EmployeeRepositoryCustom{
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	public void addGrade(String employeeId, Grade grade) {		
		
		Query query = new Query(Criteria.where("_id").is(employeeId) );
		List<Grade> existingGrade = getGradeAll(employeeId);
		if(existingGrade == null ) {			
			existingGrade = new ArrayList<Grade>();
		}
		
		System.out.println("existingGrade.size "+existingGrade.size());
		existingGrade.add(grade);
		Update update = new Update();
		update.set("gradeHistory", existingGrade);
		WriteResult writeResult = mongoTemplate.updateFirst(query, update, Employee.class);
	}

	@Override
	public List<Grade> getGradeAll(String employeeId) {
		Query query = new Query(Criteria.where("id").is(employeeId));
		List<Employee> employees= mongoTemplate.find(query, Employee.class);
		List<Grade> gradeList = employees.get(0).getGradeHistory();
		return gradeList;
	}

	@Override
	public void updateGrade(String employeeId, Grade grade) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteGrade(String employeeId, String gradeId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Grade getGradeById(String employeeId, String gradeId) {
		// TODO Auto-generated method stub
		return null;
	}
}
