package com.farian.rms.RMSAPI.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;


import com.farian.rms.RMSAPI.employee.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.WriteResult;

public class GeneralQuery {

	@Autowired
	MongoTemplate mongoTemplate;
	
	public <E> List getEmployeeAttributeList(String employeeId, String employeeAttributeName) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
		Query query = new Query(Criteria.where("id").is(employeeId));
		Employee employee = mongoTemplate.findOne(query, Employee.class);
		List<E> gradeList = getEmployeeAttributeListByName(employee, employeeAttributeName);
		return gradeList;
	}
	
	
	public <E> List getEmployeeAttributeListByName(E object, String employeeAttributeName) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Field field = object.getClass().getDeclaredField(employeeAttributeName);    
		  field.setAccessible(true);
		  List value = (List) field.get(object);
		  return value;
	}
	
	

	public <E> void addEmployeeAttributeListMember(String employeeId, String employeeAttributeName, E updatedEmployeeAttributeMember, String employeeDTOAttributeName) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Query query = new Query(Criteria.where(RMSConstanst.ID_WITH_UNDERSCORE).is(employeeId) );
		System.out.println("general query");
		System.out.println("query "+ query);
		System.out.println("employeeId "+ employeeId +", employeeAttributeName "+ employeeAttributeName);
		List<E> existingGrade = getEmployeeAttributeList(employeeId, employeeAttributeName);
		if(existingGrade == null ) {			
			existingGrade = new ArrayList<E>();
		}
		
		existingGrade.add(updatedEmployeeAttributeMember);
		Update update = new Update();
		update.set(employeeAttributeName, existingGrade);
		System.out.println("update "+ update);
		mongoTemplate.updateFirst(query, update, Employee.class);
	}
	
	
	
	
	public <E> void UpdateEmployeeAttributeListMember(String employeeId, String personListIdAttribute, String personListIdValue,
			String personListUpdatedQuery, E updatedEmployeeAttributeMember) {
		
		Query query = new Query(Criteria.where(RMSConstanst.ID_WITH_UNDERSCORE).is(employeeId).and(personListIdAttribute).is(personListIdValue));		
		System.out.println("query "+query);
		Update update = new Update().set(personListUpdatedQuery, updatedEmployeeAttributeMember);
		System.out.println("update "+update);
		System.out.println("mongoTemplate "+mongoTemplate);
		mongoTemplate.updateFirst(query, update, Employee.class);

		/*
		 * Original sample query Query query = new
		 * Query(Criteria.where("_id").is(employeeId).and("gradeHistory._id").is(grade.
		 * getId())); Update update = new Update().set("gradeHistory.$", grade);
		 * mongoTemplate.updateFirst(query, update, Employee.class);
		 */
		
	}

	/*
	 * General query for deleting a list attribute inside Employee object. 
	 * Example list attribute for gradeHistory, family, history  
	 * Param : 
	 * employeeId,
	 * employeeListIdAttribute : name of attribute Id on mongodb. Example : "gradeHistory._id"
	 * gradeId : grade id which will be deleted
	 * employeeAttributeName : name of attribute name. Example : "gradeHistory"
	 * */

	public <E> void DeleteEmployeeAttributeListMember(String employeeId, String employeeListIdAttribute, String gradeId, String employeeAttributeName ) {
		Query query = new Query(Criteria.where(RMSConstanst.ID_WITH_UNDERSCORE).is(employeeId).and(employeeListIdAttribute).is(gradeId));
		Update update = new Update().pull(employeeAttributeName, Query.query(Criteria.where(RMSConstanst.ID_WITH_UNDERSCORE).is(gradeId)));
		mongoTemplate.updateFirst(query, update, Employee.class);
	}
	
	
	
	

}
