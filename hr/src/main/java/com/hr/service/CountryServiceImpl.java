package com.hr.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hr.dao.CountriesRepository;
import com.hr.dto.CountriesPojo;
import com.hr.dto.RegionsPojo;
import com.hr.entity.CountriesEntity;
import com.hr.entity.RegionsEntity;
import com.hr.exceptions.ResourceAlreadyExistsException;
import com.hr.exceptions.ResourceNotFoundException;

@Service
public class CountryServiceImpl implements CountryService {
	
	@Autowired
	CountriesRepository countriesRepository;

	@Override
	public String addCountry(CountriesPojo newCountry)throws ResourceAlreadyExistsException {
		Optional<CountriesEntity> countryEntity = countriesRepository.findById(newCountry.getCountryId());
		if(countryEntity.isPresent()) {
			throw new ResourceAlreadyExistsException("Country with name"+newCountry.getCountryName()+"ALready Exists");
		}else {
		CountriesEntity countriesEntity = new CountriesEntity();
		BeanUtils.copyProperties(newCountry, countriesEntity);
		{
			RegionsEntity regionsEntity = new RegionsEntity();
			BeanUtils.copyProperties(newCountry.getRegionsPojo(), regionsEntity);
			countriesEntity.setRegionsEntity(regionsEntity);
		}
		
		countriesRepository.save(countriesEntity);
		return "Record Added Successfully";
		}
	}

	@Override
	public String update(CountriesPojo updateCountry) throws ResourceNotFoundException {
		Optional<CountriesEntity> countryEntity = countriesRepository.findById(updateCountry.getCountryId());
		if(countryEntity.isPresent()) {
			CountriesEntity countriesEntity = new CountriesEntity();
			BeanUtils.copyProperties(updateCountry, countriesEntity);
			{
				RegionsEntity regionsEntity = new RegionsEntity();
				BeanUtils.copyProperties(updateCountry.getRegionsPojo(), regionsEntity);
				countriesEntity.setRegionsEntity(regionsEntity);
			}
			countriesRepository.save(countriesEntity);
		}
		else {
			throw new ResourceNotFoundException("Country with name "+updateCountry.getCountryName()+" Not Exists");	
		}
		return "Record Added Successfully";
	}

	@Override
	public List<CountriesPojo> getAllCountries() {
		List<CountriesEntity> allCountryEntity = countriesRepository.findAll();
		List<CountriesPojo> allCountryPojo = new ArrayList<CountriesPojo>();
		for(CountriesEntity eachCountryEntity : allCountryEntity) {
			CountriesPojo eachCountryPojo = new CountriesPojo();
			BeanUtils.copyProperties(eachCountryEntity, eachCountryPojo);
			
			RegionsPojo regionsPojo = new RegionsPojo();
			BeanUtils.copyProperties(eachCountryEntity.getRegionsEntity(), regionsPojo);
			eachCountryPojo.setRegionsPojo(regionsPojo);
			
			allCountryPojo.add(eachCountryPojo);
			
		}
		return allCountryPojo;
	}

	@Override
	public CountriesPojo getCountryById(String countryId) {
		Optional<CountriesEntity> countriesEntity = countriesRepository.findById(countryId);
		CountriesEntity countryEntity = countriesEntity.get();
		if(countryEntity!=null) {
		CountriesPojo countriesPojo = new CountriesPojo();
		BeanUtils.copyProperties(countriesEntity.get(), countriesPojo);
		RegionsEntity regionsEntity = countryEntity.getRegionsEntity();
		RegionsPojo regionsPojo = new RegionsPojo();
		BeanUtils.copyProperties(regionsEntity, regionsPojo);
		countriesPojo.setRegionsPojo(regionsPojo);
		return countriesPojo;
		}
		return null;
	}

	@Override
	public String deleteCountryById(String countryId) {
		Optional<CountriesEntity> countriesEntity = countriesRepository.findById(countryId);
		if(countriesEntity.isPresent()) {
				countriesRepository.deleteById(countryId);
				return "Record Deleted Successfully";
		}
		return null;
	}

}
