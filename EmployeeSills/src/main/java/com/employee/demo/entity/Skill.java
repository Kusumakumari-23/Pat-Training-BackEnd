package com.employee.demo.entity;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

//@Entity
//@Table(name="skills", schema="employee")
//@AllArgsConstructor
//@Data
@IdClass(SkillCompositeKey.class)
public class Skill implements Serializable{
	
	@Id
	private Long skillId;
	
	@Id
	private String skillName;
	
//	@Column(name="details")
	private String details;

	@ManyToOne
	@JoinColumn(name= "empId")
	private Employee emp;
	
	public Skill() {
		
	}

	public Skill(Long skillId, String skillName, String details) {
		super();
		this.skillId = skillId;
		this.skillName = skillName;
		this.details = details;
	}

	public Skill(Long skillId, String skillName, String details, Employee emp) {
		super();
		this.skillId = skillId;
		this.skillName = skillName;
		this.details = details;
		this.emp = emp;
	}

	public Long getSkillId() {
		return skillId;
	}

	public void setSkillId(Long skillId) {
		this.skillId = skillId;
	}

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Employee getEmp() {
		return emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
	}

	@Override
	public String toString() {
		return "Skill [skillId=" + skillId + ", skillName=" + skillName + ", details=" + details + "]";
	}
	
}
