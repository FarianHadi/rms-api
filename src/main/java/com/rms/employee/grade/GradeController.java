package com.rms.employee.grade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@Api(tags = "Grade History API")
public class GradeController {
	
	@Autowired
	GradeService service;
	
	@RequestMapping(value="/employee/{id}/grade", method=RequestMethod.POST)
	public void addGrade(@PathVariable String id, @RequestBody Grade grade) {
		service.addGradeHistory(id, grade);		
	}
	
	@RequestMapping("/employee/{id}/grade")
	public List<Grade> getGradeAll(@PathVariable String id) {		
		return service.getGradeAll(id);		
	}
	
}
