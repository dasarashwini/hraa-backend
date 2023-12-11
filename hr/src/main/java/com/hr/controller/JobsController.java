package com.hr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hr.dto.JobsPojo;
import com.hr.entity.JobsEntity;
import com.hr.service.JobsService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/v1/jobs")
public class JobsController {
	@Autowired
	   JobsService jobsService;
	@PostMapping("/add")
	public ResponseEntity<String> addnewJob(@Valid @RequestBody JobsPojo jobsPojo){
		String job = jobsService.addnewJob(jobsPojo);
		return new ResponseEntity<String>(job,HttpStatus.OK);
	}
	@PutMapping()
	public ResponseEntity<String> updateJobsEntity(@Valid @RequestBody JobsPojo jobsPojo) {
		String job = jobsService.updateJobsEntity(jobsPojo);
		return new ResponseEntity<String>(job,HttpStatus.OK);
	}
	@GetMapping()
	public List<JobsPojo> getJobDetails(){
		List<JobsPojo>jobPojoList= jobsService.getJobDetails();
		return jobPojoList;
	}
	@DeleteMapping("/{jobId}")	
	public ResponseEntity<String> deleteJobsById(@Valid @PathVariable("jobId") String jobId ) {
		String job = jobsService.deleteJobsById(jobId);
		return new ResponseEntity<String>(job,HttpStatus.OK);
		
	}
//	@PutMapping()
//	public JobsPojo Updatesalary(String jobId,double minSalary, double maxSalary) {
//		return jobdetails.Updatesalary(jobId, minSalary, maxSalary);
//		
//	}
	@GetMapping("{jobId}")
	public JobsPojo findByJobId(@Valid @PathVariable("jobId") String jobId) {
		return jobsService.findByJobId(jobId);
		
	}
	


}
	

