package com.hr.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hr.entity.LocationsEntity;

public interface ManagerRepository extends JpaRepository<LocationsEntity, Integer>{

}
