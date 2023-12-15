package com.hr.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class JobsPojo {
	@NotNull(message="Validation Failed...")

		@NotBlank(message="Validation Failed..")

		private String jobId;

		@NotNull(message="Validation Failed...")

		@NotBlank(message="Validation Failed..")

		private String jobTitle;

		private double minSalary;

		private double maxSalary;
}
