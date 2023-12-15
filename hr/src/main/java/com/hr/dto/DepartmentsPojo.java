package com.hr.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DepartmentsPojo {
	@NotBlank(message="Validation Failed..")
	private int departmentId;
	@NotBlank(message="Validation Failed..")
	private String departmentName;
	private LocationsPojo locationsPojo;
}
