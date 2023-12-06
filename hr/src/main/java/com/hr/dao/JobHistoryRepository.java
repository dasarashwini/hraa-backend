package com.hr.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hr.entity.EmployeesEntity;
import com.hr.entity.JobHistoryEntity;

@Repository
public interface JobHistoryRepository extends JpaRepository<JobHistoryEntity,JobHistoryEntity.JobHistoryId> {

}
