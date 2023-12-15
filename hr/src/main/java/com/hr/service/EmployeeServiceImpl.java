package com.hr.service;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hr.dao.EmployeesRepository;
import com.hr.dto.CountriesPojo;
import com.hr.dto.DepartmentsPojo;
import com.hr.dto.EmployeesPojo;
import com.hr.dto.JobsPojo;
import com.hr.dto.LocationsPojo;
import com.hr.dto.RegionsPojo;
import com.hr.entity.CountriesEntity;
import com.hr.entity.DepartmentsEntity;
import com.hr.entity.EmployeesEntity;
import com.hr.entity.JobsEntity;
import com.hr.entity.LocationsEntity;
import com.hr.entity.RegionsEntity;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
		@Autowired
		EmployeesRepository employeesRepository;

		/******* Adding The New Employee ******/
		@Override
		public String addEmployee(EmployeesPojo newEmployee) {
            //Copy Work is Taking place from Pojo to Entity
			EmployeesEntity employeesEntity = new EmployeesEntity();
			BeanUtils.copyProperties(newEmployee, employeesEntity);
			// Copying the Foreign key objects of Jobs table
			{
				JobsEntity jobsEntity =   new JobsEntity();
				BeanUtils.copyProperties(newEmployee.getJobsPojo(), jobsEntity);
				employeesEntity.setJobsEntity(jobsEntity);
				//  Copying the Foreign key objects of Department table
				{
					DepartmentsEntity departmentsEntity = new DepartmentsEntity();
					BeanUtils.copyProperties(newEmployee.getDepartmentsPojo(), departmentsEntity);
					employeesEntity.setDepartmentsEntity(departmentsEntity);
				}
			}
			employeesRepository.save(employeesEntity);
			return "Record Created Successfully";
		}

		/******* Deleting The Existing Employee ******/
		@Override
		public String DeleteByEmployeeId(int id) {
			employeesRepository.deleteById(id);
			return "Record Deleted Successfully";
		}
		
		/******* Finding the Employee By his First Name ******/
		@Override
		public EmployeesPojo findByFirstName(String firstName) {
			EmployeesEntity employeesentity = employeesRepository.findByFirstName(firstName);
			EmployeesPojo employeesPojo = new EmployeesPojo();
			BeanUtils.copyProperties(employeesentity, employeesPojo);
				JobsPojo jobsPojo =   new JobsPojo();
				BeanUtils.copyProperties(employeesentity.getJobsEntity(), jobsPojo);
				employeesPojo.setJobsPojo(jobsPojo);
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
					employeesPojo.setDepartmentsPojo(departmentsPojo);
			return employeesPojo;
		}

		/******* Finding the Employee By his Email Id ******/
		@Override
		public EmployeesPojo findByEmail(String email) {
			EmployeesEntity employeesentity = employeesRepository.findByEmail(email);
			EmployeesPojo employeesPojo = new EmployeesPojo();
			BeanUtils.copyProperties(employeesentity, employeesPojo);
				JobsPojo jobsPojo =   new JobsPojo();
				BeanUtils.copyProperties(employeesentity.getJobsEntity(), jobsPojo);
				employeesPojo.setJobsPojo(jobsPojo);
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
			    employeesPojo.setDepartmentsPojo(departmentsPojo);
			return employeesPojo;
		}

		/******* Finding the Employee By his Phone Number ******/
		@Override
		public EmployeesPojo findByphoneNumber(String phoneNumber) {
			EmployeesEntity employeesentity = employeesRepository.findByphoneNumber(phoneNumber);
			EmployeesPojo employeesPojo = new EmployeesPojo();
			BeanUtils.copyProperties(employeesentity, employeesPojo);
				JobsPojo jobsPojo =   new JobsPojo();
				BeanUtils.copyProperties(employeesentity.getJobsEntity(), jobsPojo);
				employeesPojo.setJobsPojo(jobsPojo);
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
				employeesPojo.setDepartmentsPojo(departmentsPojo);
			return employeesPojo;
		}

		/******* Finding the Employees who does not got any Commission ******/
		@Override
		public List<EmployeesPojo> findAllEmployeeWithNoCommission() {
			List<EmployeesEntity> allEmployeesEntity = employeesRepository.findAllEmployeeWithNoCommission();
			List<EmployeesPojo> allEmployeesPojos = new ArrayList<EmployeesPojo>();
			for(EmployeesEntity eachEmployeesEntity : allEmployeesEntity) {
				EmployeesPojo eachEmployeesPojo = new EmployeesPojo();
				BeanUtils.copyProperties(eachEmployeesEntity, eachEmployeesPojo);
					JobsPojo jobsPojo = new JobsPojo();
					BeanUtils.copyProperties(eachEmployeesEntity.getJobsEntity(), jobsPojo);
					eachEmployeesPojo.setJobsPojo(jobsPojo);
						DepartmentsPojo departmentsPojo = new DepartmentsPojo();
						BeanUtils.copyProperties(eachEmployeesEntity.getDepartmentsEntity(), departmentsPojo);
						eachEmployeesPojo.setDepartmentsPojo(departmentsPojo);
				allEmployeesPojos.add(eachEmployeesPojo);
			}
			return allEmployeesPojos;
		}

		/******* Updating the Employee Details ******/
		@Override
		public String UpdateEmployee(EmployeesPojo newEmployee){
			EmployeesEntity employeesEntity = new EmployeesEntity();
			BeanUtils.copyProperties(newEmployee, employeesEntity);
				JobsEntity jobsEntity =   new JobsEntity();
				BeanUtils.copyProperties(newEmployee.getJobsPojo(), jobsEntity);
				employeesEntity.setJobsEntity(jobsEntity);
					DepartmentsEntity departmentsEntity = new DepartmentsEntity();
					BeanUtils.copyProperties(newEmployee.getDepartmentsPojo(), departmentsEntity);
					employeesEntity.setDepartmentsEntity(departmentsEntity);
					employeesRepository.save(employeesEntity);
			return "Record Modified Successfully";
		}

		/******* Getting the all Employees who are working in a particular department ******/
		@Override
		public List<EmployeesEntity> getAllEmployeesByDepartmentId(int departmentId) {	
			return employeesRepository.findAllEmployeesByDepartmentId(departmentId);
		}

		/******* Getting a count of employees working in different departments ******/
	    @Override
	    public List<Map<String, Object>> countEmployeesByDepartment() {
		       List<Object[]> results = employeesRepository.countEmployeesByDepartment();
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
		  public String updateCommissionPercentageForSalesDepartment(int departmentId, double newCommissionPct) {
		        List<EmployeesEntity> salesEmployees = employeesRepository.findByDepartmentsEntityDepartmentId(departmentId);
		        if(salesEmployees.isEmpty()) return null;
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
		  public List<Map<String, Object>> countAllEmployeesGroupByLocation() {
		        List<Object[]> results = employeesRepository.countEmployeesByLocation();
		        List<Map<String, Object>> response = new ArrayList<>();
		        for (Object[] result : results) {
		            Map<String, Object> entry = new HashMap<>();
		            entry.put("location", result[0]); // Assuming the first element is location
		            entry.put("count", result[1]); // Assuming the second element is count
		            response.add(entry);
                    }
		        return response;
		  }

		  /******* Getting a List of employees by their hire date ******/
		  @Override
		  public List<EmployeesEntity> listAllEmployeesByHireDate(Date fromHireDate, Date toHireDate) {
			  return employeesRepository.findByHireDateBetween(fromHireDate, toHireDate);
		  }

		  /******* Finding the All Open Positions Which were Not Filled ******/
		  @Override
		  public List<Map<String, Object>> FindAllOpenPositionsWhichWereNotFilled() {
		     List<Object[]> result = employeesRepository.getAllOpenPositionswhichwasnotfilled();
		     List<Map<String, Object>> response = new ArrayList<>();
		     for (Object[] results : result) {
		        Map<String, Object> entry = new HashMap<>();
		        entry.put("job_id", results[0]); // Assuming the first element is job_id
		        entry.put("job_title", results[1]); // Assuming the second element is job_title
		        response.add(entry);
		     }
		     return response;
		  }

}
	
		

