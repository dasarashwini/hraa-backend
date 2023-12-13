package com.hr.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;



@Data
@Entity
@Table(name="employees", schema = "hr")
public class EmployeesEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="employee_id",length = 6)
	private  int employeeId;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="email")
	private String email;
	
	@Column(name="phone_number")
	private String phoneNumber;
	
	@Column(name="hire_date")
	@Temporal(TemporalType.DATE)
	private Date hireDate;
	
	@ManyToOne
	@JoinColumn(name="job_id")
	private JobsEntity jobsEntity;

	@Column(name="salary")
	private double salary;
	
	@Column(name="commission_pct")
	private double commissionPct;
	
	@Column(name="manager_id",length = 6)
	private int managerId;
	
	@ManyToOne
	@JoinColumn(name="department_id")
	private DepartmentsEntity departmentsEntity;

}
