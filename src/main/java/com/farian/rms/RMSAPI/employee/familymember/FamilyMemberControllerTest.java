package com.farian.rms.RMSAPI.employee.familymember;

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
@WebMvcTest(FamilyMemberController.class)
public class FamilyMemberControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private FamilyMemberService familyService;

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	@Test
	public void getFamilyAll_shoudReturnAllEmployeesFamilyMember() throws Exception {
		when(familyService.getFamilyAll("123454123")).thenReturn(getDummyFamilyMemberList());
		mockMvc.perform(get("/employees/123454123/families/")).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id", is("01922de9-fb21-42a1-94e7-d3fafb9c4b20"))).andExpect(jsonPath("$", hasSize(2)));

		verify(familyService, times(1)).getFamilyAll("123454123");
		verifyNoMoreInteractions(familyService);

	}

	@Test
	public void addFamilyMember_shoudStoreDataCorrectly() throws Exception {
		when(familyService.addFamily(Mockito.any(String.class), Mockito.any(FamilyMember.class)))
				.thenReturn(getDummyFamilyMemberList());
		ObjectMapper mapper = new ObjectMapper();
		String jsonNewFamily = mapper.writeValueAsString(getDummyFamily());
		mockMvc.perform(post("/employees/123454123/families/").content(jsonNewFamily).contentType(contentType))
				.andExpect(status().isOk()).andExpect(jsonPath("$[0].id", is("01922de9-fb21-42a1-94e7-d3fafb9c4b20")))
				.andExpect(jsonPath("$", hasSize(2)));
		;
	}

	@Test
	public void updateFamilyMember_shoudUpdateDataCorrectly() throws Exception {
		when(familyService.updateFamily(Mockito.any(String.class), Mockito.any(FamilyMember.class)))
				.thenReturn(getDummyFamilyMemberList());
		ObjectMapper mapper = new ObjectMapper();
		FamilyMember updatedFamilyMember = getDummyFamily();
		updatedFamilyMember.setFirstName("Veranda");
		updatedFamilyMember.setLastName("Putri");
		updatedFamilyMember.setDob("20 Sept 2020");
		
		String jsonNewFamily = mapper.writeValueAsString(updatedFamilyMember);
		mockMvc.perform(put("/employees/123454123/families/").content(jsonNewFamily).contentType(contentType))
				.andExpect(status().isOk()).andExpect(jsonPath("$[0].id", is("01922de9-fb21-42a1-94e7-d3fafb9c4b20")))
				.andExpect(jsonPath("$[0].firstName", is("Ariana"))).andExpect(jsonPath("$[0].lastName", is("Grande")))
				.andExpect(jsonPath("$[0].type", is("wife"))).andExpect(jsonPath("$", hasSize(2)));
		;
	}

	@Test
	public void deleteFamilyMember_shoudDeleteDataCorrectly() throws Exception {
		when(familyService.deleteFamily(Mockito.any(String.class), Mockito.any(String.class)))
				.thenReturn(getDummyFamilyMemberList());
		mockMvc.perform(delete("/employees/123454123/families/Raisa").contentType(contentType))
		.andExpect(status().isOk()).andExpect(jsonPath("$[0].id", is("01922de9-fb21-42a1-94e7-d3fafb9c4b20")))
		.andExpect(jsonPath("$[0].firstName", is("Ariana"))).andExpect(jsonPath("$[0].lastName", is("Grande")))
		.andExpect(jsonPath("$[0].type", is("wife"))).andExpect(jsonPath("$", hasSize(2)));
		;
	}

	
	private List<FamilyMember> getDummyFamilyMemberList() {
		List<FamilyMember> result = new ArrayList<FamilyMember>();

		FamilyMember fam1 = new FamilyMember();
		fam1.setId("01922de9-fb21-42a1-94e7-d3fafb9c4b20");
		fam1.setFirstName("Ariana");
		fam1.setLastName("Grande");
		fam1.setType("wife");
		fam1.setDob("9 Jan 2010");
		
		FamilyMember fam2 = new FamilyMember();
		fam2.setId("01922de9-fb21-42a1-94e7-d3fafb9c4b21");
		fam2.setFirstName("Ayu");
		fam2.setLastName("TingTing");
		fam2.setType("Aunt");
		fam2.setDob("9 Jan 2010");
		result.add(fam1);
		result.add(fam2);

		return result;

	}

	private FamilyMember getDummyFamily() {

		FamilyMember fam1 = new FamilyMember();
		fam1.setId("01922de9-fb21-42a1-94e7-d3fafb9c4b20");
		fam1.setFirstName("Ariana");
		fam1.setLastName("Grande");
		fam1.setType("wife");
		fam1.setDob("9 Jan 2010");

		return fam1;

	}

}
