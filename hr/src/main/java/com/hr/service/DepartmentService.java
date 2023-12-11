package com.hr.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hr.dto.DepartmentsPojo;
import com.hr.exceptions.ResourceAlreadyExistsException;
import com.hr.exceptions.ResourceNotFoundException;


@Service
public interface DepartmentService {
	public String addnewDepartment(DepartmentsPojo departmentsPojo)throws ResourceAlreadyExistsException;
	public String updatedepartmentEntity(DepartmentsPojo departmentsPojo) throws ResourceNotFoundException;
	public String deletedepartmentById(int departmentId);
	public List<DepartmentsPojo> getallDepartmentsByEmployees(int empId);
	public Map<String, Object> findMaxSalaryInDepartment(Integer departmentId);
	public Map<String, Object> findMinSalaryInDepartment(Integer departmentId);
}
