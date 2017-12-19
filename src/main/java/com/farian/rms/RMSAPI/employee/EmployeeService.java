package com.farian.rms.RMSAPI.employee;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farian.rms.RMSAPI.exception.EntityNotFoundException;
import com.farian.rms.RMSAPI.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository repo;
	
	public List<Employee> getAllEmployee(){
		List<Employee> employee = new ArrayList<Employee>();
		repo.findAll().forEach(
				person ->{
			employee.add(person);
		});
		return employee;
	}
	
	public String addEmployee(Employee employee) {	
		return repo.save(employee).getId();
	}
	
	public Employee updateEmployee(Employee employee) {	
		String id = employee.getId();		
		if(!repo.isEmployeeExist(id)) {
			throw new EntityNotFoundException(id, "Employee id : "+id+" is not found");
		}
		return repo.save(employee);
	}
	
	public String deleteEmployee(String id) {			
		if(!repo.isEmployeeExist(id)) {
			throw new EntityNotFoundException(id, "Employee id : "+id+" is not found");
		}
		repo.delete(id);
		return "Employee with id "+ id + " has been deleted succesfully";
	}
	
}
