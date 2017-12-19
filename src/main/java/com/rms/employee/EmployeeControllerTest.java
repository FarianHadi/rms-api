//package com.rms.employee;
//
//import java.util.Collections;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import com.rms.repository.EmployeeRepository;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//
//public class EmployeeControllerTest {
//	@Autowired
//	private MockMvc mockMvc;	
//	
//	@MockBean
//	EmployeeRepository employeeRepo;	
//	
//	@Test
//	public void getAllEmployees_shouldReturnAllEntries() throws Exception{
//		Mockito.when(employeeRepo.findAll()).thenReturn(
//				Collections.emptyList());
//		
//		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/employee").accept(MediaType.APPLICATION_JSON)).andReturn();
//		System.out.println(mvcResult.getResponse());
//		Mockito.verify(employeeRepo).findAll();
//	}
//	
//}
