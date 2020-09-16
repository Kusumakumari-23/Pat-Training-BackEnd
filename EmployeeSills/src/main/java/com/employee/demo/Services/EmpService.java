package com.employee.demo.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.employee.demo.Repository.EmpRepositoryInterface;
import com.employee.demo.entity.Employee;
import com.employee.demo.entity.Skill;
import com.employee.demo.exception.EmployeeSkillInsertionException;
import com.employee.demo.exception.ResourceNotFoundException;

@Service
public class EmpService implements EmpServiceInterface {

	@Autowired
	private EmpRepositoryInterface empRepo;
	
	@Override
	public List<Employee> getAllEmployees() {
		
		return empRepo.getAllEmployees();
	}
	
	@Override
	public List<Skill> getAllSkills() {
		
		return empRepo.getAllSkills();
	}
	
	@Override
	public Employee getEmployeeById(Long empId) {
		
		Employee emp = empRepo.getEmployeeById(empId);
		return emp;
	}
	
	// INSERT BOTH SKILL AND EMPLOYEE

	@Override
	@Transactional(isolation=Isolation.SERIALIZABLE, 
	               propagation= Propagation.REQUIRES_NEW,
	               rollbackFor= EmployeeSkillInsertionException.class)
	public Employee addEmployee(Employee employee) {
		
		try {
		return empRepo.addEmployee(employee);
		}catch(Exception e) {
			throw new EmployeeSkillInsertionException(" Internal Server Error");
		}
		
	}

	@Override
	public Employee updateEmployeeDetails(Employee employee) {
		
		return empRepo.updateEmployeeDetails(employee);
	}

	@Override
	public void deleteEmployeeDetails(Long empId) {
		empRepo.deleteEmployeeDetails(empId);
		
	}

	@Override
	public void addSkillName(Skill skill) {
		// TODO Auto-generated method stub
		
	}
	
// batch Insert - inserting multiple records at a time

	@Override
	public int[] batchInsert(List<Employee> employees) {
		
		return empRepo.batchInsert(employees);
	}

    @Override
    public int[] batchSkillInsert(List<Skill> skills) {
	
	return empRepo.batchSkillInsert(skills);
}

	
	
	


}
