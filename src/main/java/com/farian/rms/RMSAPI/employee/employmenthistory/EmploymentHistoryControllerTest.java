package com.farian.rms.RMSAPI.employee.employmenthistory;

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
@WebMvcTest(EmploymentHistoryController.class)
public class EmploymentHistoryControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private EmployementHistoryService employmentService;

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	@Test
	public void getEmploymentAll_shoudReturnAllEmploymentHistory() throws Exception {
		when(employmentService.getEmploymentAll("123454123")).thenReturn(getDummyEmploymentList());
		mockMvc.perform(get("/employees/123454123/employments/")).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id", is("4f67e932-f4dd-402f-91d2-9d233cbe587b"))).andExpect(jsonPath("$", hasSize(2)));

		verify(employmentService, times(1)).getEmploymentAll("123454123");
		verifyNoMoreInteractions(employmentService);

	}

	@Test
	public void addEmployment_shoudStoreDataCorrectly() throws Exception {
		when(employmentService.addEmploymentHistory(Mockito.any(String.class), Mockito.any(EmploymentHistory.class)))
				.thenReturn(getDummyEmploymentList());
		ObjectMapper mapper = new ObjectMapper();
		String jsonNewEmployment = mapper.writeValueAsString(getDummyEmployment());
		mockMvc.perform(post("/employees/123454123/employments/").content(jsonNewEmployment).contentType(contentType))
				.andExpect(status().isOk()).andExpect(jsonPath("$[0].id", is("4f67e932-f4dd-402f-91d2-9d233cbe587b")))
				.andExpect(jsonPath("$", hasSize(2)));
		;
	}

	@Test
	public void updateEmployment_shoudUpdateDataCorrectly() throws Exception {
		when(employmentService.updateEmployment(Mockito.any(String.class), Mockito.any(EmploymentHistory.class)))
				.thenReturn(getDummyEmploymentList());
		ObjectMapper mapper = new ObjectMapper();
		EmploymentHistory updatedEmployment = getDummyEmployment();
		updatedEmployment.setCompany("Garuda Indonesia");
		updatedEmployment.setPosition("Senior FA");
		String jsonNewEmployemnet = mapper.writeValueAsString(updatedEmployment);
		mockMvc.perform(put("/employees/123454123/employments/").content(jsonNewEmployemnet).contentType(contentType))
				.andExpect(status().isOk()).andExpect(jsonPath("$[0].id", is("4f67e932-f4dd-402f-91d2-9d233cbe587b")))
				.andExpect(jsonPath("$[0].company", is("AirAsia"))).andExpect(jsonPath("$[0].position", is("Junior FA")))
				.andExpect(jsonPath("$[0].periode", is("20 Sept 2015 - 20 Sept 2016"))).andExpect(jsonPath("$", hasSize(2)));
		;
	}
	@Test
	public void deleteEmployment_shoudDeleteDataCorrectly() throws Exception {
		when(employmentService.deleteEmployment(Mockito.any(String.class), Mockito.any(String.class)))
				.thenReturn(getDummyEmploymentList());
		mockMvc.perform(delete("/employees/123454123/employments/4f67e932-f4dd-402f-91d2-9d233cbe587b").contentType(contentType))
		.andExpect(status().isOk()).andExpect(jsonPath("$[0].id", is("4f67e932-f4dd-402f-91d2-9d233cbe587b")))
		.andExpect(jsonPath("$[0].company", is("AirAsia"))).andExpect(jsonPath("$[0].position", is("Junior FA")))
		.andExpect(jsonPath("$[0].periode", is("20 Sept 2015 - 20 Sept 2016"))).andExpect(jsonPath("$", hasSize(2)));
		;
	}

	
	private List<EmploymentHistory> getDummyEmploymentList() {
		List<EmploymentHistory> result = new ArrayList<EmploymentHistory>();

		EmploymentHistory Employment1 = new EmploymentHistory();
		Employment1.setCompany("AirAsia");
		Employment1.setPeriode("20 Sept 2015 - 20 Sept 2016");
		Employment1.setJobDescription("Flight Attendant");
		Employment1.setPosition("Junior FA");
		Employment1.setId("4f67e932-f4dd-402f-91d2-9d233cbe587b");
		EmploymentHistory Employment2 = new EmploymentHistory();
		Employment2.setCompany("Citilink Indonesia");
		Employment2.setPeriode("20 Sept 2016 - 20 Sept 2017");
		Employment2.setJobDescription("Flight Attendant");
		Employment2.setPosition("Senior FA");
		Employment2.setId("4f67e932-f4dd-402f-91d2-9d233cbe587b");
		result.add(Employment1);
		result.add(Employment2);

		return result;

	}

	private EmploymentHistory getDummyEmployment() {

		EmploymentHistory Employment1 = new EmploymentHistory();
		Employment1.setCompany("AirAsia");
		Employment1.setPeriode("20 Sept 2015 - 20 Sept 2016");
		Employment1.setJobDescription("Flight Attendant");
		Employment1.setPosition("Junior FA");
		Employment1.setId("4f67e932-f4dd-402f-91d2-9d233cbe587b");

		return Employment1;

	}

}
