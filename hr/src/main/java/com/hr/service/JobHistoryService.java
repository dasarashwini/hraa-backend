package com.hr.service;

import java.util.Map;

import org.springframework.stereotype.Service; 

import com.hr.dto.JobHistoryPojo;
import com.hr.exceptions.ResourceAlreadyExistsException;

@Service
public interface JobHistoryService {

	String addJobHistory(JobHistoryPojo newJobHistory) throws ResourceAlreadyExistsException;

	String modifyJobHistory(JobHistoryPojo newJobHistory);
	
	public Map<String, Integer> findExperienceOfEmployee(int employeeId);
	
	public Map<String, Integer> listAllEmployeesWithLessThanOneYearExperience(int empId);
}
