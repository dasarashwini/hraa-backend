package com.hr.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hr.dao.UserRepository;
import com.hr.dto.DepartmentsPojo;
import com.hr.dto.EmployeesPojo;
import com.hr.dto.JobsPojo;
import com.hr.dto.RolesPojo;
import com.hr.dto.UserPojo;
import com.hr.entity.EmployeesEntity;
import com.hr.entity.ManagerEntity;
import com.hr.entity.RolesEntity;
import com.hr.entity.UserEntity;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserPojo authenticateUser(UserPojo userPojo) {
		UserEntity fetchedUserEntity = userRepository.findByUserName(userPojo.getUserName());
		if(fetchedUserEntity!=null && fetchedUserEntity.getUserPassword().equals(userPojo.getUserPassword())) {
			// means a record has been fetched
			// also password has matched
			// now copy the collection of roles entity into collection of roles pojo
			List<RolesPojo> allRolesPojo = new ArrayList<>();
			for(RolesEntity eachRolesEntity: fetchedUserEntity.getAllRolesEntity()) {
				// This add method is working only because of AllArgsConstructor annotation
				allRolesPojo.add(new RolesPojo(eachRolesEntity.getRolesId(), eachRolesEntity.getRolesName()));		
			}
			userPojo.setAllRolesPojo(allRolesPojo);
			
		}else {
			// username/password does not exist
			// throw custom exception
			throw new RuntimeException("Invalid Username/password!");
		}
		return userPojo;
	}
}
