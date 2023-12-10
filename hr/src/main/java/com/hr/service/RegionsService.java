package com.hr.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hr.dto.RegionsPojo;
import com.hr.exceptions.ResourceAlreadyExistsException;


@Service
public interface RegionsService {
	
	String addRegion(RegionsPojo newRegion) throws ResourceAlreadyExistsException;
	String updateRegion(RegionsPojo newRegion) throws ResourceAlreadyExistsException;
	List<RegionsPojo> getAllRegions();
	RegionsPojo getRegionById(int regionId);
	String deleteRegionById(int regionId);	

}
