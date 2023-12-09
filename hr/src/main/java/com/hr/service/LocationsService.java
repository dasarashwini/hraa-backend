package com.hr.service;

import java.util.List;

import com.hr.dto.LocationsPojo;
import com.hr.exceptions.ResourceAlreadyExistsException;
import com.hr.exceptions.ResourceNotFoundException;

public interface LocationsService {
	public String addLocations(LocationsPojo newLocations) throws ResourceAlreadyExistsException;
	String updateLocations(LocationsPojo newLocations);
	List<LocationsPojo> getAllLocations();
	LocationsPojo getLocationsById(int locationsId);
	String deleteLocationsById(int locationsId);	





}
