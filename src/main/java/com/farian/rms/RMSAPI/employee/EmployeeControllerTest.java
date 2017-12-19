package com.farian.rms.RMSAPI.employee;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.farian.rms.RMSAPI.AppConfig;
import com.farian.rms.RMSAPI.employee.employmenthistory.EmploymentHistory;
import com.farian.rms.RMSAPI.employee.familymember.FamilyMember;
import com.farian.rms.RMSAPI.employee.gradehistory.GradeHistory;
import com.farian.rms.RMSAPI.exception.EntityNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;



@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
@Import({AppConfig.class}) 
public class EmployeeControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private  EmployeeService service;
	
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));
	
	String data1 = "{\r\n" + 
			"    \"id\" : \"5a25f28eaf230e34a810e741\",  \r\n" + 
			"    \"firstName\":\"asdfasdfasdfasdfasdfasdfasdfasdfasdfasdf\",\r\n" + 
			"    \"lastName\" : \"eqrwersdfqwefasdf\",\r\n" + 
			"    \"gender\" : \"male\",\r\n" + 
			"    \"dateOfBirth\" : \"12 February 2000\",\r\n" + 
			"    \"nationality\" : \"USA\",\r\n" + 
			"    \"maritalStatus\" : \"single\",\r\n" + 
			"    \"phone\" : \"1234\",\r\n" + 
			"    \"division\" : \"Justice League\",\r\n" + 
			"    \"subDivision\" : \"Security\",\r\n" + 
			"    \"status\" : \"Active\",\r\n" + 
			"    \"suspendDate\" : \"9 OCt 2079\",\r\n" + 
			"    \"hiredDate\" : \"3 April 2015\",\r\n" + 
			"    \"email\" : \"flash@gmail.com\",\r\n" + 
			"    \"gradeHistory\" : [],\r\n" + 
			"    \"familyMember\" : [],\r\n" + 
			"    \"employmentHistory\" : []\r\n" + 
			"}\r\n" + 
			"";
	

	@Test
	public void addEmployee_shouldAddDataSuccessfully() throws Exception {
		Employee dummyData = constructEmployeeObject();	
		dummyData.setId("5a25f28eaf230e34a810e741");
        ObjectMapper mapper = new ObjectMapper();
        String jsonNewEmp = mapper.writeValueAsString(dummyData);
		when(service.addEmployee(Mockito.any(Employee.class))).thenReturn("5a25f28eaf230e34a810e741");
		ResultActions resultAction =  mockMvc.perform(post("/employees/")
				.content(jsonNewEmp)
				.contentType(contentType)
				)
		.andExpect(status().isOk());
		
	}
	
	@Test
	public void updateEmployee_shouldReturnErrorNotFound_whenEmployeeDoesnotExist() throws Exception {
		Employee employee = new Employee();
		employee.setId("5a25f28eaf230e34a810e741");
		when(service.updateEmployee(employee)).thenThrow(new EntityNotFoundException("5a25f28eaf230e34a810e741", "Employee id : 5a25f28eaf230e34a810e741 is not found")) ;
		mockMvc.perform(put("/employee/5a25f28eaf230e34a810e741/")
				.content(data1)
				.contentType(contentType)
				)
		.andExpect(status().isNotFound());	
	}
	
	@Test
	public void updateEmployee_shouldUpdateEmployeeDataSuccessfuly() throws Exception {	
		Employee dummyData = constructEmployeeObject();	
		dummyData.setId("5a25f28eaf230e34a810e741");
        ObjectMapper mapper = new ObjectMapper();
        String jsonNewEmp = mapper.writeValueAsString(dummyData);
		when(service.updateEmployee(Mockito.any(Employee.class))).thenReturn(dummyData);
		ResultActions resultAction =  mockMvc.perform(put("/employees/5a25f28eaf230e34a810e741/")
				.content(jsonNewEmp)
				.contentType(contentType)
				)
		.andExpect(status().isOk());
		checkResponseSingleData(resultAction, dummyData);
	}
	
	@Test
	public void deleteEmployee_shouldReturnErrorNotFound_whenEmployeeDoesnotExist() throws Exception {
		Employee employee = new Employee();
		employee.setId("5a25f28eaf230e34a810e741");
		when(service.deleteEmployee(Mockito.any(String.class))).thenThrow(new EntityNotFoundException("5a25f28eaf230e34a810e741", "Employee id : 5a25f28eaf230e34a810e741 is not found")) ;
		mockMvc.perform(delete("/employee/5a25f28eaf230e34a810e741/")
				.content(data1)
				.contentType(contentType)
				)
		.andExpect(status().isNotFound());	
	}
	
	@Test
	public void deleteEmployee_shouldDeleteEmployeeDataSuccessfuly() throws Exception {	
		Employee dummyData = constructEmployeeObject();	
		String id = "5a25f28eaf230e34a810e741";
		dummyData.setId(id);
        ObjectMapper mapper = new ObjectMapper();
        String jsonNewEmp = mapper.writeValueAsString(dummyData);
		when(service.deleteEmployee(Mockito.any(String.class))).thenReturn("Employee with id "+ id + " has been deleted succesfully") ;
		ResultActions resultAction =  mockMvc.perform(delete("/employees/5a25f28eaf230e34a810e741/")
				.content(jsonNewEmp)
				.contentType(contentType)
				)
		.andExpect(status().isOk());		
	}
	
	
	
	
	
	
	private void checkResponseSingleData(ResultActions resultAction, Employee expectedResult ) throws Exception {
		resultAction.andExpect(jsonPath("$.id", is(expectedResult.getId())))
		.andExpect(jsonPath("$.firstName", is(expectedResult.getFirstName())))
		.andExpect(jsonPath("$.lastName", is(expectedResult.getLastName())))
		.andExpect(jsonPath("$.gender", is(expectedResult.getGender())))
		.andExpect(jsonPath("$.dateOfBirth", is(expectedResult.getDateOfBirth())))
		.andExpect(jsonPath("$.nationality", is(expectedResult.getNationality())))
		.andExpect(jsonPath("$.maritalStatus", is(expectedResult.getMaritalStatus())))
		.andExpect(jsonPath("$.phone", is(expectedResult.getPhone())))
		.andExpect(jsonPath("$.division", is(expectedResult.getDivision())))
		.andExpect(jsonPath("$.subDivision", is(expectedResult.getSubDivision())))
		.andExpect(jsonPath("$.status", is(expectedResult.getStatus())))
		.andExpect(jsonPath("$.suspendDate", is(expectedResult.getSuspendDate())))
		.andExpect(jsonPath("$.hiredDate", is(expectedResult.getHiredDate())))
		.andExpect(jsonPath("$.email", is(expectedResult.getEmail())))
		.andExpect(jsonPath("$.grade", is(expectedResult.getGrade())))
		.andExpect(jsonPath("$.gradeHistory").doesNotExist())
		.andExpect(jsonPath("$.familyMember").doesNotExist())
		.andExpect(jsonPath("$.employmentHistoryList").doesNotExist())		
		;
	}
	
	
	private Employee constructEmployeeObject() {
		Employee employee = new Employee();
		List gradeList = new ArrayList<>();
		GradeHistory grade = new GradeHistory();
		grade.setId("5a25f28eaf230e34a810e741");
		grade.setDs("DS03");
		grade.setGrade("SE-JP");
		grade.setStartDate("31 Desember 2010");
		grade.setEndDate("7 January 2011");		
		gradeList.add(grade);
		
		List familyMemberList = new ArrayList<>();
		FamilyMember family = new FamilyMember();
		family.setId("13e28fef-63a6-4299-ad93-934750c34d44");
		family.setFirstName("Supardi");
		family.setLastName("Nasir");
		family.setType("Child");
		family.setDob("9 February 2001");
		familyMemberList.add(family);
		
		List employmentHistoryList = new ArrayList<>();
		EmploymentHistory employmentHistory = new EmploymentHistory();
		employmentHistory.setCompany("Mitrais");
		employmentHistory.setId("13e28fef-63a6-4299-ad93-934750c34d44");
		employmentHistory.setJobDescription("Junior Programmer");
		employmentHistory.setPeriode("2010 - 2011");
		employmentHistory.setPosition("SE-JP");
		employmentHistoryList.add(employmentHistory);

		employee.setId("5a25d2cd4450c2572063df7d");
		employee.setFirstName("Superman");
		employee.setLastName("Clark Ken");
		employee.setGender("male");
		employee.setDateOfBirth("6 November 2000");
		employee.setNationality("Indonesia");		
		employee.setMaritalStatus("single");
		employee.setPhone("61234");
		employee.setDivision("CDC");
		employee.setSubDivision("Justice Leage");
		employee.setStatus("Active");		
		employee.setSuspendDate("7 January 2011");
		employee.setHiredDate("31 Desember 2010");
		employee.setEmail("Superman@gmail.com");		
		employee.setGrade("SE-PG");
		// employee.setGradeHistory(gradeList);	
		// employee.setFamilyMember(familyMemberList);
		// employee.setEmploymentHistory(employmentHistoryList);
		
		return employee;
	}
	
	
	
	
	
}
