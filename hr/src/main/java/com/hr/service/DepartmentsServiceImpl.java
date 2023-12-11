package com.hr.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hr.dao.DepartmentsRepository;
import com.hr.dao.EmployeesRepository;
import com.hr.dto.CountriesPojo;
import com.hr.dto.DepartmentsPojo;
import com.hr.dto.LocationsPojo;
import com.hr.dto.RegionsPojo;
import com.hr.entity.CountriesEntity;
import com.hr.entity.DepartmentsEntity;
import com.hr.entity.LocationsEntity;
import com.hr.entity.RegionsEntity;
import com.hr.exceptions.ResourceAlreadyExistsException;
import com.hr.exceptions.ResourceNotFoundException;

@Service
public class DepartmentsServiceImpl implements DepartmentService {
	@Autowired
	DepartmentsRepository departmentsRepository;
	
	@Autowired
	EmployeesRepository employeesRepository;

	@Override
	public String addnewDepartment(DepartmentsPojo departmentsPojo)throws ResourceAlreadyExistsException {
		Optional<DepartmentsEntity>departmentEntity=departmentsRepository.findById(departmentsPojo.getDepartmentId());
		if(departmentEntity.isPresent()) {
			throw new ResourceAlreadyExistsException("department with Name "+departmentsPojo.getDepartmentName()+"already Exists");
		}
		else {
		
		DepartmentsEntity departmentsEntity = new DepartmentsEntity();
		BeanUtils.copyProperties(departmentsPojo, departmentsEntity);
		{
			LocationsEntity locationsEntity = new LocationsEntity();
			BeanUtils.copyProperties(departmentsPojo.getLocationsPojo(), locationsEntity);
			departmentsEntity.setLocationsEntity(locationsEntity);
		}
		departmentsRepository.save(departmentsEntity);
		return "Record Created Successfully";
		}
	}
	@Override
	public String updatedepartmentEntity(DepartmentsPojo departmentsPojo)throws ResourceNotFoundException {
		Optional<DepartmentsEntity>departmentEntity=departmentsRepository.findById(departmentsPojo.getDepartmentId());
		if(departmentEntity.isPresent()) {
		DepartmentsEntity departmentsEntity = new DepartmentsEntity();
		BeanUtils.copyProperties(departmentsPojo, departmentsEntity);
		{
			LocationsEntity locationsEntity = new LocationsEntity();
			BeanUtils.copyProperties(departmentsPojo.getLocationsPojo(), locationsEntity);
			departmentsEntity.setLocationsEntity(locationsEntity);
		}
		departmentsRepository.save(departmentsEntity);
		}else {
			throw new ResourceNotFoundException("department with Name "+departmentsPojo.getDepartmentName()+" Not Exists");
			
		}
		return "Record Modified Successfully";
	}


	@Override
	public String deletedepartmentById(int departmentId) {
		Optional<DepartmentsEntity>departmentsEntity=departmentsRepository.findById(departmentId);
		if(departmentsEntity.isPresent()) {
			departmentsRepository.deleteById(departmentId);;
		return "Record deleted succssfully";
		}
		return null;
	}

	@Override
	public List<DepartmentsPojo> getallDepartmentsByEmployees(int empId) {
		List<DepartmentsEntity> allDepartmentsEntity = departmentsRepository.findAll();
		List<DepartmentsPojo> departmentPojoList=new ArrayList<DepartmentsPojo>();
		for(DepartmentsEntity eachDepartmentsEntity:allDepartmentsEntity ) {
			DepartmentsPojo eachDepartmentPojo = new DepartmentsPojo();
			BeanUtils.copyProperties(eachDepartmentsEntity, eachDepartmentPojo);
				LocationsPojo locationPojo = new LocationsPojo();
				BeanUtils.copyProperties(eachDepartmentsEntity.getLocationsEntity(), locationPojo);
					CountriesPojo countriesPojo = new CountriesPojo();
					BeanUtils.copyProperties(eachDepartmentsEntity.getLocationsEntity().getCountriesEntity(),countriesPojo);
						RegionsPojo regionsPojo = new RegionsPojo();
						BeanUtils.copyProperties(eachDepartmentsEntity.getLocationsEntity().getCountriesEntity().getRegionsEntity(),regionsPojo);
						countriesPojo.setRegionsPojo(regionsPojo);
					locationPojo.setCountriesPojo(countriesPojo);
				eachDepartmentPojo.setLocationsPojo(locationPojo);
			departmentPojoList.add(eachDepartmentPojo);
		}
		return departmentPojoList;
		
	}

	public Map<String, Object> findMaxSalaryInDepartment(Integer departmentId) {
        return departmentsRepository.findMaxSalaryInDepartment(departmentId);
    }
	
	public Map<String, Object> findMinSalaryInDepartment(Integer departmentId) {
        return departmentsRepository.findMaxSalaryInDepartment(departmentId);
    }
	


}
