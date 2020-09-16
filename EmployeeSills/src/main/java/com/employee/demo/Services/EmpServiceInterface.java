package com.employee.demo.Services;

import java.util.List;

import com.employee.demo.entity.Employee;
import com.employee.demo.entity.Skill;

public interface EmpServiceInterface {

	List<Employee> getAllEmployees();
	List<Skill> getAllSkills();
	Employee addEmployee(Employee employee);
	Employee updateEmployeeDetails(Employee employee);
    void deleteEmployeeDetails(Long empId);
    void addSkillName(Skill skill);
    Employee getEmployeeById(Long empId);
    
    public int[] batchInsert(List<Employee> employees);
    public int[] batchSkillInsert(List<Skill> skills);
   // public int[] batchInsert(List<Employee> employees, List<Skill> skills);
}
