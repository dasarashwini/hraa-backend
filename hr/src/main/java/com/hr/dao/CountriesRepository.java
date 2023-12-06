package com.hr.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hr.entity.CountriesEntity;

@Repository
public interface CountriesRepository extends JpaRepository<CountriesEntity, String> {

}
