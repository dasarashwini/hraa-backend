package com.hr.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hr.entity.JobsEntity;

@Repository
public interface JobsRepository extends JpaRepository<JobsEntity, String> {
	public JobsEntity findByJobId(String jobId);

}
