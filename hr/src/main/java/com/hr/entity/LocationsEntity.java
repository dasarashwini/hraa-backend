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
@Table(name="locations", schema = "hr")
public class LocationsEntity {
	
	@Id
	@Column(name="location_id")
	private Integer locationsId;
	
	@Column(name="street_address")
	private String streetAddress;
	
	@Column(name="postal_code")
	private String postalCode;
	
	@Column(name="city")
	private String city;
	
	@Column(name="state_province")
	private String stateProvince;
	
	@ManyToOne
	@JoinColumn(name="country_id")
	private CountriesEntity countriesEntity;	
	
}
