package com.employee.demo.entity;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
//@Entity
public class SkillCompositeKey implements Serializable{
 
//	@Column(name="skillId")
	private Long skillId;
	
//	@Column(name="skillName")
	private String skillName;
	
}
