package com.hr.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "regions", schema = "hr")
public class RegionsEntity {
	
	@Id
	@Column(name="region_id")
	private Integer regionId;
	
	@Column(name="region_name")
	private String regionName;
}
