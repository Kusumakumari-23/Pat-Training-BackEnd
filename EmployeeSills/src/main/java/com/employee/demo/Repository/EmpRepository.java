package com.employee.demo.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.employee.demo.entity.Employee;
import com.employee.demo.entity.Skill;
import com.employee.demo.exception.EmployeeSkillInsertionException;
import com.employee.demo.exception.ResourceNotFoundException;

//@Transactional
@Repository
public class EmpRepository implements EmpRepositoryInterface {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	@Override
	public List<Employee> getAllEmployees() {
		
		String sql = "SELECT * FROM employees";
		RowMapper<Employee> rowMapper=new BeanPropertyRowMapper<Employee>(Employee.class);
		return this.jdbcTemplate.query(sql, rowMapper);
	}
	
	@Override
	public List<Skill> getAllSkills() {
		
		String sql = "SELECT * FROM skills";
		
		RowMapper<Skill> rowMapper=new BeanPropertyRowMapper<Skill>(Skill.class);
		return this.jdbcTemplate.query(sql, rowMapper);
	}
	
	@Override
	public Employee getEmployeeById(Long empId) {
		
		try {
		final String EMP_BY_ID="select * from employees where empId=?;"; 
	    List<Employee> query = jdbcTemplate.query(EMP_BY_ID, new BeanPropertyRowMapper<Employee>(Employee.class), empId);  
	    return DataAccessUtils.uniqueResult(query);
		}catch (EmptyResultDataAccessException e) {
			return null;
	}
	}
	
	//INSERT EMPLOYEE AND SKILL TOGETHER

	@Override
	public Employee addEmployee(Employee emp) {
		
		final String EMP_INSERT= "insert into employees (empId, firstName, lastName, hireDate, city, email, phoneNo) values (?, ?, ?, ?, ?, ?, ?) ";
		final String SKILL_INSERT="insert into skills (skillId, skillName, details) values(?, ?, ?)";
		
		jdbcTemplate.update(EMP_INSERT, new Object[] { emp.getEmpId(), emp.getFirstName(), emp.getLastName(), emp.getHireDate(), emp.getCity(),
				emp.getEmail(), emp.getPhoneNo()
				 });
		System.out.println("Inserted into Employee Table Successfully");
		
		jdbcTemplate.update(SKILL_INSERT, new Object[] { emp.getSkills().getSkillId(), emp.getSkills().getSkillName(),
				emp.getSkills().getDetails()
				 });
		System.out.println("Inserted into Employee Table Successfully");
		
//		if(size1!=size2) {
//			throw new EmployeeSkillInsertionException(" Internal Error");
//		}
		return emp;
		
	}

	@Override
	public Employee updateEmployeeDetails(Employee employee) {
		
		final String UPDATE_EMPLOYEE="update employees set firstName=?, lastName=?, hireDate=?, email=?, city=?, phoneNo=? where empId=?;";
		jdbcTemplate.update(UPDATE_EMPLOYEE, employee.getFirstName(), employee.getLastName(), employee.getHireDate(), employee.getEmail(), employee.getCity(), employee.getPhoneNo(), employee.getEmpId());
		return employee;
		
		
	}
	
	@Override
	public void deleteEmployeeDetails(Long empId) {
		 
		final String DELETE_BY_ID = "delete from employees where empId=?";
		
		int size= jdbcTemplate.update(DELETE_BY_ID, empId);
		if(size==0) {
			throw new ResourceNotFoundException("no employee with "+empId+" to delete");
		}
		
	}
	

	@Override
	public void addSkillName(Skill skill) {
		// TODO Auto-generated method stub
		
	}
	
// batch Insert - inserting multiple records at a time

	@Override
	public int[] batchInsert(List<Employee> employees) {
		
		//final String INSERT_EMP_QUERY = "insert into employees (empId, firstName, lastName, hireDate, city, email, phoneNo) values (?, ?, ?, ?, ?, ?, ?) ";
		final String INSERT_EMP_QUERY = "insert into employees (empId, firstName, lastName, hireDate, city, email, phoneNo,skills) values (?, ?, ?, ?, ?, ?, ?, ?) "+          
		"insert into skills (skillId, skillName, details) values(?, ?, ?);";
		
		return this.jdbcTemplate.batchUpdate(INSERT_EMP_QUERY,
	      new BatchPreparedStatementSetter() {
	      
	      @Override
	      public void setValues(PreparedStatement ps, int i) throws SQLException {
	        // emp id is auto generated so not provided
	    	ps.setLong(1, employees.get(i).getEmpId());
	        ps.setString(2, employees.get(i).getFirstName());
	        ps.setString(3, employees.get(i).getLastName()); 
	        ps.setDate(4, employees.get(i).getHireDate());
	        ps.setString(5, employees.get(i).getCity());
	        ps.setString(6, employees.get(i).getEmail());
	        ps.setString(7, employees.get(i).getPhoneNo());
	        ps.setObject(8, employees.get(i).getSkills());
	        ps.setObject(8, employees.get(i).getSkills());
	        ps.setObject(9, employees.get(i).getSkills());
	        ps.setObject(10, employees.get(i).getSkills());
//	        List<Skill> skills=employees.get(i).getSkills();
//	        for(Skill employees: skills) {
//	        	ps.setObject(8, employees.getSkillId());
//	        	ps.setObject(9, employees.getSkillName());
//	        	ps.setObject(10, employees.getDetails());
//	        	//ps.setObject(8, employees.getEmpId());
//	        	
//	        }
	        
	      }
	                    
	      @Override
	      public int getBatchSize() {
	          return employees.size();
	      }
	    });            
	}

	@Override
	public int[] batchSkillInsert(List<Skill> skills) {
		
		final String INSERT_SKILL_QUERY="insert into skills (skillId, skillName, details,empId) values(?, ?, ?, ?);";
		
		return this.jdbcTemplate.batchUpdate(INSERT_SKILL_QUERY,
			      new BatchPreparedStatementSetter() {
			      
			      @Override
			      public void setValues(PreparedStatement ps, int i) throws SQLException {
			        // emp id is auto generated so not provided
			    	ps.setLong(1,   skills.get(i).getSkillId());
			    	ps.setString(2, skills.get(i).getSkillName());
			    	ps.setString(3, skills.get(i).getDetails());
			    	ps.setObject(4, skills.get(i).getEmp());
			        	
			        }
			                    
			      @Override
			      public int getBatchSize() {
			          return skills.size();
			      }
			    });            
	}

}
