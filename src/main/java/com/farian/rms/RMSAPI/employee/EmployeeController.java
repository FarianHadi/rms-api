package com.farian.rms.RMSAPI.employee;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(tags = "Employee")
@RequestMapping("/")
public class EmployeeController {

	@Autowired
	private EmployeeService service;

	@Autowired
	private ModelMapper modelMapper;

	@RequestMapping(method = RequestMethod.POST, value = "/employees", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Add Employee", notes = "Creating a new employee data", response = EmployeeDTO.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "Success", response = EmployeeDTO.class) })
	public String addEmployee(@RequestBody EmployeeDTO employeeDTO) {
		Employee employee = convertDTOToEmployee(employeeDTO);
		return service.addEmployee(employee);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/employees")
	public List<EmployeeDTO> getAllEmployees() {
		List<Employee> employeeList = service.getAllEmployee();
		return employeeList.stream().map(employee -> convertEmployeeToDTO(employee)).collect(Collectors.toList());
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/employees/{id}")
	public EmployeeDTO updateEmployee(@RequestBody EmployeeDTO employeeDTO) {
		Employee employee = convertDTOToEmployee(employeeDTO);
		Employee result = service.updateEmployee(employee);			
		return convertEmployeeToDTO(result);
	}

	
	@RequestMapping(method = RequestMethod.DELETE, value = "/employees/{id}")
	public String deleteEmployee(@PathVariable String id) {
		return service.deleteEmployee(id);
		
	}

	private EmployeeDTO convertEmployeeToDTO(Employee employee) {
		EmployeeDTO employeeDTO = modelMapper.map(employee, EmployeeDTO.class);
		return employeeDTO;
	}

	private Employee convertDTOToEmployee(EmployeeDTO employeeDTO) {
		Employee employee = modelMapper.map(employeeDTO, Employee.class);
		return employee;
	}

}
