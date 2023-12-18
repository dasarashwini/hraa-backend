package com.hr.service;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.helpers.ThreadLocalMapOfStacks;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hr.dao.EmployeesRepository;
import com.hr.dto.CountriesPojo;
import com.hr.dto.DepartmentsPojo;
import com.hr.dto.EmployeesPojo;
import com.hr.dto.JobsPojo;
import com.hr.dto.LocationsPojo;
import com.hr.dto.ManagerPojo;
import com.hr.dto.RegionsPojo;
import com.hr.dto.UserPojo;
import com.hr.entity.CountriesEntity;
import com.hr.entity.DepartmentsEntity;
import com.hr.entity.EmployeesEntity;
import com.hr.entity.JobsEntity;
import com.hr.entity.LocationsEntity;
import com.hr.entity.ManagerEntity;
import com.hr.entity.RegionsEntity;
import com.hr.entity.UserEntity;
import com.hr.exceptions.ResourceAlreadyExistsException;
import com.hr.exceptions.ResourceNotFoundException;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	

		@Autowired
		EmployeesRepository employeesRepository;

		/******* Adding The New Employee ******/
		@Override
		public String addEmployee(EmployeesPojo newEmployee) throws ResourceAlreadyExistsException{
			String email = newEmployee.getEmail();
			if(employeesRepository.existsByEmail(email)) {
				throw new ResourceAlreadyExistsException("Employee or the Email is already Present please Try wiht other email");
			}
            //Copy Work is Taking place from Pojo to Entity
			EmployeesEntity employeesEntity = new EmployeesEntity();
			BeanUtils.copyProperties(newEmployee, employeesEntity);
			{
				// Copying the Foreign key objects of Jobs table
				JobsEntity jobsEntity =   new JobsEntity();
				BeanUtils.copyProperties(newEmployee.getJobsPojo(), jobsEntity);
				employeesEntity.setJobsEntity(jobsEntity);
				
				// Copying the Foreign key objects of Manager table
				ManagerEntity managerEntity = new ManagerEntity();
				BeanUtils.copyProperties(newEmployee.getManagerPojo(), managerEntity);
				employeesEntity.setManagerEntity(managerEntity);
				
				//  Copying the Foreign key objects of Department table
				DepartmentsEntity departmentsEntity = new DepartmentsEntity();
				BeanUtils.copyProperties(newEmployee.getDepartmentsPojo(), departmentsEntity);
				employeesEntity.setDepartmentsEntity(departmentsEntity);
				
				// Copying the Foreign key objects of User table
				UserEntity userEntity = new UserEntity();
				BeanUtils.copyProperties(newEmployee.getUserPojo(), userEntity);
				employeesEntity.setUserEntity(userEntity);

			}
			employeesRepository.save(employeesEntity);
			return "Record Created Successfully";
		}

		/******* Deleting The Existing Employee ******/
		@Override
		public String DeleteByEmployeeId(int id) throws ResourceNotFoundException {
	        Optional<EmployeesEntity> EmployeeId = employeesRepository.findById(id);
	        System.out.println(EmployeeId);
	        if(EmployeeId.isEmpty()) {
				throw new ResourceNotFoundException("Employee with ID " + id + " not found");
	        }
	        else {
			employeesRepository.deleteById(id);
			return "Record Deleteds Successfully"; 
	        }
		}
		
		/******* Finding the Employee By his First Name ******/
		@Override
		public List<EmployeesPojo> findByFirstName(String firstName) throws ResourceNotFoundException{
			List<EmployeesEntity> employeesentity = employeesRepository.findAllByFirstName(firstName);
			if(employeesentity.isEmpty()) {
				throw new ResourceNotFoundException(firstName+" Not Found");
			}
			List<EmployeesPojo> allEmployeesPojo = new ArrayList<>();
			for(EmployeesEntity eachEmployeesEntity:employeesentity) {
			EmployeesPojo employeesPojo = new EmployeesPojo();
			BeanUtils.copyProperties(eachEmployeesEntity, employeesPojo);
				JobsPojo jobsPojo =   new JobsPojo();
				BeanUtils.copyProperties(eachEmployeesEntity.getJobsEntity(), jobsPojo);
				employeesPojo.setJobsPojo(jobsPojo);
					ManagerPojo ManagerPojo = new ManagerPojo();
					BeanUtils.copyProperties(eachEmployeesEntity.getManagerEntity(), ManagerPojo);
					employeesPojo.setManagerPojo(ManagerPojo);
						DepartmentsPojo departmentsPojo = new DepartmentsPojo();
						BeanUtils.copyProperties(eachEmployeesEntity.getDepartmentsEntity(), departmentsPojo);
							LocationsEntity locationsEntity = eachEmployeesEntity.getDepartmentsEntity().getLocationsEntity();
							LocationsPojo locationsPojo = new LocationsPojo();
							BeanUtils.copyProperties(locationsEntity, locationsPojo);
								CountriesEntity countriesEntity = eachEmployeesEntity.getDepartmentsEntity().getLocationsEntity().getCountriesEntity();
								CountriesPojo countriesPojo = new CountriesPojo();
								BeanUtils.copyProperties(countriesEntity, countriesPojo);
									RegionsEntity regionsEntity = eachEmployeesEntity.getDepartmentsEntity().getLocationsEntity().getCountriesEntity().getRegionsEntity();
									RegionsPojo regionsPojo = new RegionsPojo();
									BeanUtils.copyProperties(regionsEntity, regionsPojo);
								countriesPojo.setRegionsPojo(regionsPojo);
							locationsPojo.setCountriesPojo(countriesPojo);
						departmentsPojo.setLocationsPojo(locationsPojo);
						UserPojo userPojo = new UserPojo();
						BeanUtils.copyProperties(eachEmployeesEntity.getUserEntity(), userPojo);
						employeesPojo.setUserPojo(userPojo);
					employeesPojo.setDepartmentsPojo(departmentsPojo);
				allEmployeesPojo.add(employeesPojo);
			}
			return allEmployeesPojo;
		}

		/******* Finding the Employee By his Email Id ******/
		@Override
		public EmployeesPojo findByEmail(String email){
			EmployeesEntity employeesentity = employeesRepository.findByEmail(email);
			if(employeesentity == null) {
				throw new ResourceNotFoundException(email+" Does not Exist");	
			}
			EmployeesPojo employeesPojo = new EmployeesPojo();
			BeanUtils.copyProperties(employeesentity, employeesPojo);
				JobsPojo jobsPojo =   new JobsPojo();
				BeanUtils.copyProperties(employeesentity.getJobsEntity(), jobsPojo);
				employeesPojo.setJobsPojo(jobsPojo);
					ManagerPojo ManagerPojo = new ManagerPojo();
					BeanUtils.copyProperties(employeesentity.getManagerEntity(), ManagerPojo);
					employeesPojo.setManagerPojo(ManagerPojo);
						DepartmentsPojo departmentsPojo = new DepartmentsPojo();
						BeanUtils.copyProperties(employeesentity.getDepartmentsEntity(), departmentsPojo);
							LocationsEntity locationsEntity = employeesentity.getDepartmentsEntity().getLocationsEntity();
							LocationsPojo locationsPojo = new LocationsPojo();
							BeanUtils.copyProperties(locationsEntity, locationsPojo);
								CountriesEntity countriesEntity = employeesentity.getDepartmentsEntity().getLocationsEntity().getCountriesEntity();
								CountriesPojo countriesPojo = new CountriesPojo();
								BeanUtils.copyProperties(countriesEntity, countriesPojo);
									RegionsEntity regionsEntity = employeesentity.getDepartmentsEntity().getLocationsEntity().getCountriesEntity().getRegionsEntity();
									RegionsPojo regionsPojo = new RegionsPojo();
									BeanUtils.copyProperties(regionsEntity, regionsPojo);
								countriesPojo.setRegionsPojo(regionsPojo);
							locationsPojo.setCountriesPojo(countriesPojo);
						departmentsPojo.setLocationsPojo(locationsPojo);
						UserPojo userPojo = new UserPojo();
						BeanUtils.copyProperties(employeesentity.getUserEntity(), userPojo);
						employeesPojo.setUserPojo(userPojo);
					employeesPojo.setDepartmentsPojo(departmentsPojo);
			return employeesPojo;
		}
		
		/******* Finding the Employee By his Phone Number ******/
		@Override
		public EmployeesPojo findByphoneNumber(String phoneNumber) throws ResourceNotFoundException{
			EmployeesEntity employeesentity = employeesRepository.findByPhoneNumber(phoneNumber);
			 if(employeesentity == null) {
				 throw new ResourceNotFoundException(phoneNumber+" Not Found");
			 }
			 EmployeesPojo employeesPojo = new EmployeesPojo();
				BeanUtils.copyProperties(employeesentity, employeesPojo);
					JobsPojo jobsPojo =   new JobsPojo();
					BeanUtils.copyProperties(employeesentity.getJobsEntity(), jobsPojo);
					employeesPojo.setJobsPojo(jobsPojo);
						ManagerEntity managerEntity = new ManagerEntity();
						BeanUtils.copyProperties(employeesentity.getManagerEntity(), managerEntity);
						employeesentity.setManagerEntity(managerEntity);
							DepartmentsPojo departmentsPojo = new DepartmentsPojo();
							BeanUtils.copyProperties(employeesentity.getDepartmentsEntity(), departmentsPojo);
								LocationsEntity locationsEntity = employeesentity.getDepartmentsEntity().getLocationsEntity();
								LocationsPojo locationsPojo = new LocationsPojo();
								BeanUtils.copyProperties(locationsEntity, locationsPojo);
									CountriesEntity countriesEntity = employeesentity.getDepartmentsEntity().getLocationsEntity().getCountriesEntity();
									CountriesPojo countriesPojo = new CountriesPojo();
									BeanUtils.copyProperties(countriesEntity, countriesPojo);
										RegionsEntity regionsEntity = employeesentity.getDepartmentsEntity().getLocationsEntity().getCountriesEntity().getRegionsEntity();
										RegionsPojo regionsPojo = new RegionsPojo();
										BeanUtils.copyProperties(regionsEntity, regionsPojo);
									countriesPojo.setRegionsPojo(regionsPojo);
								locationsPojo.setCountriesPojo(countriesPojo);
							departmentsPojo.setLocationsPojo(locationsPojo);
							UserPojo userPojo = new UserPojo();
							BeanUtils.copyProperties(employeesentity.getUserEntity(), userPojo);
							employeesPojo.setUserPojo(userPojo);
						employeesPojo.setDepartmentsPojo(departmentsPojo);
				return employeesPojo;
		}

		/******* Finding the Employees who does not got any Commission ******/
		@Override
		public List<EmployeesPojo> findAllEmployeeWithNoCommission() throws ResourceNotFoundException{
			List<EmployeesEntity> allEmployeesEntity = employeesRepository.findAllEmployeeWithNoCommission();
			if(allEmployeesEntity == null) {
				throw new ResourceNotFoundException("No Record Found");
			}
			List<EmployeesPojo> allEmployeesPojos = new ArrayList<EmployeesPojo>();
			for(EmployeesEntity eachEmployeesEntity : allEmployeesEntity) {
				EmployeesPojo eachEmployeesPojo = new EmployeesPojo();
				BeanUtils.copyProperties(eachEmployeesEntity, eachEmployeesPojo);
					JobsPojo jobsPojo = new JobsPojo();
					BeanUtils.copyProperties(eachEmployeesEntity.getJobsEntity(), jobsPojo);
					eachEmployeesPojo.setJobsPojo(jobsPojo);
						ManagerEntity managerEntity = new ManagerEntity();
						BeanUtils.copyProperties(eachEmployeesEntity.getManagerEntity(), managerEntity);
						eachEmployeesEntity.setManagerEntity(managerEntity);
							DepartmentsPojo departmentsPojo = new DepartmentsPojo();
							BeanUtils.copyProperties(eachEmployeesEntity.getDepartmentsEntity(), departmentsPojo);
						eachEmployeesPojo.setDepartmentsPojo(departmentsPojo);
				allEmployeesPojos.add(eachEmployeesPojo);
			}
			return allEmployeesPojos;
		}

		/******* Updating the Employee Details ******/
		@Override
		public String UpdateEmployee(EmployeesPojo newEmployee) throws ResourceNotFoundException{
			String email = newEmployee.getEmail();
			if(employeesRepository.existsByEmail(email)) {
				EmployeesEntity employeesEntity = new EmployeesEntity();
				BeanUtils.copyProperties(newEmployee, employeesEntity);
					JobsEntity jobsEntity =   new JobsEntity();
					BeanUtils.copyProperties(newEmployee.getJobsPojo(), jobsEntity);
					employeesEntity.setJobsEntity(jobsEntity);
						ManagerEntity managerEntity = new ManagerEntity();
						BeanUtils.copyProperties(newEmployee.getManagerPojo(), managerEntity);
						employeesEntity.setManagerEntity(managerEntity);
							DepartmentsEntity departmentsEntity = new DepartmentsEntity();
							BeanUtils.copyProperties(newEmployee.getDepartmentsPojo(), departmentsEntity);
							employeesEntity.setDepartmentsEntity(departmentsEntity);
								UserEntity userEntity = new UserEntity();
								BeanUtils.copyProperties(newEmployee.getUserPojo(), userEntity);
								employeesEntity.setUserEntity(userEntity);
						employeesRepository.save(employeesEntity);
				return "Record Modified Successfully";
			}
			else {
				throw new ResourceNotFoundException("Unable to find the Employee with Email "+email);
			 }
		}

		/******* Getting the all Employees who are working in a particular department ******/
		@Override
		public List<EmployeesPojo> getAllEmployeesByDepartmentId(int departmentId) throws ResourceNotFoundException{	
			List<EmployeesEntity> allEmployees = employeesRepository.findAllEmployeesByDepartmentId(departmentId);
			if(allEmployees!=null) {
				List<EmployeesPojo> allEmployeesPojo = new ArrayList<>();
				for(EmployeesEntity eachEmployeesEntity:allEmployees) {
				EmployeesPojo employeesPojo = new EmployeesPojo();
				BeanUtils.copyProperties(eachEmployeesEntity, employeesPojo);
					JobsPojo jobsPojo =   new JobsPojo();
					BeanUtils.copyProperties(eachEmployeesEntity.getJobsEntity(), jobsPojo);
					employeesPojo.setJobsPojo(jobsPojo);
						ManagerPojo ManagerPojo = new ManagerPojo();
						BeanUtils.copyProperties(eachEmployeesEntity.getManagerEntity(), ManagerPojo);
						employeesPojo.setManagerPojo(ManagerPojo);
							DepartmentsPojo departmentsPojo = new DepartmentsPojo();
							BeanUtils.copyProperties(eachEmployeesEntity.getDepartmentsEntity(), departmentsPojo);
								LocationsEntity locationsEntity = eachEmployeesEntity.getDepartmentsEntity().getLocationsEntity();
								LocationsPojo locationsPojo = new LocationsPojo();
								BeanUtils.copyProperties(locationsEntity, locationsPojo);
									CountriesEntity countriesEntity = eachEmployeesEntity.getDepartmentsEntity().getLocationsEntity().getCountriesEntity();
									CountriesPojo countriesPojo = new CountriesPojo();
									BeanUtils.copyProperties(countriesEntity, countriesPojo);
										RegionsEntity regionsEntity = eachEmployeesEntity.getDepartmentsEntity().getLocationsEntity().getCountriesEntity().getRegionsEntity();
										RegionsPojo regionsPojo = new RegionsPojo();
										BeanUtils.copyProperties(regionsEntity, regionsPojo);
									countriesPojo.setRegionsPojo(regionsPojo);
								locationsPojo.setCountriesPojo(countriesPojo);
							departmentsPojo.setLocationsPojo(locationsPojo);
							UserPojo userPojo = new UserPojo();
							BeanUtils.copyProperties(eachEmployeesEntity.getUserEntity(), userPojo);
							employeesPojo.setUserPojo(userPojo);
						employeesPojo.setDepartmentsPojo(departmentsPojo);
					allEmployeesPojo.add(employeesPojo);
				}
				return allEmployeesPojo;
			}
			else {
				throw new ResourceNotFoundException("No Record Found");
			}
		}

		/******* Getting a count of employees working in different departments ******/
	    @Override
	    public List<Map<String, Object>> countEmployeesByDepartment() throws ResourceNotFoundException{
		       List<Object[]> results = employeesRepository.countEmployeesByDepartment();
		       if(results!=null) {
		       List<Map<String, Object>> countsList = new ArrayList<>();
		        results.forEach(result -> {
		            String departmentName = (String) result[0];
		            long count = (Long) result[1];
		            Map<String, Object> countMap = new HashMap<>();
		            countMap.put("departmentName", departmentName);
		            countMap.put("count", count);
		            countsList.add(countMap);
		        });
		        return countsList;
		       }
		       else {
		    	   throw new ResourceNotFoundException("No Record Found");
		       }
	     }
		 
	    /******* finding the Maximum salary of a job by Employee Id ******/
		 @Override
		 public Map<String, Object> findMaxSalaryOfJobByEmployeeId(int employeeId) {
		        List<Object[]> result = employeesRepository.findMaxSalaryOfJobByEmployeeId(employeeId);
		        
		        if (result.isEmpty()) {
		            return Collections.emptyMap();
		        }
		        Map<String, Object> resultMap = new HashMap<>();
		        resultMap.put("Job Title", result.get(0)[0]);
		        resultMap.put("max salary", result.get(0)[1]);
		        return resultMap;
		  }
		  
		 /******* Updating the commission percentage foe sales department ******/
		  @Override
		  public String updateCommissionPercentageForSalesDepartment(int departmentId, double newCommissionPct) throws ResourceNotFoundException{
		        List<EmployeesEntity> salesEmployees = employeesRepository.findByDepartmentsEntityDepartmentId(departmentId);
		        if(salesEmployees.isEmpty())  throw new ResourceNotFoundException("Unable to find the Department Id");
		        else {
		        	 for (EmployeesEntity employee : salesEmployees) {
				            employee.setCommissionPct(newCommissionPct);
				            employeesRepository.save(employee);
				        }
		        	return "Commission PCT Updated SuccessFully";
		         }  
		  }
		  
		  /******* Finding the total commission issued to the Employee by the Department ******/
		  @Override
		  public double findTotalCommissionIssuedToEmployeeByDepartment(int departmentId) {
		        return employeesRepository.findTotalCommissionIssuedToEmployeeByDepartment(departmentId);
		    }
		  
		  /******* Getting the count of employees who are in same location ******/
		  @Override
		  public List<Map<String, Object>> countAllEmployeesGroupByLocation() throws ResourceNotFoundException{
		        List<Object[]> results = employeesRepository.countEmployeesByLocation();
		        if(results!=null) {
		        List<Map<String, Object>> response = new ArrayList<>();
		        for (Object[] result : results) {
		            Map<String, Object> entry = new HashMap<>();
		            entry.put("location", result[0]); // Assuming the first element is location
		            entry.put("count", result[1]); // Assuming the second element is count
		            response.add(entry);
                    }
		        return response;
		        }
		        else {
		        	 throw new ResourceNotFoundException("No record found");
		        }
		  }

		  /******* Getting a List of employees by their hire date ******/
		  @Override
		  public List<EmployeesEntity> listAllEmployeesByHireDate(Date fromHireDate, Date toHireDate) throws ResourceNotFoundException {
			  List<EmployeesEntity> employeesEntities = employeesRepository.findByHireDateBetween(fromHireDate, toHireDate);
			  if(employeesEntities!=null) {
				  return employeesEntities;
			  }
			  else {
				  throw new ResourceNotFoundException("All Positions are filled");
			  }	  
		  }

		  /******* Finding the All Open Positions Which were Not Filled ******/
		  @Override
		  public List<Map<String, Object>> FindAllOpenPositionsWhichWereNotFilled() throws ResourceNotFoundException{
		     List<Object[]> result = employeesRepository.getAllOpenPositionswhichwasnotfilled();
		     if(result!=null) {
		     List<Map<String, Object>> response = new ArrayList<>();
		     for (Object[] results : result) {
		        Map<String, Object> entry = new HashMap<>();
		        entry.put("jobId", results[0]); // Assuming the first element is job_id
		        entry.put("jobTitle", results[1]); // Assuming the second element is job_title
		        response.add(entry);
		     }
		     return response;
		     }
		     else {
		    	 throw new ResourceNotFoundException("All Positions are filled");
		     }
		  }

		@Override
		public List<EmployeesPojo> findAllEmployees() throws ResourceNotFoundException {
			List<EmployeesEntity> employeesentity = employeesRepository.findAll();
			if(employeesentity.isEmpty()) {
				throw new ResourceNotFoundException(" No Record Found");
			}
			List<EmployeesPojo> allEmployeesPojo = new ArrayList<>();
			for(EmployeesEntity eachEmployeesEntity:employeesentity) {
			EmployeesPojo employeesPojo = new EmployeesPojo();
			BeanUtils.copyProperties(eachEmployeesEntity, employeesPojo);
				JobsPojo jobsPojo =   new JobsPojo();
				BeanUtils.copyProperties(eachEmployeesEntity.getJobsEntity(), jobsPojo);
				employeesPojo.setJobsPojo(jobsPojo);
					ManagerPojo ManagerPojo = new ManagerPojo();
					BeanUtils.copyProperties(eachEmployeesEntity.getManagerEntity(), ManagerPojo);
					employeesPojo.setManagerPojo(ManagerPojo);
						DepartmentsPojo departmentsPojo = new DepartmentsPojo();
						BeanUtils.copyProperties(eachEmployeesEntity.getDepartmentsEntity(), departmentsPojo);
							LocationsEntity locationsEntity = eachEmployeesEntity.getDepartmentsEntity().getLocationsEntity();
							LocationsPojo locationsPojo = new LocationsPojo();
							BeanUtils.copyProperties(locationsEntity, locationsPojo);
								CountriesEntity countriesEntity = eachEmployeesEntity.getDepartmentsEntity().getLocationsEntity().getCountriesEntity();
								CountriesPojo countriesPojo = new CountriesPojo();
								BeanUtils.copyProperties(countriesEntity, countriesPojo);
									RegionsEntity regionsEntity = eachEmployeesEntity.getDepartmentsEntity().getLocationsEntity().getCountriesEntity().getRegionsEntity();
									RegionsPojo regionsPojo = new RegionsPojo();
									BeanUtils.copyProperties(regionsEntity, regionsPojo);
								countriesPojo.setRegionsPojo(regionsPojo);
							locationsPojo.setCountriesPojo(countriesPojo);
						departmentsPojo.setLocationsPojo(locationsPojo);
						UserPojo userPojo = new UserPojo();
						BeanUtils.copyProperties(eachEmployeesEntity.getUserEntity(), userPojo);
						employeesPojo.setUserPojo(userPojo);
					employeesPojo.setDepartmentsPojo(departmentsPojo);
				allEmployeesPojo.add(employeesPojo);
			}
			return allEmployeesPojo;
		}
}
	
		

