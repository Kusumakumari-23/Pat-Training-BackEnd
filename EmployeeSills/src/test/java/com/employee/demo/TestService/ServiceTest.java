package com.employee.demo.TestService;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.transaction.annotation.Transactional;
import org.junit.Assert;

import com.employee.demo.Repository.EmpRepositoryInterface;
import com.employee.demo.Services.EmpService;
import com.employee.demo.entity.Employee;
import com.employee.demo.entity.Skill;

@RunWith(MockitoJUnitRunner.Silent.class)
@Transactional
public class ServiceTest {

	@Mock
	private EmpRepositoryInterface empRepo;
	
	@InjectMocks
	private EmpService empService;
	
	@Test
	@DisplayName("Testing Employee Insertion")
	public void addEmployeeTest() {
		
		Employee employee=new Employee(5L, "devdev", "omkar", new Date(2019-04-11), "fdfkdnk", "mp", "56478");
		
		empService.addEmployee(employee);
		verify(empRepo,times(1)).addEmployee(employee);
	}
	
	@Test
	public void addSkillsTest() {
		List<Skill> skills= Arrays.asList(
				new Skill(101L,"c","Programming Language"),
				new Skill(102L,"c++","oop"));
		
		empService.batchSkillInsert(skills);
		verify(empRepo,times(1)).batchSkillInsert(skills);
		
	}
	
	@Test
	public void EmployeeDetailsById() {
		Employee emp=new Employee(5L, "devdev", "omkar", new Date(2019-04-11), "fdfkdnk", "mp", "56478");
		
		Mockito.when(empRepo.getEmployeeById(5L)).thenReturn(emp);
		Assert.assertSame(5L, empService.getEmployeeById(emp.getEmpId()).getEmpId());
	}
	
	@Test
	public void EmployeeUpdate() {
		Employee emp=new Employee(5L, "devdev", "omkar", new Date(2019-04-11), "fdfkdnk", "mp", "56478");
		
		//Mockito.when(empRepo.updateEmployeeDetails(emp)).thenReturn(emp);
		Mockito.when(empRepo.updateEmployeeDetails(emp)).thenReturn(emp);
		Assert.assertSame(5L, empService.updateEmployeeDetails(emp).getEmpId());
	}
	
	@Test
	public void deleteById() {
		empService.deleteEmployeeDetails(5L);
		empService.deleteEmployeeDetails(5L);
		
		verify(empRepo,times(2)).deleteEmployeeDetails(5L);
		
	}
	
	
}
	


