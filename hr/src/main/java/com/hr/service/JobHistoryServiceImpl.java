package com.hr.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.hr.dao.JobHistoryRepository;
import com.hr.dto.JobHistoryPojo;
import com.hr.entity.DepartmentsEntity;
import com.hr.entity.EmployeesEntity;
import com.hr.entity.JobHistoryEntity;
import com.hr.entity.JobsEntity;
import com.hr.entity.LocationsEntity;
import com.hr.exceptions.ResourceAlreadyExistsException;

@Service
public class JobHistoryServiceImpl implements JobHistoryService {
	
	@Autowired
    JobHistoryRepository jobHistoryRepository;
	
	List<JobHistoryPojo> jobHistoryList = new ArrayList<>();

	@Override
	public String addJobHistory(JobHistoryPojo newJobHistory) throws ResourceAlreadyExistsException {
		

		
		JobHistoryEntity jobHistoryEntity = new JobHistoryEntity();
		BeanUtils.copyProperties(newJobHistory, jobHistoryEntity);
		// Copying the Foreign key objects of Employees table
		{
			EmployeesEntity employeesEntity =   new EmployeesEntity();
			BeanUtils.copyProperties(newJobHistory.getEmployeesPojo(), employeesEntity);
			jobHistoryEntity.setEmployeesEntity(employeesEntity);
		}
		// Copying the Foreign key objects of Jobs table
		{
			JobsEntity jobsEntity =   new JobsEntity();
			BeanUtils.copyProperties(newJobHistory.getJobsPojo(), jobsEntity);
			jobHistoryEntity.setJobsEntity(jobsEntity);
		}
		// Copying the Foreign key objects of departments table
		{

			DepartmentsEntity departmentsEntity =   new DepartmentsEntity();
			BeanUtils.copyProperties(newJobHistory.getDepartmentsPojo(), departmentsEntity);
			jobHistoryEntity.setDepartmentsEntity(departmentsEntity);
		
		}
		jobHistoryRepository.save(jobHistoryEntity);
		return "Record added successfully";
		}
		



	@Override
	public String modifyJobHistory(JobHistoryPojo newJobHistory) {

		JobHistoryEntity jobHistoryEntity = new JobHistoryEntity();
		BeanUtils.copyProperties(newJobHistory, jobHistoryEntity);
		
		// Copying the Foreign key objects of Employees table
		{
			EmployeesEntity employeesEntity =   new EmployeesEntity();
			BeanUtils.copyProperties(newJobHistory.getEmployeesPojo(), employeesEntity);
			jobHistoryEntity.setEmployeesEntity(employeesEntity);
		}
		// Copying the Foreign key objects of Jobs table
		{
			JobsEntity jobsEntity =   new JobsEntity();
			BeanUtils.copyProperties(newJobHistory.getJobsPojo(), jobsEntity);
			jobHistoryEntity.setJobsEntity(jobsEntity);
		}
		// Copying the Foreign key objects of departments table
		{

			DepartmentsEntity departmentsEntity =   new DepartmentsEntity();
			BeanUtils.copyProperties(newJobHistory.getDepartmentsPojo(), departmentsEntity);
			jobHistoryEntity.setDepartmentsEntity(departmentsEntity);
				
		}
		
		jobHistoryRepository.save(jobHistoryEntity);
		return "Record modified successfully";
		}
	
	 
	 @Override
	 public Map<String, Integer> findExperienceOfEmployee(int employeeId) {
		  
	  		
	        List<JobHistoryEntity> jobHistories = jobHistoryRepository.findByEmployeesEntity_EmployeeId(employeeId);

	        if (jobHistories.isEmpty()) {
	            // Handle the case where there is no job history for the employee
	            Map<String, Integer> emptyResult = new HashMap<>();
	            emptyResult.put("years", 0);
	            emptyResult.put("months", 0);
	            emptyResult.put("days", 0);
	            return emptyResult;
	        }

	        JobHistoryEntity latestJobHistory = jobHistories.get(0);
	        LocalDate startDate = latestJobHistory.getStartDate().toLocalDate();
	        LocalDate endDate = LocalDate.now();

	        Period period = Period.between(startDate, endDate);

	        Map<String, Integer> result = new HashMap<>();
	        result.put("years", period.getYears());
	        result.put("months", period.getMonths());
	        result.put("days", period.getDays());

	        return result;
	    }
	 
	 @Override
	 public Map<String, Integer> listAllEmployeesWithLessThanOneYearExperience(int empId) {
	        // Retrieve job history records for the employee
	        List<JobHistoryEntity> jobHistoryList = jobHistoryRepository.findByEmployeesEntity_EmployeeId(empId);

	        // Calculate total duration of experience
	        Period totalExperience = calculateTotalExperience(jobHistoryList);

	        // Convert the Period to Map
	        Map<String, Integer> experienceMap = convertPeriodToMap(totalExperience);

	        return experienceMap;
	    }

	    private Period calculateTotalExperience(List<JobHistoryEntity> jobHistoryList) {
	        // Calculate total duration of experience by summing up periods from job history records
	        Period totalExperience = Period.ZERO;
	        for (JobHistoryEntity jobHistory : jobHistoryList) {
	            LocalDate startDate = jobHistory.getStartDate().toLocalDate();
	            LocalDate endDate = jobHistory.getEndDate() != null ? jobHistory.getEndDate().toLocalDate() : LocalDate.now();

	            totalExperience = totalExperience.plus(Period.between(startDate, endDate));
	        }
	        return totalExperience;
	    }

	    private Map<String, Integer> convertPeriodToMap(Period period) {
	        // Convert Period to Map
	        Map<String, Integer> experienceMap = new HashMap<>();
	        experienceMap.put("years", period.getYears());
	        experienceMap.put("months", period.getMonths());
	        experienceMap.put("days", period.getDays());
	        return experienceMap;
	    }



		
	  
}