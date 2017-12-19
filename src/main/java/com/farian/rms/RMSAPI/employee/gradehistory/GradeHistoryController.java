package com.farian.rms.RMSAPI.employee.gradehistory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.farian.rms.RMSAPI.employee.EmployeeDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(tags = "Grade History")
public class GradeHistoryController {
	
	@Autowired
	GradeHistoryService service;
	
	@RequestMapping(value="/employees/{id}/grade", method=RequestMethod.POST)
	@ApiOperation(value = "Add Grade History", notes = "Insert new grade on grade history list", response = GradeHistory.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "Success", response = GradeHistory.class) })
	public List<GradeHistory> addGrade(@PathVariable String id, @RequestBody GradeHistory grade) {
		System.out.println("aaaaaaaaaa");
		List result = service.addGradeHistory(id, grade);
		System.out.println("bbbbb "+result.toString());
		return result;
	}
	
	@RequestMapping(value="/employees/{id}/grade", method = RequestMethod.GET)
	@ApiOperation(value = "Get All Grade History", notes = "Gather all grade history which are owned by employee", response = GradeHistory.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "Success", response = GradeHistory.class) })
	public List<GradeHistory> getGradeAll(@PathVariable String id) {	
		return service.getGradeAll(id);
	}
	
	@RequestMapping(value="/employees/{id}/grade", method=RequestMethod.PUT)
	@ApiOperation(value = "Update Grade History", notes = "Update the employee's history", response = GradeHistory.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "Success", response = GradeHistory.class) })
	
	public List<GradeHistory> updateGrade(@PathVariable String id, @RequestBody GradeHistory grade) {
		return service.updateGrade(id, grade );		
	}
	
	@RequestMapping(value="/employees/{id}/grade/{gradeid}", method=RequestMethod.DELETE)
	@ApiOperation(value = "Delete Grade History", notes = "delete grade history which are owned by employee", response = GradeHistory.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "Success", response = GradeHistory.class) })
	public List<GradeHistory> deleteGrade(@PathVariable String id, @PathVariable String gradeid){
		return service.deleteGrade(id, gradeid);
	}
	
	
}
