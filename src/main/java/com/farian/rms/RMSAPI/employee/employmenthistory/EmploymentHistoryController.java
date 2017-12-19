package com.farian.rms.RMSAPI.employee.employmenthistory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.farian.rms.RMSAPI.employee.gradehistory.GradeHistory;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(tags = "Employment History")
public class EmploymentHistoryController {
	
	@Autowired
	EmployementHistoryService service;
	
	@RequestMapping(value="/employees/{id}/employments", method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Add Employement History", notes = "Insert new employment item on grade history list", response = EmploymentHistory.class)
	@ApiResponses({ @ApiResponse(code = 201, message = "Success", response = EmploymentHistory.class) })
	public List<EmploymentHistory> addEmployment(@PathVariable String id, @RequestBody EmploymentHistory employment) {
		System.out.println("controlers");
		return service.addEmploymentHistory(id, employment);		
	}
	
	@RequestMapping(value="/employees/{id}/employments",  method = RequestMethod.GET)
	@ApiOperation(value = "Get All Employement History", notes = "gather grade history list", response = EmploymentHistory.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "Success", response = EmploymentHistory.class) })
	public List<EmploymentHistory> getEmploymentAll(@PathVariable String id) {		
		return service.getEmploymentAll(id);		
	}
	
	@RequestMapping(value="/employees/{id}/employments", method=RequestMethod.PUT)
	@ApiOperation(value = "Update Employement History", notes = "update a grade history list", response = EmploymentHistory.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "Success", response = EmploymentHistory.class) })
	public List<EmploymentHistory> updateEmployment(@PathVariable String id, @RequestBody EmploymentHistory employments) {
		return service.updateEmployment(id, employments );		
	}
	
	
	@RequestMapping(value="/employees/{id}/employments/{employmentid}", method=RequestMethod.DELETE)
	@ApiOperation(value = "Delete Employement History", notes = "Delete one of history list", response = EmploymentHistory.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "Success", response = EmploymentHistory.class) })
	public List<EmploymentHistory> deleteEmployment(@PathVariable String id, @PathVariable String employmentid){
		return service.deleteEmployment(id, employmentid);
	}
	
	
}
