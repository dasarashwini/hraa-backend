//package com.hr.entity;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.EnumType;
//import jakarta.persistence.Enumerated;
//import jakarta.persistence.Id;
//import jakarta.persistence.OneToOne;
//import jakarta.persistence.PrimaryKeyJoinColumn;
//import jakarta.persistence.Table;
//import lombok.Data;
//
//@Data
//@Entity
//@Table(name = "user")
//public class UserEntity {
//	
//	@Id
//	@OneToOne
//	@PrimaryKeyJoinColumn(name = "employee_id")
//	private EmployeesEntity employeesEntity;
//	
//	@Column(name="role")
//	@Enumerated(EnumType.STRING)
//	private String role;
//	
//	@Column(name="password")
//	private UserRole password;
//	
//	
//	
//}
