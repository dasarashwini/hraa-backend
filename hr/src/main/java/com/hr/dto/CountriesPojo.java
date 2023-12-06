package com.hr.dto;

import lombok.Data;

@Data
public class CountriesPojo {
	private String countryId;
	private String countryName;
	private RegionsPojo regionsPojo;
}
