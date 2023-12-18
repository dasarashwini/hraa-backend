package com.hr.service;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hr.dto.EmployeesPojo;
import com.hr.entity.EmployeesEntity;
import com.hr.entity.JobsEntity;
import com.hr.exceptions.ResourceAlreadyExistsException;
import com.hr.exceptions.ResourceNotFoundException;

@Service
public interface EmployeeService {
	
		public String addEmployee(EmployeesPojo newEmployee) throws ResourceAlreadyExistsException;
		public String DeleteByEmployeeId(int id) throws ResourceNotFoundException;
		public List<EmployeesPojo> findByFirstName(String firstName) throws ResourceNotFoundException;
		public EmployeesPojo findByEmail(String email) throws ResourceNotFoundException;
		public EmployeesPojo findByphoneNumber(String phoneNumber) throws ResourceNotFoundException;
		public List<EmployeesPojo> findAllEmployeeWithNoCommission() throws ResourceNotFoundException;
		public String UpdateEmployee(EmployeesPojo newEmployee) throws ResourceNotFoundException;	
		public List<EmployeesPojo> getAllEmployeesByDepartmentId(int departmentId) throws ResourceNotFoundException; 
	    public List<Map<String, Object>> countEmployeesByDepartment() throws ResourceNotFoundException;
		public Map<String, Object> findMaxSalaryOfJobByEmployeeId(int employeeId) throws ResourceNotFoundException;
	    public String updateCommissionPercentageForSalesDepartment(int departmentId, double newCommissionPct);
		public double findTotalCommissionIssuedToEmployeeByDepartment(int departmentId);
	    public List<Map<String, Object>> countAllEmployeesGroupByLocation() throws ResourceNotFoundException;
	    public List<EmployeesEntity> listAllEmployeesByHireDate(Date fromHireDate, Date toHireDate) throws ResourceNotFoundException;
		public List<Map<String, Object>> FindAllOpenPositionsWhichWereNotFilled() throws ResourceNotFoundException;
		public List<EmployeesPojo> findAllEmployees() throws ResourceNotFoundException;
}
