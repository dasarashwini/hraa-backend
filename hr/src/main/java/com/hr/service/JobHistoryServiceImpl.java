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
import com.hr.dto.CountriesPojo;
import com.hr.dto.DepartmentsPojo;
import com.hr.dto.EmployeesPojo;
import com.hr.dto.JobHistoryPojo;
import com.hr.dto.JobsPojo;
import com.hr.dto.LocationsPojo;
import com.hr.dto.RegionsPojo;
import com.hr.entity.DepartmentsEntity;
import com.hr.entity.EmployeesEntity;
import com.hr.entity.JobHistoryEntity;
import com.hr.entity.JobsEntity;
import com.hr.entity.LocationsEntity;
import com.hr.exceptions.ResourceAlreadyExistsException;
import com.hr.exceptions.ResourceNotFoundException;

@Service
public class JobHistoryServiceImpl implements JobHistoryService {
	
	@Autowired
    JobHistoryRepository jobHistoryRepository;
	
	List<JobHistoryPojo> jobHistoryList = new ArrayList<>();

	@Override
	public String addJobHistory(JobHistoryPojo newJobHistory) throws ResourceAlreadyExistsException {
		 try {
			 if (employeeExists(newJobHistory.getEmployeesPojo().getEmployeeId())) {
		            throw new ResourceAlreadyExistsException("Employee ID already exists");
		        }
		 
		
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
		}catch(Exception e) {
			throw new ResourceAlreadyExistsException("Error adding job history: " + e.getMessage());
		}
	}



	private boolean employeeExists(int employeeId) {
		return jobHistoryRepository.existsByEmployeesEntityEmployeeId(employeeId);
	}



	@Override
	public String modifyJobHistory(JobHistoryPojo newJobHistory) throws ResourceNotFoundException {
		
		try {
			int employeeId = newJobHistory.getEmployeesPojo().getEmployeeId();

	        // Check if the employee ID exists
	        if (!employeeExists(employeeId)) {
	            throw new ResourceNotFoundException("Employee ID not found");
	        }

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
		}catch(Exception e) {
			throw new RuntimeException("Failed to modify job history record",e);
		}
		}
	
	 
	 @Override
	 public Map<String, Integer> findExperienceOfEmployee(int employeeId)throws ResourceNotFoundException {
		  
	  		
	        List<JobHistoryEntity> jobHistories = jobHistoryRepository.findByEmployeesEntity_EmployeeId(employeeId);

	        if (jobHistories.isEmpty()) {
	        	
	        
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
	 public Map<String, Integer> listAllEmployeesWithLessThanOneYearExperience(int empId)throws ResourceNotFoundException {
	        // Retrieve job history records for the employee
	        List<JobHistoryEntity> jobHistoryList = jobHistoryRepository.findByEmployeesEntity_EmployeeId(empId);

	        // Calculate total duration of experience
	        Period totalExperience = calculateTotalExperience(jobHistoryList);
	        if (totalExperience.getYears() > 1 || (totalExperience.getYears() == 1 && totalExperience.getMonths() > 0)) {
	            throw new ResourceNotFoundException("Employee with ID " + empId + " has more than one year of experience");
	        }

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
	    
	    @Override
	    public List<JobHistoryPojo> getAllJobHistories() {
	        List<JobHistoryEntity> allJobHistoryEntities = jobHistoryRepository.findAll();
	        return convertToJobHistoryPojos(allJobHistoryEntities);
	    }

	    private List<JobHistoryPojo> convertToJobHistoryPojos(List<JobHistoryEntity> jobHistoryEntities) {
	        List<JobHistoryPojo> jobHistoryPojos = new ArrayList<>();
	        for (JobHistoryEntity entity : jobHistoryEntities) {
	            if (entity != null) {
	                JobHistoryPojo jobHistoryPojo = new JobHistoryPojo();
	                BeanUtils.copyProperties(entity, jobHistoryPojo);

	                EmployeesEntity employeesEntity = entity.getEmployeesEntity();
	                if (employeesEntity != null) {
	                    EmployeesPojo employeesPojo = new EmployeesPojo();
	                    BeanUtils.copyProperties(employeesEntity, employeesPojo);
	                    jobHistoryPojo.setEmployeesPojo(employeesPojo);
	                }

	                JobsEntity jobsEntity = entity.getJobsEntity();
	                if (jobsEntity != null) {
	                    JobsPojo jobsPojo = new JobsPojo();
	                    BeanUtils.copyProperties(jobsEntity, jobsPojo);
	                    jobHistoryPojo.setJobsPojo(jobsPojo);
	                }

	                DepartmentsEntity departmentsEntity = entity.getDepartmentsEntity();
	                if (departmentsEntity != null) {
	                    DepartmentsPojo departmentsPojo = new DepartmentsPojo();
	                    BeanUtils.copyProperties(departmentsEntity, departmentsPojo);
	                    jobHistoryPojo.setDepartmentsPojo(departmentsPojo);
	                }

	                jobHistoryPojos.add(jobHistoryPojo);
	            }
	        }
	        return jobHistoryPojos;
	    }
	    


}




		
	  
