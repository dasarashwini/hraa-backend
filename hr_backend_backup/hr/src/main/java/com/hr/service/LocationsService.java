package com.hr.service;

import java.util.List;

import com.hr.dto.LocationsPojo;
import com.hr.exceptions.ResourceAlreadyExistsException;
import com.hr.exceptions.ResourceNotFoundException;

public interface LocationsService {
	public String addLocations(LocationsPojo newLocations)throws ResourceAlreadyExistsException;
	String updateLocations(LocationsPojo newLocations)throws ResourceAlreadyExistsException;
	List<LocationsPojo> getAllLocations()throws ResourceNotFoundException;
	LocationsPojo getLocationsById(int locationsId)throws ResourceNotFoundException;
	public String deleteLocationsById(int locationsId)throws ResourceNotFoundException;	





}
