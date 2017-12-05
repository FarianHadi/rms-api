package com.rms.employee;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rms.repository.EmployeeRepository;

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
		repo.save(employee);
		return "Success";
	}
	
	public void updateEmployee(Employee employee) {
		repo.save(employee);
	}
	
	public void deleteEmployee(String id) {
		repo.delete(id);
	}
	
}
