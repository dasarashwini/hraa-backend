package com.hr.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CountriesPojo {
	private String countryId;
	private String countryName;
	private RegionsPojo regionsPojo;
}
