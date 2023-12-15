package com.hr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hr.dto.LocationsPojo;
import com.hr.service.LocationsService;

import jakarta.validation.Valid;

@Validated
@RestController
@RequestMapping("/api/v1/location")
public class LocationsController {
	
	@Autowired
	LocationsService locationsService;
	
	@PostMapping("/add")
	ResponseEntity<String> addLocations(@RequestBody @Valid LocationsPojo newLocations) {
		String msg=locationsService.addLocations(newLocations);
		return new ResponseEntity<String>(msg,HttpStatus.CREATED);
	}
	
	@PutMapping
	ResponseEntity<String> updateLocations(@Valid @RequestBody LocationsPojo newLocations) {
		String upmsg=locationsService.updateLocations(newLocations);
		return new ResponseEntity<String>(upmsg,HttpStatus.ACCEPTED);
	}
	
	@GetMapping
	List<LocationsPojo> getAllLocations(){
		List<LocationsPojo> allLocationsPojos=locationsService.getAllLocations();
		System.out.println(allLocationsPojos);
		return allLocationsPojos;
	}
	
	@GetMapping("/{id}")
	LocationsPojo getLocationsById(@PathVariable("id") int locationsId) {
		return locationsService.getLocationsById(locationsId);
		
	}
	
	@DeleteMapping("/{id}")
	String deleteLocationsById(@PathVariable("id") int locationsId) {
		return locationsService.deleteLocationsById(locationsId);
	}

}
