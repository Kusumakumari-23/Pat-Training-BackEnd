package com.employee.demo.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.Mockito.when;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.employee.demo.Controller.EmployeeController;
import com.employee.demo.Services.EmpServiceInterface;
import com.employee.demo.entity.Employee;
import com.employee.demo.entity.Skill;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
//@WithMockUser
public class TestController {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private EmpServiceInterface empService;
	
	List<Employee> employees= Arrays.asList(
			new Employee(5L, "devdev", "omkar", new Date(2019-04-11), "fdfkdnk", "mp", "56478"),
			new Employee(4L, "dev", "kumari", new Date(2020-04-11), "fdhjf", "ap", "2394ooo78"));
	
	List<Skill> skills= Arrays.asList(
			new Skill(101L,"c","Programming Language"),
			new Skill(102L,"c++","oop"));
	
	@Test
	public void addEmpTest() throws Exception {
		
		RequestBuilder request= MockMvcRequestBuilders.post("/emp/addemployee").accept(MediaType.APPLICATION_JSON).content(
				"{\"empId\":\"1\","
				+ "\"firstName\":\"mahadev\","
				+ "\"lastName\":\"dev\","
				+ "\"hireDate\":\"2020-05-05\","
				+ "\"email\":\"kdjhu\","
				+ "\"city\":\"ap\","
				+ "\"phoneNo\":\"54682\"}").contentType(MediaType.APPLICATION_JSON);
		
		mockMvc.perform(request).andExpect(status().is(200)).andReturn();	
				
	}
	
//	@Test
//	public void deleteEmpTest() throws Exception {
//		
//		Employee employee=new Employee(5L, "devdev", "omkar", new Date(2019-04-11), "fdfkdnk", "mp", "56478");
//		
//		when(empService.getEmployeeById(anyLong())).thenReturn(employee);
//		when(empService.deleteEmployeeDetails(anyLong())).thenReturn(true);
//		String uri="/employee/5";
//		
//		RequestBuilder request= MockMvcRequestBuilders.delete(uri);
//		mockMvc.perform(request).andExpect(status().is(200));
//		
//	}
	
//	@Test
//	   public void deleteProduct() throws Exception {
//	      String uri = "/employee/5";
//	      MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(uri)).andExpect(status().isOk()).andReturn();
//	      int status = mvcResult.getResponse().getStatus();
//	      //assertEquals(200, status);
//	      String content = mvcResult.getResponse().getContentAsString();
//	      assertEquals(content, "Product is deleted successsfully");
//	   }
	
	@Test
	public void getAllEmployees() throws Exception {
		
		Mockito.when(empService.getAllEmployees()).thenReturn(employees);
		
		RequestBuilder request= MockMvcRequestBuilders.get("/emp/getemployees");
		mockMvc.perform(request).andExpect(status().isOk()).andReturn();
	}
	
	@Test
	public void getAllSkills() throws Exception{
		
		Mockito.when(empService.getAllSkills()).thenReturn(skills);
		
		RequestBuilder request=MockMvcRequestBuilders.get("/emp/getskills");
		mockMvc.perform(request).andExpect(status().isOk()).andReturn();
		
	}
	
	@Test
	public void testUpdateEmployee() throws Exception {
		
		Employee emp=new Employee(5L, "devdev", "omkar", new Date(2019-04-11), "fdfkdnk", "mp", "56478");
		
		Mockito.when(empService.updateEmployeeDetails(emp)).thenReturn(emp);
		
		RequestBuilder request= MockMvcRequestBuilders.put("/emp/updateEmployee").accept(MediaType.APPLICATION_JSON).content(
				"{\"empId\":\"1\","
				+ "\"firstName\":\"mahadev\","
				+ "\"lastName\":\"dev\","
				+ "\"hireDate\":\"2020-05-05\","
				+ "\"email\":\"kdjhu\","
				+ "\"city\":\"ap\","
				+ "\"phoneNo\":\"54682\"}").contentType(MediaType.APPLICATION_JSON);
		
		mockMvc.perform(request).andExpect(status().is(200)).andReturn();
	}
	
	@Test
	public void testGetEmployee() throws Exception {
		Employee employee=new Employee(5L, "devdev", "omkar", new Date(2019-04-11), "fdfkdnk", "mp", "56478");
		
		when(empService.getEmployeeById(anyLong())).thenReturn(employee);
		String uri="/employeeById/5";
		
		RequestBuilder request=MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(request).andExpect(status().isOk())
		.andExpect(jsonPath("$.empId",Matchers.is(5)))
		.andExpect(jsonPath("$.*",Matchers.hasSize(8)));
	}
} 
