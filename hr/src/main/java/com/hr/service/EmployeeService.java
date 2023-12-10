package com.hr.service;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hr.dto.EmployeesPojo;
import com.hr.entity.EmployeesEntity;
import com.hr.entity.JobsEntity;

@Service
public interface EmployeeService {
	
		public String addEmployee(EmployeesPojo newEmployee);
		public String DeleteByEmployeeId(int id);
		public EmployeesPojo findByFirstName(String firstName);
		public EmployeesPojo findByEmail(String email);
		public EmployeesPojo findByphoneNumber(String phoneNumber);
		public List<EmployeesPojo> findAllEmployeeWithNoCommission();
		public String UpdateEmployee(EmployeesPojo newEmployee);	
		public List<EmployeesEntity> getAllEmployeesByDepartmentId(int departmentId); 
	    public List<Map<String, Object>> countEmployeesByDepartment();
		public Map<String, Object> findMaxSalaryOfJobByEmployeeId(int employeeId);
	    public String updateCommissionPercentageForSalesDepartment(int departmentId, double newCommissionPct);
		public double findTotalCommissionIssuedToEmployeeByDepartment(int departmentId);
	    public List<Map<String, Object>> countAllEmployeesGroupByLocation();
	    public List<EmployeesEntity> listAllEmployeesByHireDate(Date fromHireDate, Date toHireDate);
		public List<Map<String, Object>> FindAllOpenPositionsWhichWereNotFilled();
}
