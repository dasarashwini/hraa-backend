package com.hr.service;

import java.util.List;

import org.apache.el.stream.Optional;
import org.springframework.stereotype.Service;

import com.hr.dto.JobsPojo;
import com.hr.exceptions.ResourceAlreadyExistsException;
import com.hr.exceptions.ResourceNotFoundException;

@Service
public interface JobsService {
	public String addnewJob(JobsPojo jobspojos)throws ResourceAlreadyExistsException;
	public String updateJobsEntity(JobsPojo jobsPojo)throws ResourceNotFoundException;
	public List<JobsPojo> getJobDetails();
	public String deleteJobsById(String jobId );
	public JobsPojo findByJobId(String jobId);
	public JobsPojo Updatesalary(String jobId,double minSalary, double maxSalary);
}

	



