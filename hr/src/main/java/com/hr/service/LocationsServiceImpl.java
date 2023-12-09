package com.hr.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hr.dao.EmployeesRepository;
import com.hr.dao.LocationsRepository;
import com.hr.dto.CountriesPojo;
import com.hr.dto.LocationsPojo;
import com.hr.entity.CountriesEntity;
import com.hr.entity.EmployeesEntity;
import com.hr.entity.LocationsEntity;
import com.hr.entity.RegionsEntity;
import com.hr.exceptions.ResourceAlreadyExistsException;
import com.hr.exceptions.ResourceNotFoundException;

@Service
public class LocationsServiceImpl implements LocationsService {

	@Autowired
	LocationsRepository locationsRepository;
	
    List<LocationsPojo> locationsPojoList= new ArrayList<>();


	@Override
	public String addLocations(LocationsPojo newLocations) throws ResourceAlreadyExistsException {
		//copy
	Optional<LocationsEntity>locationEntity= locationsRepository.findById(newLocations.getLocationsId());
	if(locationEntity.isPresent()) {
		throw new ResourceAlreadyExistsException("Location with ID "+newLocations.getLocationsId()+" Already exists");
	}
	else {
	LocationsEntity locationsEntity = new LocationsEntity();
	BeanUtils.copyProperties(newLocations, locationsEntity);
	//copying the foreign key objects of countries table
	{
		CountriesEntity countriesEntity = new CountriesEntity();
		BeanUtils.copyProperties(newLocations.getCountriesPojo(), countriesEntity);
		locationsEntity.setCountriesEntity(countriesEntity);

	}
	
		locationsRepository.save(locationsEntity);
		String string = "Locations added successfully";
		return string ;
	}
	
	}
		
	@Override
	public String updateLocations(LocationsPojo newLocations) throws ResourceNotFoundException{
//		Optional<LocationsEntity>locationEntity= locationsRepository.findById(newLocations.getLocationsId());
//		if(locationEntity.isPresent()) {
//			LocationsEntity locationsEntity = new LocationsEntity();
//			BeanUtils.copyProperties(newLocations, locationsEntity);
//			locationsRepository.save(locationsEntity);
//			
//		}else {
//			throw new ResourceAlreadyExistsException("Location with ID "+newLocations.getLocationsId()+" Not exists");
//		}
//		return "Locations updated successfully";
		//copy
		Optional<LocationsEntity>locationEntity= locationsRepository.findById(newLocations.getLocationsId());
		if(locationEntity.isPresent()) {
			throw new ResourceAlreadyExistsException("Location with ID "+newLocations.getLocationsId()+" Already exists");
		}
		else {
		LocationsEntity locationsEntity = new LocationsEntity();
		BeanUtils.copyProperties(newLocations, locationsEntity);
		//copying the foreign key objects of countries table
		{
			CountriesEntity countriesEntity = new CountriesEntity();
			BeanUtils.copyProperties(newLocations.getCountriesPojo(), countriesEntity);
			locationsEntity.setCountriesEntity(countriesEntity);

		}
		
			locationsRepository.save(locationsEntity);
			String string = "Locations updated successfully";
			return string ;
		}
		
		

	}

//	@Override
//	public List<LocationsPojo> getAllLocations() throws ResourceNotFoundException {
//		List<LocationsEntity> allLocationsEntity = locationsRepository.findAll();
//		
//		List<LocationsPojo>allLocationsPojos = new ArrayList<LocationsPojo>();
//		for(LocationsEntity eachLocationsEntity : allLocationsEntity) {
//			LocationsPojo eacLocationsPojo = new LocationsPojo();
//			BeanUtils.copyProperties(eachLocationsEntity, eacLocationsPojo);
//			allLocationsPojos.add(eacLocationsPojo);
//		}
//		if(allLocationsPojos.isEmpty()) {
//			throw new ResourceNotFoundException("No Locations found");
//		}
//		
//		return allLocationsPojos;
//	}
	@Override
	public List<LocationsPojo> getAllLocations(){
		List<LocationsEntity> location = locationsRepository.findAll();
		List<LocationsPojo>allLocationsPojos = new ArrayList<LocationsPojo>();
    	BeanUtils.copyProperties(location, allLocationsPojos);
		CountriesPojo countriesPojo = new CountriesPojo();
		return null;
	}

	@Override
	public LocationsPojo getLocationsById(int locationsId) {
		LocationsEntity location = locationsRepository.getById(locationsId);
		LocationsPojo locationspojo = new LocationsPojo();
		BeanUtils.copyProperties(location, locationspojo);
		CountriesPojo countriesPojo = new CountriesPojo();
		BeanUtils.copyProperties(location.getCountriesEntity(), countriesPojo);
		locationspojo.setCountriesPojo(countriesPojo);
		return locationspojo;
	}


	@Override
	public String deleteLocationsById(int locationsId) {
		Optional<LocationsEntity> locationsEntity=locationsRepository.findById(locationsId);
		LocationsPojo locationsPojo = new LocationsPojo();
		BeanUtils.copyProperties(locationsEntity.get(), locationsPojo);
		if(locationsPojo!=null)
		locationsRepository.deleteById(locationsId);
		String string = "locations deleted successfully";
		return string;
	}

	

	
}
