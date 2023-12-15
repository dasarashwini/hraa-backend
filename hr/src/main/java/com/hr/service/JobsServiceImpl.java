package com.hr.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hr.dao.JobsRepository;
import com.hr.dto.JobsPojo;
import com.hr.entity.JobsEntity;
import com.hr.exceptions.ResourceAlreadyExistsException;
import com.hr.exceptions.ResourceNotFoundException;

@Service
public class JobsServiceImpl implements JobsService {
	@Autowired
	JobsRepository jobsRepository;
	
	List<JobsEntity> jobsentityList = new ArrayList<>();
	
	@Override
	public String addnewJob(JobsPojo jobsPojo) throws ResourceAlreadyExistsException{
		Optional<JobsEntity>jobEntity=jobsRepository.findById(jobsPojo.getJobTitle());
		if(jobEntity.isPresent()) {
			throw new ResourceAlreadyExistsException("Jobs with Title "+jobsPojo.getJobTitle()+"already Exists");
		}
		else {
			JobsEntity jobsEntity = new JobsEntity();
			BeanUtils.copyProperties(jobsPojo, jobsEntity);
			jobsRepository.save(jobsEntity);
		}
		return "Record Created Successfully";
	}

	@Override
	public String updateJobsEntity(JobsPojo jobsPojo)throws ResourceNotFoundException {
		Optional<JobsEntity>jobEntity=jobsRepository.findById(jobsPojo.getJobId());
		if(jobEntity.isPresent()) {
		JobsEntity jobsEntity=new JobsEntity();
		BeanUtils.copyProperties(jobsPojo, jobsEntity);
		jobsRepository.save(jobsEntity);
		}else {
			throw new ResourceNotFoundException("Jobs with Title "+jobsPojo.getJobTitle()+" Not Exists");
			
			
		}
		return "Record Updated";
		
	}
	@Override
	public List<JobsPojo> getJobDetails() {
		List<JobsEntity> jobEntityList = jobsRepository.findAll();
		for(JobsEntity j:jobEntityList)
			System.out.println(j);
		List<JobsPojo> jobPojoList = new ArrayList<JobsPojo>();
		for(JobsEntity eachJobsEntity: jobEntityList) {
			JobsPojo eachJobsPojo = new JobsPojo();
			BeanUtils.copyProperties(eachJobsEntity,eachJobsPojo);
			jobPojoList.add(eachJobsPojo);
			
		}
//		System.out.println(jobPojoList);
		return jobPojoList;
	}

	@Override
	public String deleteJobsById(String jobId) {
		Optional<JobsEntity> jobsEntity = jobsRepository.findById(jobId);
		JobsPojo jobsPojo = new JobsPojo();
		BeanUtils.copyProperties(jobsEntity.get(), jobsPojo);
		if(jobsPojo!=null)
			jobsRepository.deleteById(jobId);
		return "Record Deleted Successfully";
	}

	@Override
	public JobsPojo Updatesalary(String jobId,double minSalary, double maxSalary)  {
		Optional<JobsEntity> jobEntity =jobsRepository.findById(jobId);
		if(jobEntity.isPresent()) {
			JobsPojo jobsPojo = new JobsPojo();
			JobsEntity jobsEntity = jobEntity.get();
			jobsEntity.setMinSalary(minSalary);
			jobsEntity.setMaxSalary(maxSalary);
			jobsRepository.save(jobsEntity);
			BeanUtils.copyProperties(jobsEntity, jobsPojo);
			return jobsPojo;
		}
		return null;
	}

	@Override
	public JobsPojo findByJobId(String jobId) {
		JobsEntity jobdsE = jobsRepository.findByJobId(jobId);
		JobsPojo jobsPojo = new JobsPojo();
		BeanUtils.copyProperties(jobdsE, jobsPojo);
		return jobsPojo ;
	}



}


