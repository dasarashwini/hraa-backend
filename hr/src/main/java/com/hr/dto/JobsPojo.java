package com.hr.dto;

import lombok.Data;

@Data
public class JobsPojo {
	private int jobId;
	private String jobTitle;
	private double minSalary;
	private double maxSalary;
}
