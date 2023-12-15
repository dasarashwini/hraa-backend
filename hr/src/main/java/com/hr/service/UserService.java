package com.hr.service;
import org.springframework.stereotype.Service;

import com.hr.dto.UserPojo;

@Service
public interface UserService {
	public UserPojo authenticateUser(UserPojo userPojo);
}
