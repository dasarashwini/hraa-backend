package com.hr.dao;

import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hr.entity.DepartmentsEntity;

@Repository
public interface DepartmentsRepository extends JpaRepository<DepartmentsEntity, Integer> {
	
	
	
	 @Query("SELECT d.departmentName AS departmentName, MAX(e.salary) AS maxSalary " +
	            "FROM EmployeesEntity e " +
	            "JOIN e.departmentsEntity d " +
	            "WHERE d.departmentId = :departmentId " +
	            "GROUP BY d.departmentId, d.departmentName")
	    Map<String, Object> findMaxSalaryInDepartment(@Param("departmentId") Integer departmentId);
	 
	 @Query("SELECT d.departmentName AS departmentName, MIN(e.salary) AS minSalary " +
	            "FROM EmployeesEntity e " +
	            "JOIN e.departmentsEntity d " +
	            "WHERE d.departmentId = :departmentId " +
	            "GROUP BY d.departmentId, d.departmentName")
	    Map<String, Object> findMinSalaryInDepartment(@Param("departmentId") Integer departmentId);

}
