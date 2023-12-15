package com.hr.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hr.dao.ManagerRepositoty;
import com.hr.dto.ManagerPojo;
import com.hr.entity.DepartmentsEntity;
import com.hr.entity.ManagerEntity;
import com.hr.exceptions.ResourceNotFoundException;

@Service
public class ManagerServiceImpl implements ManagerService {

	@Autowired
	ManagerRepositoty managerRepositoty;

	@Override
	public String addManager(ManagerPojo managerPojo) {
		ManagerEntity managerEntity = new ManagerEntity();
		BeanUtils.copyProperties(managerPojo, managerEntity);
		DepartmentsEntity departmentsEntity = new DepartmentsEntity();
		BeanUtils.copyProperties(managerPojo.getDepartmentsPojo(), departmentsEntity);
		managerEntity.setDepartmentsEntity(departmentsEntity);
		managerRepositoty.save(managerEntity);
		return " Manager Added Successfully ";
	}

	@Override
	public String updateManager(ManagerPojo managerPojo) {
		ManagerEntity managerEntity = new ManagerEntity();
		BeanUtils.copyProperties(managerPojo, managerEntity);
		DepartmentsEntity departmentsEntity = new DepartmentsEntity();
		BeanUtils.copyProperties(managerPojo.getDepartmentsPojo(), departmentsEntity);
		managerEntity.setDepartmentsEntity(departmentsEntity);
		managerRepositoty.save(managerEntity);
		return "Manager Modified Successfully ";
	}

	@Override
	public String deleteManager(int managerId) throws ResourceNotFoundException {
		ManagerEntity managerIdEntity = managerRepositoty.findByManagerId(managerId);
		if(managerIdEntity != null) {
		managerRepositoty.deleteById(managerId);
		}
		else {
			throw new ResourceNotFoundException(" Manager ID "+managerId+" is not Found");
		}
	return "Manager Deleted Successfully";
	}

	@Override
	public ManagerEntity findByManagerId(int id) {
		return managerRepositoty.findByManagerId(id);
	}
	
	

}
