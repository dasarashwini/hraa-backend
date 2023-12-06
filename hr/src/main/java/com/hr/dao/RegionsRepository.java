package com.hr.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hr.entity.RegionsEntity;

@Repository
public interface RegionsRepository extends JpaRepository<RegionsEntity, Integer> {
		
}
