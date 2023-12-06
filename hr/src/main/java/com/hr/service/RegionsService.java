package com.hr.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hr.dto.RegionsPojo;


@Service
public interface RegionsService {
	
	String addRegion(RegionsPojo newRegion);
	String updateRegion(RegionsPojo newRegion);
	List<RegionsPojo> getAllRegions();
	RegionsPojo getRegionById(int regionId);
	String deleteRegionById(int regionId);	

}
