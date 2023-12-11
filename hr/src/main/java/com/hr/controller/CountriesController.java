package com.hr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hr.dto.CountriesPojo;
import com.hr.service.CountryService;

@RestController
@RequestMapping("/api/v1/country")
public class CountriesController {
	
	@Autowired
	CountryService countryService;
	
	@PostMapping
	ResponseEntity<String>addCountry(@RequestBody CountriesPojo newCountry) {
		String msge= countryService.addCountry(newCountry);
		return new ResponseEntity<String>(msge,HttpStatus.OK);
	}
	
	@PutMapping()
	ResponseEntity<String> update(@RequestBody CountriesPojo updateCountry) {
		String msge = countryService.update(updateCountry);
		return new ResponseEntity<String>(msge,HttpStatus.OK);	
	}
	
	
	@GetMapping
	public List<CountriesPojo> getAllCountries(){
		return countryService.getAllCountries();
	}
	
	@GetMapping("/{id}")
	public CountriesPojo getCountryById(@PathVariable("id") String countryId) {
		return countryService.getCountryById(countryId);
	}
	
	@DeleteMapping("/{id}")
	public String deleteCountryById(@PathVariable("id") String countryId) {
		return countryService.deleteCountryById(countryId);
	}
}
