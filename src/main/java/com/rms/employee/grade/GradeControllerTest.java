package com.rms.employee.grade;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.StreamingHttpOutputMessage.Body;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.rms.employee.grade.Grade;
import com.rms.employee.grade.GradeController;
import com.rms.employee.grade.GradeService;

import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;

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
		// RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/employee/12341234/grade/").accept(MediaType.APPLICATION_JSON);
		
		mockMvc.perform(get("/employee/123454123/grade/"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[0].id", is("Raisa")));
		
		
		
		System.out.println("yyyyyyyyyyyyyyyyyyyyyyyyyyyy");
		// .andExpect(model().size(2));	
		// .andExpect(jsonPath("$", hasSize(1)));
		
		
		mockMvc.perform(get("/employee/123454123/grade/")).andReturn();
		
		verify(gradeService, times(1)).getGradeAll("");
		verifyNoMoreInteractions(gradeService);
		// JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
		
	}

	private List<Grade> getDummyGradeList() {
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
