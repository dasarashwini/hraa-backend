package com.hr.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hr.entity.ManagerEntity;

@Repository
public interface ManagerRepositoty extends JpaRepository<ManagerEntity, Integer> {
          public ManagerEntity findByManagerId(int id);
}