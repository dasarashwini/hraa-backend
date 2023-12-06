package com.hr.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
//import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name="job_history", schema = "hr")
@IdClass(JobHistoryEntity.JobHistoryId.class)
public class JobHistoryEntity {
	
	@Id
	@ManyToOne
	@JoinColumn(name = "employee_id")
	private EmployeesEntity employeesEntity;
	
	@Id
	@Column(name="start_date")
	@Temporal(TemporalType.DATE)
	private Date startDate;
	
	@Column(name="end_date")
	@Temporal(TemporalType.DATE)
	private Date endDate;
	
	@ManyToOne
	@JoinColumn(name="job_id")
	private JobsEntity jobsEntity;
	
	@ManyToOne
	@JoinColumn(name="department_id")
	private DepartmentsEntity departmentsEntity;
	
	public static class JobHistoryId implements Serializable {

        @ManyToOne
        @JoinColumn(name = "employee_id")
        private EmployeesEntity employeesEntity;

        @Column(name = "start_date")
        private Date startDate;
        
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            JobHistoryId that = (JobHistoryId) o;
            return Objects.equals(employeesEntity, that.employeesEntity) &&
                    Objects.equals(startDate, that.startDate);
        }

        @Override
        public int hashCode() {
            return Objects.hash(employeesEntity, startDate);
        }
        
	}
}
