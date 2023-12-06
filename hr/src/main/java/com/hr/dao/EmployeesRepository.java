package com.hr.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hr.entity.EmployeesEntity;

@Repository
public interface EmployeesRepository extends JpaRepository<EmployeesEntity, Integer> {

}
