package com.farian.rms.RMSAPI.employee.familymember;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.farian.rms.RMSAPI.employee.employmenthistory.EmploymentHistory;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(tags = "Family Member")
public class FamilyMemberController {
	
	@Autowired
	FamilyMemberService service;
	
	@RequestMapping(value="/employees/{id}/families", method=RequestMethod.POST)
	@ApiOperation(value = "Add Family Member", notes = "Adding family member data which is owned by an employee", response = FamilyMember.class)
	@ApiResponses({ @ApiResponse(code = 201, message = "Success", response = FamilyMember.class) })
	public List<FamilyMember> addFamily(@PathVariable String id, @RequestBody FamilyMember family) {
		return service.addFamily(id, family);		
	}
	
	@RequestMapping(value="/employees/{id}/families",  method = RequestMethod.GET)
	@ApiOperation(value = "Get All Family Member Data", notes = "View employee's family member", response = FamilyMember.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "Success", response = FamilyMember.class) })
	public List<FamilyMember> getFamilyAll(@PathVariable String id) {		
		return service.getFamilyAll(id);		
	}
	
	@RequestMapping(value="/employees/{id}/families", method=RequestMethod.PUT)
	@ApiOperation(value = "Update Family Member Data", notes = "Updating the details of family member data", response = FamilyMember.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "Success", response = FamilyMember.class) })
	public List<FamilyMember> updateFamily(@PathVariable String id, @RequestBody FamilyMember family) {
		return service.updateFamily(id, family );		
	}
	
	@RequestMapping(value="/employees/{id}/families/{familyid}", method=RequestMethod.DELETE)
	@ApiOperation(value = "Delete Family Member Data", notes = "Removing employee's family member", response = FamilyMember.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "Success", response = FamilyMember.class) })
	public List<FamilyMember> deleteFamily(@PathVariable String id, @PathVariable String familyid){
		return service.deleteFamily(id, familyid);
	}
	
	
}
