package com.employee.demo.entity;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;

//@AllArgsConstructor
//@Data
//@Entity
public class EmployeeCompositeKey implements Serializable{

//	@Column(name="empId")
	@Id
	private Long empId;
	
//	@Column(name="firstName")
	private String firstName;

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
	
	
	
}
