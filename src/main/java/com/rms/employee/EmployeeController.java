package com.rms.employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

@RestController
@Api(tags = "Employee API")
public class EmployeeController {
	
	@Autowired
	private EmployeeService service;
	
	
	@RequestMapping(method=RequestMethod.POST, value="/employee", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String addEmployee(@RequestBody Employee employee) {
		return service.addEmployee(employee);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/employee")
	public List<Employee> getAllEmployees(){
		return service.getAllEmployee();
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/employee/{id}")
	public void updateEmployee(@RequestBody Employee employee) {
		service.updateEmployee(employee);
	}
	
	@ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "The employee ID", required = true,  defaultValue="Niklas")
      })
	@RequestMapping(method=RequestMethod.DELETE, value="/employee/{id}")
	public void deleteEmployee(@PathVariable String id) {
		service.deleteEmployee(id);
	}
	
	
}
