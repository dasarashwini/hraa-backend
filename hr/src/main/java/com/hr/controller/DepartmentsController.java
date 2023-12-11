package com.hr.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hr.dto.DepartmentsPojo;
import com.hr.service.DepartmentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/department")
public class DepartmentsController {
	@Autowired
	DepartmentService  departmentsService;
	
	@PostMapping
	public ResponseEntity<String> addnewDepartment(@Valid @RequestBody DepartmentsPojo departmentsPojo) {
		String msge= departmentsService.addnewDepartment(departmentsPojo);
		return new ResponseEntity<String>(msge,HttpStatus.OK);
		
	}
	@PutMapping
	public ResponseEntity<String> updatedepartmentEntity(@Valid @RequestBody DepartmentsPojo departmentsPojo) {
		String msge= departmentsService.updatedepartmentEntity(departmentsPojo);
		return new ResponseEntity<String>(msge,HttpStatus.OK);
		
	}
	@DeleteMapping("/{departmentId}")
	public String deletedepartmentById(@Valid @PathVariable("departmentId") int departmentId) {
		return departmentsService.deletedepartmentById(departmentId);
		
	}
	@GetMapping("/{empid}")
	public List<DepartmentsPojo> getallDepartmentsWithEmployees(@Valid @PathVariable("empid")int empId){
		List<DepartmentsPojo>departmentsPojoList= departmentsService.getallDepartmentsByEmployees(empId);
		return departmentsPojoList;
		
	}
	
	
	@GetMapping("/findMaxSalary/{deptId}")
	public Map<String, Object> findMaxSalaryInDepartment(@Valid @PathVariable("deptId") Integer departmentId){
		return departmentsService.findMaxSalaryInDepartment(departmentId);
	}
	
	
	@GetMapping("/findMinSalary/{deptId}")
	public Map<String, Object> findMinSalaryInDepartment(@Valid @PathVariable("deptId") Integer departmentId) {
		return departmentsService.findMinSalaryInDepartment(departmentId);
	}
	

}
