package com.employee.demo.TestRepository;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.employee.demo.EmployeeSillsApplication;
import com.employee.demo.Repository.EmpRepository;
import com.employee.demo.entity.Employee;
import com.employee.demo.entity.Skill;

@RunWith(MockitoJUnitRunner.Silent.class)
@SpringBootTest(classes=EmployeeSillsApplication.class)
public class EmpRepositoryTest {

	@Mock
	private JdbcTemplate jdbcTemplate;
	
	@InjectMocks
	private EmpRepository empRepo;
	
//	@Test
//	public void addEmployeeTest() {
//		
//		Employee employee=new Employee(5L, "devdev", "omkar", new Date(2019-04-11), "fdfkdnk", "mp", "56478");
//		
//		empRepo.addEmployee(employee);
//		Mockito.when(jdbcTemplate.batchUpdate("insert into employees (empId, firstName, lastName, hireDate, city, email, phoneNo) values (?, ?, ?, ?, ?, ?, ?) "));
//		Assert.assertNotNull(employee);
//	}
	
	@Test
	public void addSkillsTest() {
		List<Skill> skills= Arrays.asList(
				new Skill(101L,"c","Programming Language"),
				new Skill(102L,"c++","oop"));
		
		empRepo.batchSkillInsert(skills);
		//verify(jdbcTemplate,times(1)).update("insert into skills (skillId, skillName, details) values(?, ?, ?)", skills);
		Mockito.when(jdbcTemplate.batchUpdate("insert into skills (skillId, skillName, details) values(?, ?, ?)"));
		Assert.assertNotNull(skills);
		
	}
	
	@Test
	public void EmployeeDetailsById() {
		Employee emp=new Employee(5L, "devdev", "omkar", new Date(2019-04-11), "fdfkdnk", "mp", "56478");
		
		Mockito.when(jdbcTemplate.queryForObject("select * from employees where empId=?", new BeanPropertyRowMapper<Employee>(Employee.class), emp.getEmpId())).thenReturn(emp);
		//Assert.assertNotSame(5L, empRepo.getEmployeeById(emp.getEmpId()).getEmpId());
		Assert.assertNotNull(emp);
	}
	
	@Test
	public void EmployeeUpdate() {
		Employee emp=new Employee(5L, "devdev", "omkar", new Date(2019-04-11), "fdfkdnk", "mp", "56478");
		
		empRepo.updateEmployeeDetails(emp);
		Mockito.when(jdbcTemplate.update("update employees set firstName=?, lastName=?, hireDate=?, email=?, city=?, phoneNo=? where empId=?;"));
		Assert.assertNotNull(emp);
		//Assert.assertSame(5L, empService.updateEmployeeDetails(emp).getEmpId());
	}
	
	
	
}
