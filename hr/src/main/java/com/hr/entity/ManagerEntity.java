package com.hr.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "manager")
public class ManagerEntity {
	
	@Id
	@Column(name = "manager_id")
	private int managerId;
	
	@OneToOne
	@JoinColumn(name = "department_id")
	private DepartmentsEntity departmentsEntity;
}
