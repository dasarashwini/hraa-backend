//package com.hr.service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.hr.dao.ManagerRepository;
//import com.hr.dto.LocationsPojo;
//import com.hr.dto.ManagerPojo;
//import com.hr.entity.CountriesEntity;
//import com.hr.entity.LocationsEntity;
//import com.hr.entity.ManagerEntity;
//import com.hr.exceptions.ResourceAlreadyExistsException;
//
//@Service
//public class ManagerServiceImpl implements ManagerService {
//	
//	@Autowired
//	ManagerRepository managerRepository;
//	
//	List<ManagerPojo> managerPojoList = new ArrayList<>();
//	
//	public String addManager(ManagerPojo newManager) throws ResourceAlreadyExistsException {
//	    Optional<ManagerEntity> managerEntity = managerRepository.findById(newManager.getManagerId());
//	    
//	    if (managerEntity.isPresent()) {
//	        throw new ResourceAlreadyExistsException("manager with ID " + newManager.getManagerId() + " Already exists");
//	    } else {
//	        ManagerEntity managerEntity = new ManagerEntity();
//	        BeanUtils.copyProperties(newManager, managerEntity);
//
//	        // copying the foreign key objects of countries table
//	        {
//	            CountriesEntity countriesEntity = new CountriesEntity();
//	            BeanUtils.copyProperties(newLocations.getCountriesPojo(), countriesEntity);
//	            locationsEntity.setCountriesEntity(countriesEntity);
//	        }
//
//	        locationsRepository.save(locationsEntity);
//	        String string = "Locations added successfully";
//	        return string;
//	    }
//	}
//
//
//	
//
//}
