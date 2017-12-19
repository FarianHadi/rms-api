package com.rms;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.rms.employee.grade.Grade;
import com.rms.employee.grade.GradeController;
import com.rms.employee.grade.GradeService;

import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.when;

// @RunWith(SpringJUnit4ClassRunner.class)

@RunWith(SpringRunner.class)
@WebMvcTest(GradeController.class)
public class GradeControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private GradeService gradeService;	
	
	
	@Test
	public void getGradeAll_shoudReturnAllGradeAnEmployee() throws Exception {
		
		when(gradeService.getGradeAll(Mockito.anyString())).thenReturn(getDummyGradeList());
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/employee/12341234/grade/").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();		
		System.out.println(result.getResponse());
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
