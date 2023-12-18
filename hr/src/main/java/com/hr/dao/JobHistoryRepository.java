	package com.hr.dao;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hr.entity.JobHistoryEntity;

@Repository
public interface JobHistoryRepository extends JpaRepository<JobHistoryEntity,JobHistoryEntity.JobHistoryId> {
	
    List<JobHistoryEntity> findByEmployeesEntity_EmployeeId(int employeeId);
	
    @Query("SELECT jh FROM JobHistoryEntity jh WHERE jh.employeesEntity.employeeId = :empId")
    List<JobHistoryEntity> findByEmployeeId();

	boolean existsByEmployeesEntityEmployeeId(int employeeId);
    
   

	
	
}
