package com.hr.dao;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hr.entity.EmployeesEntity;

@Repository
public interface EmployeesRepository extends JpaRepository<EmployeesEntity, Integer> {
       public EmployeesEntity findByFirstName(String firstName);
       public EmployeesEntity findByEmail(String email);
       public EmployeesEntity findByphoneNumber(String phoneNumber);
       public  List<EmployeesEntity> findByHireDateBetween(Date fromHireDate,Date toHireDate);
       public List<EmployeesEntity> findByDepartmentsEntityDepartmentId(int departmentId);
       
       @Query
       ("SELECT e FROM EmployeesEntity e WHERE e.commissionPct IS NULL OR e.commissionPct = 0")
       public List<EmployeesEntity> findAllEmployeeWithNoCommission();
       
       @Query
       ("SELECT e FROM EmployeesEntity e WHERE e.departmentsEntity.departmentId = :departmentId")
       public List<EmployeesEntity> findAllEmployeesByDepartmentId(@Param("departmentId") int departmentId);
       
       @Query
       ("SELECT d.departmentName, COUNT(e) FROM EmployeesEntity e JOIN e.departmentsEntity d GROUP BY d.departmentName")
       public List<Object[]> countEmployeesByDepartment();
       
       @Query
       ("select je.jobTitle, max(e.salary) from EmployeesEntity e join e.jobsEntity je where e.employeeId = :employeeId group by je.jobTitle")
       public List<Object[]> findMaxSalaryOfJobByEmployeeId(@Param("employeeId") int employeeId);
       
       @Query
       ("SELECT COALESCE(SUM(e.commissionPct), 0) FROM EmployeesEntity e WHERE e.departmentsEntity.departmentId = :departmentId")
       public double findTotalCommissionIssuedToEmployeeByDepartment(@Param("departmentId") int departmentId);
         
       @Query
       ("SELECT d.locationsEntity.locationsId, COUNT(e.employeeId) FROM EmployeesEntity e JOIN e.departmentsEntity d GROUP BY d.locationsEntity.locationsId")
       public List<Object[]> countEmployeesByLocation();

       @Query
       ("SELECT DISTINCT j.jobId, j.jobTitle FROM JobsEntity j, EmployeesEntity e WHERE j.jobId != e.jobsEntity.jobId")
        public List<Object[]> getAllOpenPositionswhichwasnotfilled();
        
       @Query
       ("SELECT DISTINCT e.jobsEntity.jobTitle FROM EmployeesEntity e WHERE e.departmentsEntity.departmentId = :departmentId AND e.managerId IS NULL")
       public List<Object[]> getAllOpenPositionsJobsInDepartment(int departmentId);
}
  