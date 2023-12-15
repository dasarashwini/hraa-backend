package com.hr.dto;

import java.sql.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeesPojo {
	
	
	private  int employeeId;
	
	@NotNull(message = "FirstName Id Should not be null")
	@NotBlank(message = "FirstName Id Should not be null")
	private String firstName;
	
	@NotNull(message = "validation failed")
	@NotBlank(message = "validation failed")
	private String lastName;
	
	@NotNull(message = "validation failed")
	@NotBlank(message = "validation failed")
	private String email;
	
	@NotNull(message = "validation failed")
	@NotBlank(message = "validation failed")
	private String phoneNumber;
	
	private Date hireDate;
	private JobsPojo jobsPojo;	
	private double salary;
	private double commissionPct;	
    private ManagerPojo managerPojo;
    private DepartmentsPojo departmentsPojo;
    private UserPojo userPojo;
}
