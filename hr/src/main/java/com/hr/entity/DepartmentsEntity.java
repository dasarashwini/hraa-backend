package com.hr.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="departments", schema = "hr")
public class DepartmentsEntity {
	
	@Id
	@Column(name="department_id")
	private Integer departmentId;
	
	@Column(name="department_name")
	private String departmentName;
	
	@ManyToOne
	@JoinColumn(name = "location_id")
	private LocationsEntity locationsEntity;
	
}
