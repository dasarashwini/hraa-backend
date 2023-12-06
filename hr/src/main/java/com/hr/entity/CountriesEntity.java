package com.hr.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "countries", schema = "hr")
public class CountriesEntity {
	
	@Id
	@Column(name="country_id",length = 2)
	private char countryId;
	
	@Column(name="country_name")
	private String countryName;
	
	@ManyToOne
	@JoinColumn(name = "region_id")
	private RegionsEntity regionsEntity;
}
