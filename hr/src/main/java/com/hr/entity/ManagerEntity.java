package com.hr.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "manager", schema = "hr")
public class ManagerEntity {
	
	@Id
	@OneToOne
	@JoinColumn(name = "department_id")
	private DepartmentsEntity departmentsEntity;
	
	@OneToOne
	@JoinColumn(name = "manager_id")
	private EmployeesEntity employeesEntity;

}
