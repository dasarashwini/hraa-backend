package com.hr.dto;

import lombok.Data;

@Data

public class LocationsPojo {
	private int locationsId;
	private String streetAddress;
	private String postalCode;
	private String city;
	private String stateProvince;
	private CountriesPojo countriesPojo;
	
}
