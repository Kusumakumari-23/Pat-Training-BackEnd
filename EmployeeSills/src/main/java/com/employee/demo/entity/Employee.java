package com.employee.demo.entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

//@Table(name="employees",schema="employee")
//@AllArgsConstructor
//@Data
@IdClass(EmployeeCompositeKey.class)
public class Employee {

	@Id
	private Long empId;
	
	@Id
	private String firstName;
	
	private String lastName;
	
	private Date hireDate;
	
	private String city;
	
	private String email;
	
	private String phoneNo;
	
//	@OneToMany
//	@JoinColumn(name="skillId")
	private Skill skills;
	
	public Employee() {
		
	}
	
	
	
	public Employee(Long empId, String firstName, String lastName, Date hireDate, String city, String email,
			String phoneNo) {
		super();
		this.empId = empId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.hireDate = hireDate;
		this.city = city;
		this.email = email;
		this.phoneNo = phoneNo;
	}



	public Employee(Long empId, String firstName, String lastName, Date hireDate, String city, String email,
			String phoneNo, Skill skills) {
		super();
		this.empId = empId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.hireDate = hireDate;
		this.city = city;
		this.email = email;
		this.phoneNo = phoneNo;
		this.skills = skills;
	}

	public Long getEmpId() {
		return empId;
	}

	public void setEmpId(Long empId) {
		this.empId = empId;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public Skill getSkills() {
		return skills;
	}

	public void setSkills(Skill skills) {
		this.skills = skills;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", firstName=" + firstName + ", lastName=" + lastName + ", hireDate="
				+ hireDate + ", city=" + city + ", email=" + email + ", phoneNo=" + phoneNo + ", skills=" + skills
				+ "]";
	}
	
	
}
