package com.hr.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class JobHistoryPojo {
	private EmployeesPojo employeesPojo;
	private Date startDate;
	private Date endDate;
	private JobsPojo jobsPojo;
	private DepartmentsPojo departmentsPojo;
}
