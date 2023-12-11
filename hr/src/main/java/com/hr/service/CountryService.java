package com.hr.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hr.dto.CountriesPojo;
import com.hr.dto.RegionsPojo;

@Service
public interface CountryService {
	
	String addCountry(CountriesPojo newCountry);
	String update(CountriesPojo updateCountry);
	List<CountriesPojo> getAllCountries();
	CountriesPojo getCountryById(String countryId);
	String deleteCountryById(String countryId);	

}