package com.hr.controller;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hr.dto.EmployeesPojo;
import com.hr.entity.EmployeesEntity;
import com.hr.entity.JobsEntity;
import com.hr.service.EmployeeService;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@PostMapping
	public ResponseEntity<String> addEmployee(@RequestBody EmployeesPojo newEmployee) {
		return ResponseEntity.ok(employeeService.addEmployee(newEmployee));
	}

	@DeleteMapping("/{id}")
	public String DeleteByEmployeeId(@PathVariable("id") int id) {
		return employeeService.DeleteByEmployeeId(id);
	}
  
	@GetMapping("/findfname/{firstName}")
	public EmployeesPojo findByFirstName(@PathVariable("firstName") String firstName) {
		return employeeService.findByFirstName(firstName);
	}

	@GetMapping("/findemail/{email}")
	public EmployeesPojo findByEmail(@PathVariable("email") String email) {
		return employeeService.findByEmail(email);
	}

	@GetMapping("/findphone/{phone}")
	public EmployeesPojo findByphoneNumber(@PathVariable("phone") String phoneNumber) {
		return employeeService.findByphoneNumber(phoneNumber);
	}

	@GetMapping("/findAllEmployeeWithNoCommission")
	public List<EmployeesPojo> findAllEmployeeWithNoCommission() {
		return employeeService.findAllEmployeeWithNoCommission();
	}

	@PutMapping
	public String modifyEmployee(@RequestBody EmployeesPojo newEmployee) {
		return employeeService.UpdateEmployee(newEmployee);
	}

	@GetMapping("/{deptId}")
	public List<EmployeesEntity> getAllEmployeesByDepartmentId(@PathVariable("deptId") int deptId) {
		return employeeService.getAllEmployeesByDepartmentId(deptId);
	}
	
	@GetMapping("/getCount")
	 public List<Map<String, Object>> countEmployeesByDepartment(){
		return employeeService.countEmployeesByDepartment();
	}
	
	@GetMapping("/getMaxSalary/{empId}")
	 public Map<String, Object> findMaxSalaryOfJobByEmployeeId(@PathVariable("empId") int employeeId) {
		return employeeService.findMaxSalaryOfJobByEmployeeId(employeeId);
	}
	
	@PutMapping("/{deptId}/{pct}")
	 public String updateCommissionPercentageForSalesDepartment(@PathVariable("deptId") int departmentId,@PathVariable("pct") double newCommissionPct) {
		return employeeService.updateCommissionPercentageForSalesDepartment(departmentId, newCommissionPct);
	}
	
	@GetMapping("/TotalCmsPct/{deptId}")
	public Map<String, Object> findTotalCommissionIssuedToEmployeeByDepartment(@PathVariable("deptId") int departmentId) {
		 double totalCommission = employeeService.findTotalCommissionIssuedToEmployeeByDepartment(departmentId);
	        Map<String, Object> response = new HashMap<>();
	        response.put("departmentId", departmentId);
	        response.put("sum", totalCommission);
	        return response;
	}
	
	@GetMapping("/locationWiseCountryOfEmployees")
	public List<Map<String, Object>> countAllEmployeesGroupByLocation() {
		return employeeService.countAllEmployeesGroupByLocation();
	}
	
	@GetMapping("/hireDate/{fromDate}/{toDate}")
	public List<EmployeesEntity> listAllEmployeesByHireDate(@PathVariable("fromDate") Date fromHireDate,@PathVariable("toDate") Date toHireDate){
		return employeeService.listAllEmployeesByHireDate(fromHireDate, toHireDate);
	}

	@GetMapping("/findAllOpenPositions")
	public List<Map<String, Object>> FindAllOpenPositionsWhichWereNotFilled() {
		return employeeService.FindAllOpenPositionsWhichWereNotFilled();
	}	
}
