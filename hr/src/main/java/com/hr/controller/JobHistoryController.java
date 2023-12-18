package com.hr.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hr.dto.JobHistoryPojo;
import com.hr.dto.LocationsPojo;
import com.hr.service.JobHistoryService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/v1/job_history")
@CrossOrigin()
public class JobHistoryController {
	
	   @Autowired
	   JobHistoryService jobHistoryService;
		
		@PostMapping
        public ResponseEntity<String> addJobHistory(@Valid @RequestBody JobHistoryPojo newJobHistory) {
			String msg = jobHistoryService.addJobHistory(newJobHistory);
			return new ResponseEntity<>(msg,HttpStatus.OK);
		}
		
		
		@PutMapping
		public ResponseEntity<String> modifyJobHistory(@Valid @RequestBody JobHistoryPojo newJobHistory){
			String msg = jobHistoryService.modifyJobHistory(newJobHistory);
			return new ResponseEntity<>(msg,HttpStatus.OK);
		}
		
		@GetMapping("/totalyearsofexperience/{empId}")
		public ResponseEntity<Map<String, Integer>> findExperienceOfEmployee(@PathVariable("empId") int employeeId){
			Map<String, Integer> result = jobHistoryService.findExperienceOfEmployee(employeeId);
			return new ResponseEntity<>(result,HttpStatus.OK);
		}
		
		@GetMapping("lessthanoneyearexperience/{empId}")
		public ResponseEntity< Map<String, Integer>> listAllEmployeesWithLessThanOneYearExperience(@PathVariable("empId") int employeeId){
			Map<String, Integer> result = jobHistoryService.listAllEmployeesWithLessThanOneYearExperience(employeeId);
			return new ResponseEntity<>(result,HttpStatus.OK);
		}
		

		@GetMapping("/all")
		public List<JobHistoryPojo> getAllJobHistories(){
			List<JobHistoryPojo> allJobHistoryPojos=jobHistoryService.getAllJobHistories();
			return allJobHistoryPojos;
		}
		
		
		
		}		