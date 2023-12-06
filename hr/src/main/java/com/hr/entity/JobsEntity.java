package com.hr.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="jobs", schema = "hr")
public class JobsEntity {
	
	@Id
	@Column(name="job_id")
	private String jobId;
	
	@Column(name="job_title")
	private String jobTitle;
	
	@Column(name="min_salary")
	private double minSalary;
	
	@Column(name="max_salary")
	private double maxSalary;
	
}
