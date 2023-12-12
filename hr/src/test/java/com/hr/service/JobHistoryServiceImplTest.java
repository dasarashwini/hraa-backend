package com.hr.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hr.dao.JobHistoryRepository;
import com.hr.dto.DepartmentsPojo;
import com.hr.dto.EmployeesPojo;
import com.hr.dto.JobHistoryPojo;
import com.hr.dto.JobsPojo;
import com.hr.entity.JobHistoryEntity;

@SpringBootTest
public class JobHistoryServiceImplTest {
	
	@Mock
	JobHistoryRepository jobHistoryRepository;
	
	@InjectMocks
	JobHistoryServiceImpl jobHistoryServiceImpl;
	

  
//	@Test
//	public void testAddJobHistory() {
//		//Arrange
//		JobHistoryPojo newJobHistory = new JobHistoryPojo();
//		JobHistoryEntity expectedEntity = new JobHistoryEntity();
//		
//		//Mocking
//		when(jobHistoryRepository.findById(LocalDate.of(2015,11,13))).thenReturn(Optional.empty());
//		when(jobHistoryRepository.saveAndFlush(any())).thenReturn(expectedEntity);
//		
//		String result = jobHistoryServiceImpl.addJobHistory(newJobHistory);
//		
//		//Assert
//		assertEquals("Record added successfully", result);
//		
//		//Verify
//		verify(jobHistoryRepository, times(1)).findById(LocalDate.of(2015,11,13));
//		verify(jobHistoryRepository, times(1)).saveAndFlush(expectedEntity);
//	}	
	

}

