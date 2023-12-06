package com.hr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hr.dto.RegionsPojo;
import com.hr.service.RegionsService;

//@CrossOrigin
@RestController
@RequestMapping("/api/v1/region")
public class RegionsController {
	
	@Autowired
	RegionsService regionService;
	
	@PostMapping
	String addRegion(@RequestBody RegionsPojo newRegion) {
		return "";
	}
	
	@GetMapping
	List<RegionsPojo> getAllRegions(){
		List<RegionsPojo> allRegionsPojo = regionService.getAllRegions();
//		System.out.println(allRegionsPojo);
		return allRegionsPojo;
	}
	
	@GetMapping("/{id}")
	RegionsPojo getRegionById(@PathVariable("id") int regionId) {
		return null;
	}
	
	@DeleteMapping("/{id}")
	String deleteRegionById(@PathVariable("id") int regionId) {
		return "";
	}

}
