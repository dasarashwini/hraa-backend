package com.hr.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hr.dao.RegionsRepository;
import com.hr.dto.RegionsPojo;
import com.hr.entity.RegionsEntity;

@Service
public class RegionsServiceImpl implements RegionsService{
	
	@Autowired
	RegionsRepository regionsRepository;

	@Override
	public String addRegion(RegionsPojo newRegion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateRegion(RegionsPojo newRegion) {
		// TODO Auto-generated method stub
		return null;
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
//		System.out.println(allRegionsEntity);
		return allRegionsPojo;
	}

	@Override
	public RegionsPojo getRegionById(int regionId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteRegionById(int regionId) {
		// TODO Auto-generated method stub
		return null;
	}

}
