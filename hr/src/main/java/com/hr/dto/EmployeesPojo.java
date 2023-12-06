package com.hr.dto;

import java.sql.Date;
import lombok.Data;

@Data
public class EmployeesPojo {
	
	private  int employeeId;
	private String fristName;
	private String lastName;
	private String email;
	private int phoneNumber;
	private Date hireDate;
	private JobsPojo jobsPojo;
	private double salary;
	private double commissionPct;
	private int managerId;
	private DepartmentsPojo departmentsPojo;
}
