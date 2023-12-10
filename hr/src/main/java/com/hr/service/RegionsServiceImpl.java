package com.hr.service;

import java.beans.Beans;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hr.dao.RegionsRepository;
import com.hr.dto.RegionsPojo;
import com.hr.entity.RegionsEntity;
import com.hr.exceptions.ResourceAlreadyExistsException;
import com.hr.exceptions.ResourceNotFoundException;

@Service
public class RegionsServiceImpl implements RegionsService{
	
	@Autowired
	RegionsRepository regionsRepository;

	@Override
	public String addRegion(RegionsPojo newRegion) throws ResourceAlreadyExistsException {
		Optional<RegionsEntity> regionEntity = regionsRepository.findById(newRegion.getRegionId());
		if(regionEntity.isPresent()) {
			throw new ResourceAlreadyExistsException("Region with name "+newRegion.getRegionName()+" already exists");
		}
		else {
		RegionsEntity regionsEntity = new RegionsEntity();
		BeanUtils.copyProperties(newRegion, regionsEntity);
		regionsRepository.save(regionsEntity);
		return "Region Added Successfully";
		}
	}

	@Override
	public String updateRegion(RegionsPojo newRegion) throws ResourceNotFoundException {
		Optional<RegionsEntity> regionEntity = regionsRepository.findById(newRegion.getRegionId());
		if(regionEntity.isPresent()) {
			RegionsEntity regionsEntity = new RegionsEntity();
			BeanUtils.copyProperties(newRegion, regionsEntity);
			regionsRepository.save(regionsEntity);
		}
		else {
		throw new ResourceNotFoundException("Region with name "+newRegion.getRegionName()+" not exits");
		}
		return "Region Updated SuccessFully";
	}

	@Override
	public List<RegionsPojo> getAllRegions() {
		List<RegionsEntity> allRegionsEntity = regionsRepository.findAll();
		
		List<RegionsPojo> allRegionsPojo = new ArrayList<RegionsPojo>();
		for(RegionsEntity eachRegionEntity : allRegionsEntity) {
			RegionsPojo eachRegionPojo = new RegionsPojo();
			BeanUtils.copyProperties(eachRegionEntity, eachRegionPojo);
			allRegionsPojo.add(eachRegionPojo);
		}
		return allRegionsPojo;
	}

	@Override
	public RegionsPojo getRegionById(int regionId) {
	   Optional<RegionsEntity> regionsEntity = regionsRepository.findById(regionId);
	   RegionsPojo regionsPojo = new RegionsPojo();
	   BeanUtils.copyProperties(regionsEntity.get(), regionsPojo);
		return regionsPojo;
	}

	@Override
	public String deleteRegionById(int regionId) {
		Optional<RegionsEntity> regionsEntity = regionsRepository.findById(regionId);
		RegionsPojo regionsPojo = new RegionsPojo();
		BeanUtils.copyProperties(regionsEntity.get(), regionsPojo);
		if(regionsPojo!=null)
		 regionsRepository.deleteById(regionId);
		return "Region Deleted Successfully";
	}

}
