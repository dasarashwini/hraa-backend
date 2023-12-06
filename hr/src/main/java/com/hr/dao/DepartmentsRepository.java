package com.hr.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hr.entity.DepartmentsEntity;

@Repository
public interface DepartmentsRepository extends JpaRepository<DepartmentsEntity, Integer> {

}
