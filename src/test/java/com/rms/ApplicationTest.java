package com.rms;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rms.employee.grade.Grade;
import com.rms.employee.grade.GradeService;

@RunWith(SpringRunner.class)
@SpringBootTest
// @WebMvcTest
public class ApplicationTest {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private GradeService gradeService;	
	
	@Test
	public void contextLoads() throws Exception {
		
       
//        
//        ObjectMapper mapper = new ObjectMapper();
//        String jsonNewEmp = mapper.writeValueAsString(getDummyGradeList());
//        
//        given(gradeService.getGradeAll(Mockito.anyString())).willReturn(getDummyGradeList()); 
//        
//        mockMvc.perform(get("/employee/12341234/grade")                    
//                     .contentType(MediaType.APPLICATION_JSON)
//                     .content(jsonNewEmp))
//        .andExpect(status().isCreated())
//        .andExpect(header().string("location", equalTo("http://localhost/api/user/employees/1")))
//        .andDo(print())
//        .andReturn();
//        
//        verify(employeeService).saveEmployee(Mockito.any(Employee.class));

		
		
		
		
	
		/*when(gradeService.getGradeAll(Mockito.anyString())).thenReturn(getDummyGradeList());
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/employee/12341234/grade/").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();		
		System.out.println(result.getResponse());*/
	}
	
	private List<Grade> getDummyGradeList(){
		List<Grade> result = new ArrayList<Grade>();
		
		Grade grade1 = new Grade();
		grade1.setDs("DS01");
		grade1.setEndDate("20 Sept 2020");
		grade1.setGrade("SE-JP");
		grade1.setStartDate("9 Jan 2010");
		grade1.setId("Raisa");
		Grade grade2 = new Grade();
		grade2.setDs("DS04");
		grade2.setEndDate("20 Sept 2020");
		grade2.setGrade("SE-PG");
		grade2.setStartDate("9 Jan 2010");
		grade2.setId("Acha");
		result.add(grade1);
		result.add(grade2);
		
		return result;
		
		
	}
}
