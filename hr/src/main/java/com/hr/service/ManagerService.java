package com.hr.service;

import org.springframework.stereotype.Service;

import com.hr.dto.ManagerPojo;
import com.hr.entity.ManagerEntity;
import com.hr.exceptions.ResourceNotFoundException;

@Service
public interface ManagerService {
	public String addManager(ManagerPojo managerPojo);
	public String updateManager(ManagerPojo managerPojo);
	public String deleteManager(int managerId) throws ResourceNotFoundException;
	public ManagerEntity findByManagerId(int id);
}
