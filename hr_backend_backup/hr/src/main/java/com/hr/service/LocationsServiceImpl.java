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
import com.hr.dto.RegionsPojo;
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

	List<LocationsPojo> locationsPojoList = new ArrayList<>();

	public String addLocations(LocationsPojo newLocations) throws ResourceAlreadyExistsException {
	    Optional<LocationsEntity> locationEntity = locationsRepository.findById(newLocations.getLocationsId());
	    
	    if (locationEntity.isPresent()) {
	        throw new ResourceAlreadyExistsException("Location with ID " + newLocations.getLocationsId() + " Already exists");
	    } else {
	        LocationsEntity locationsEntity = new LocationsEntity();
	        BeanUtils.copyProperties(newLocations, locationsEntity);

	        // copying the foreign key objects of countries table
	        {
	            CountriesEntity countriesEntity = new CountriesEntity();
	            BeanUtils.copyProperties(newLocations.getCountriesPojo(), countriesEntity);
	            locationsEntity.setCountriesEntity(countriesEntity);
	        }

	        locationsRepository.save(locationsEntity);
	        String string = "Locations added successfully";
	        return string;
	    }
	}


	@Override
	public String updateLocations(LocationsPojo newLocations) throws ResourceAlreadyExistsException {
		Optional<LocationsEntity> locationEntity = locationsRepository.findById(newLocations.getLocationsId());
		if (locationEntity.isPresent()) {
			throw new ResourceAlreadyExistsException(
					"Location with ID " + newLocations.getLocationsId() + " Already exists");
		} else {
			LocationsEntity locationsEntity = new LocationsEntity();
			BeanUtils.copyProperties(newLocations, locationsEntity);
			// copying the foreign key objects of countries table
			{
				CountriesEntity countriesEntity = new CountriesEntity();
				BeanUtils.copyProperties(newLocations.getCountriesPojo(), countriesEntity);
				locationsEntity.setCountriesEntity(countriesEntity);

			}

			locationsRepository.save(locationsEntity);
			String string = "Locations updated successfully";
			return string;
		}
	}

	@Override
	public List<LocationsPojo> getAllLocations() throws ResourceNotFoundException{
		List<LocationsEntity> location = locationsRepository.findAll();
		if (location.isEmpty()) {
			throw new ResourceNotFoundException("locations is not avilable");
		} else {
			List<LocationsPojo> allLocationsPojos = new ArrayList<LocationsPojo>();
			for (LocationsEntity eachLocationsEntity : location) {
				LocationsPojo eachLocationsPojo = new LocationsPojo();
				BeanUtils.copyProperties(eachLocationsEntity, eachLocationsPojo);

				CountriesPojo countriesPojo = new CountriesPojo();
				BeanUtils.copyProperties(eachLocationsEntity.getCountriesEntity(), countriesPojo);
				eachLocationsPojo.setCountriesPojo(countriesPojo);

				RegionsPojo regionsPojo = new RegionsPojo();
				BeanUtils.copyProperties(eachLocationsEntity.getCountriesEntity().getRegionsEntity(), regionsPojo);
				countriesPojo.setRegionsPojo(regionsPojo);
				allLocationsPojos.add(eachLocationsPojo);
			}
			return allLocationsPojos;
		}
	}

	@Override
	public LocationsPojo getLocationsById(int locationsId) throws ResourceNotFoundException {
		Optional<LocationsEntity> location = locationsRepository.findById(locationsId);
		if (location.isPresent()) {
			LocationsPojo locationspojo = new LocationsPojo();
			BeanUtils.copyProperties(location.get(), locationspojo);
			CountriesPojo countriesPojo = new CountriesPojo();
			BeanUtils.copyProperties(location.get().getCountriesEntity(), countriesPojo);
			locationspojo.setCountriesPojo(countriesPojo);
			return locationspojo;
		} else {
			throw new ResourceNotFoundException("Location with id " + locationsId + " not exists");
		}
	}

	@Override
	public String deleteLocationsById(int locationsId) throws ResourceNotFoundException {
		Optional<LocationsEntity> locationsEntity = locationsRepository.findById(locationsId);
		if (locationsEntity.isEmpty()) {
			throw new ResourceNotFoundException("Location  not available");
		} else {
			LocationsPojo locationsPojo = new LocationsPojo();
			BeanUtils.copyProperties(locationsEntity.get(), locationsPojo);
			if (locationsPojo != null)
				locationsRepository.deleteById(locationsId);
		}
		String string = "locations deleted successfully";
		return string;
	}

}
