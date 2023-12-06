package com.hr.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hr.entity.LocationsEntity;

@Repository
public interface LocationsRepository extends JpaRepository<LocationsEntity, Integer> {

}
