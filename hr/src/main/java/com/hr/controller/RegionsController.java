package com.hr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hr.dto.RegionsPojo;
import com.hr.service.RegionsService;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/region")
public class RegionsController {
	@Autowired
	RegionsService regionService;
	
	@PostMapping
	ResponseEntity<String> addRegion(@RequestBody RegionsPojo newRegion) {
		String msg = regionService.addRegion(newRegion);
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	
	@PutMapping
	ResponseEntity <String> updateRegion(@RequestBody RegionsPojo newRegion) {
		String msg = regionService.updateRegion(newRegion);
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	
	@GetMapping
	ResponseEntity<List<RegionsPojo>>  getAllRegions(){
		List<RegionsPojo> allRegionsPojo = regionService.getAllRegions();
//		System.out.println(allRegionsPojo);
		return new ResponseEntity<List<RegionsPojo>>(allRegionsPojo,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	RegionsPojo getRegionById(@PathVariable("id") int regionId) {
		return regionService.getRegionById(regionId);
	}
	
	@DeleteMapping("/{id}")
	ResponseEntity<String> deleteRegionById(@PathVariable("id") int regionId) {
		String msge =regionService.deleteRegionById(regionId);
		return new ResponseEntity<String>(msge,HttpStatus.OK);
	}

}
