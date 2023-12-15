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

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@PostMapping
	public ResponseEntity<String> addEmployee(@Valid @RequestBody EmployeesPojo newEmployee) {
		return ResponseEntity.ok(employeeService.addEmployee(newEmployee));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> DeleteByEmployeeId( @PathVariable("id") int id) {
		return ResponseEntity.ok(employeeService.DeleteByEmployeeId(id));
	}
  
	@GetMapping("/findfname/{firstName}")
	public ResponseEntity<List<EmployeesPojo>> findByFirstName( @PathVariable("firstName") String firstName) {
		return ResponseEntity.ok(employeeService.findByFirstName(firstName));
	}

	@GetMapping("/findemail/{email}")
	public EmployeesPojo findByEmail( @PathVariable("email") String email) {
		return employeeService.findByEmail(email);
	}

	@GetMapping("/findphone/{phone}")
	public ResponseEntity<EmployeesPojo> findByphoneNumber( @PathVariable("phone") String phoneNumber) {
		return ResponseEntity.ok(employeeService.findByphoneNumber(phoneNumber));
	}

	@GetMapping("/findAllEmployeeWithNoCommission")
	public ResponseEntity<List<EmployeesPojo>> findAllEmployeeWithNoCommission() {
		return ResponseEntity.ok(employeeService.findAllEmployeeWithNoCommission());
	}

	@PutMapping
	public ResponseEntity<String> modifyEmployee(@Valid @RequestBody EmployeesPojo newEmployee) {
		return ResponseEntity.ok(employeeService.UpdateEmployee(newEmployee));
	}

	@GetMapping("/{deptId}")
	public ResponseEntity<List<EmployeesEntity>> getAllEmployeesByDepartmentId( @PathVariable("deptId") int deptId) {
		return ResponseEntity.ok(employeeService.getAllEmployeesByDepartmentId(deptId));
	}
	
	@GetMapping("/getCount")
	 public ResponseEntity<List<Map<String, Object>>> countEmployeesByDepartment(){
		return ResponseEntity.ok(employeeService.countEmployeesByDepartment());
	}
	
	@GetMapping("/getMaxSalary/{empId}")
	 public ResponseEntity<Map<String, Object>> findMaxSalaryOfJobByEmployeeId( @PathVariable("empId") int employeeId) {
		return ResponseEntity.ok(employeeService.findMaxSalaryOfJobByEmployeeId(employeeId));
	}
	
	@PutMapping("/{deptId}/{pct}")
	 public ResponseEntity<String> updateCommissionPercentageForSalesDepartment(@Valid @PathVariable("deptId") int departmentId,@PathVariable("pct") double newCommissionPct) {
		return ResponseEntity.ok(employeeService.updateCommissionPercentageForSalesDepartment(departmentId, newCommissionPct));
	}
	
	@GetMapping("/TotalCmsPct/{deptId}")
	public ResponseEntity<Map<String, Object>> findTotalCommissionIssuedToEmployeeByDepartment( @PathVariable("deptId") int departmentId) {
		 double totalCommission = employeeService.findTotalCommissionIssuedToEmployeeByDepartment(departmentId);
	        Map<String, Object> response = new HashMap<>();
	        response.put("departmentId", departmentId);
	        response.put("sum", totalCommission);
	        return ResponseEntity.ok(response);
	}
	
	@GetMapping("/locationWiseCountryOfEmployees")
	public ResponseEntity<List<Map<String, Object>>> countAllEmployeesGroupByLocation() {
		return ResponseEntity.ok(employeeService.countAllEmployeesGroupByLocation());
	}
	
	@GetMapping("/hireDate/{fromDate}/{toDate}")
	public ResponseEntity<List<EmployeesEntity>> listAllEmployeesByHireDate( @PathVariable("fromDate") Date fromHireDate,@PathVariable("toDate") Date toHireDate){
		return ResponseEntity.ok(employeeService.listAllEmployeesByHireDate(fromHireDate, toHireDate));
	}

	@GetMapping("/findAllOpenPositions")
	public ResponseEntity<List<Map<String, Object>>> FindAllOpenPositionsWhichWereNotFilled() {
		return ResponseEntity.ok(employeeService.FindAllOpenPositionsWhichWereNotFilled());
	}	
}
