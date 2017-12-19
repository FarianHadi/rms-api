package com.farian.rms.RMSAPI.employee.gradehistory;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.configuration.injection.MockInjection;
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

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;

// @RunWith(SpringJUnit4ClassRunner.class)

@RunWith(SpringRunner.class)
@WebMvcTest(GradeHistoryController.class)
public class GradeHistoryControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private GradeHistoryService gradeService;

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	@Test
	public void getGradeAll_shoudReturnAllGradeAnEmployee() throws Exception {
		when(gradeService.getGradeAll("123454123")).thenReturn(getDummyGradeList());
		mockMvc.perform(get("/employees/123454123/grade/")).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id", is("Raisa"))).andExpect(jsonPath("$", hasSize(2)));

		verify(gradeService, times(1)).getGradeAll("123454123");
		verifyNoMoreInteractions(gradeService);

	}

	@Test
	public void addGrade_shoudStoreDataCorrectly() throws Exception {
		when(gradeService.addGradeHistory(Mockito.any(String.class), Mockito.any(GradeHistory.class)))
				.thenReturn(getDummyGradeList());
		ObjectMapper mapper = new ObjectMapper();
		String jsonNewGrade = mapper.writeValueAsString(getDummyGrade());
		mockMvc.perform(post("/employees/123454123/grade/").content(jsonNewGrade).contentType(contentType))
				.andExpect(status().isOk()).andExpect(jsonPath("$[0].id", is("Raisa")))
				.andExpect(jsonPath("$", hasSize(2)));
		;
	}

	@Test
	public void updateGrade_shoudUpdateDataCorrectly() throws Exception {
		when(gradeService.updateGrade(Mockito.any(String.class), Mockito.any(GradeHistory.class)))
				.thenReturn(getDummyGradeList());
		ObjectMapper mapper = new ObjectMapper();
		GradeHistory updatedGrade = getDummyGrade();
		updatedGrade.setGrade("MGF");
		updatedGrade.setStartDate("20 Sept 2020");
		updatedGrade.setEndDate("1 December 2016");
		String jsonNewGrade = mapper.writeValueAsString(updatedGrade);
		mockMvc.perform(put("/employees/123454123/grade/").content(jsonNewGrade).contentType(contentType))
				.andExpect(status().isOk()).andExpect(jsonPath("$[0].id", is("Raisa")))
				.andExpect(jsonPath("$[0].grade", is("SE-JP"))).andExpect(jsonPath("$[0].startDate", is("9 Jan 2010")))
				.andExpect(jsonPath("$[0].endDate", is("20 Sept 2020"))).andExpect(jsonPath("$", hasSize(2)));
		;
	}

	@Test
	public void deleteGrade_shoudDeleteDataCorrectly() throws Exception {
		when(gradeService.deleteGrade(Mockito.any(String.class), Mockito.any(String.class)))
				.thenReturn(getDummyGradeList());
		mockMvc.perform(delete("/employees/123454123/grade/Raisa").contentType(contentType))
				.andExpect(status().isOk()).andExpect(jsonPath("$[0].id", is("Raisa")))
				.andExpect(jsonPath("$[0].grade", is("SE-JP"))).andExpect(jsonPath("$[0].startDate", is("9 Jan 2010")))
				.andExpect(jsonPath("$[0].endDate", is("20 Sept 2020"))).andExpect(jsonPath("$", hasSize(2)));
		;
	}

	
	private List<GradeHistory> getDummyGradeList() {
		List<GradeHistory> result = new ArrayList<GradeHistory>();

		GradeHistory grade1 = new GradeHistory();
		grade1.setDs("DS01");
		grade1.setEndDate("20 Sept 2020");
		grade1.setGrade("SE-JP");
		grade1.setStartDate("9 Jan 2010");
		grade1.setId("Raisa");
		GradeHistory grade2 = new GradeHistory();
		grade2.setDs("DS04");
		grade2.setEndDate("20 Sept 2020");
		grade2.setGrade("SE-PG");
		grade2.setStartDate("9 Jan 2010");
		grade2.setId("Acha");
		result.add(grade1);
		result.add(grade2);

		return result;

	}

	private GradeHistory getDummyGrade() {

		GradeHistory grade1 = new GradeHistory();
		grade1.setDs("DS01");
		grade1.setEndDate("20 Sept 2020");
		grade1.setGrade("SE-JP");
		grade1.setStartDate("9 Jan 2010");
		grade1.setId("Raisa");

		return grade1;

	}

}
