package com.hr.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hr.controller.JobHistoryController;
import com.hr.dao.JobHistoryRepository;
import com.hr.dto.DepartmentsPojo;
import com.hr.dto.EmployeesPojo;
import com.hr.dto.JobHistoryPojo;
import com.hr.dto.JobsPojo;
import com.hr.entity.JobHistoryEntity;
import com.hr.exceptions.ResourceAlreadyExistsException;
import com.hr.exceptions.ResourceNotFoundException;

@SpringBootTest
public class JobHistoryServiceImplTest {
	
	@Mock
	JobHistoryRepository jobHistoryRepository;
	
	@InjectMocks
	JobHistoryServiceImpl jobHistoryServiceImpl;
	
	 @Test
	    public void testAddJobHistoryException() {
	        // Arrange
	        JobHistoryPojo newJobHistory = new JobHistoryPojo();
	        Mockito.when(jobHistoryRepository.save(Mockito.any(JobHistoryEntity.class))).thenThrow(new RuntimeException("Mocked exception"));

	        // Act & Assert
	        assertThrows(ResourceAlreadyExistsException.class, () -> jobHistoryServiceImpl.addJobHistory(newJobHistory));
	    }
	 
	 
	@Test
	public void testFindExperienceOfEmployee() {
		int employeeId = 5;
		List<JobHistoryEntity> jobHistories = new ArrayList<>();
		
		when(jobHistoryRepository.findByEmployeesEntity_EmployeeId(employeeId)).thenReturn(jobHistories);
		
		LocalDate currentDate = LocalDate.of(2014, 11, 13);
		
		Map<String, Integer> result = jobHistoryServiceImpl.findExperienceOfEmployee(employeeId);
		
		assertEquals(0, result.get("years"));
		assertEquals(0, result.get("months"));
		assertEquals(0, result.get("days"));
		
		
	}
	
	@Test
	public void testListAllEmployeesWithLessThanOneYearExperience() {
		int employeeId = 5;
		List<JobHistoryEntity> jobHistoryList = new ArrayList<>();
		
		when(jobHistoryRepository.findByEmployeesEntity_EmployeeId(employeeId)).thenReturn(jobHistoryList);
		
		LocalDate currentDate = LocalDate.of(2014, 11, 13);
		
		//when(jobHistoryServiceImpl.getCurrentDate()).thenReturn(currentDate);
		
		Map<String, Integer> result = jobHistoryServiceImpl.listAllEmployeesWithLessThanOneYearExperience(employeeId);
		
		assertEquals(0, result.get("years"));
		assertEquals(0, result.get("months"));
		assertEquals(0, result.get("days"));
		
		
	}
	
	@Test
	public void testFindExperienceOfEmployeeException() {
		int employeeId = 5;
		List<JobHistoryEntity> jobHistories = new ArrayList<>();
		
		when(jobHistoryRepository.findByEmployeesEntity_EmployeeId(employeeId)).thenReturn(jobHistories);
		
		LocalDate currentDate = LocalDate.of(2014, 11, 13);
		
		Map<String, Integer> result = jobHistoryServiceImpl.findExperienceOfEmployee(employeeId);
		
		assertEquals(0, result.get("years"));
		assertEquals(0, result.get("months"));
		assertEquals(0, result.get("days"));
		
		assertThrows(NullPointerException.class, ()-> {
			when(jobHistoryRepository.findByEmployeesEntity_EmployeeId(employeeId)).thenReturn(null);
			jobHistoryServiceImpl.findExperienceOfEmployee(employeeId);
		});
		
		
	}
	
	@Test
	public void testListAllEmployeesWithLessThanOneYearExperienceException() {
		int employeeId = 5;
		List<JobHistoryEntity> jobHistoryList = new ArrayList<>();
		
		when(jobHistoryRepository.findByEmployeesEntity_EmployeeId(employeeId)).thenReturn(jobHistoryList);
		
		LocalDate currentDate = LocalDate.of(2014, 11, 13);
		
		//when(jobHistoryServiceImpl.getCurrentDate()).thenReturn(currentDate);
		
		Map<String, Integer> result = jobHistoryServiceImpl.listAllEmployeesWithLessThanOneYearExperience(employeeId);
		
		assertEquals(0, result.get("years"));
		assertEquals(0, result.get("months"));
		assertEquals(0, result.get("days"));
		
		assertThrows(ResourceNotFoundException.class, ()-> {
			when(jobHistoryRepository.findByEmployeesEntity_EmployeeId(employeeId)).thenThrow(ResourceNotFoundException.class);
			jobHistoryServiceImpl.listAllEmployeesWithLessThanOneYearExperience(employeeId);
		});
		
		
	}
	
	 
}
	

	


