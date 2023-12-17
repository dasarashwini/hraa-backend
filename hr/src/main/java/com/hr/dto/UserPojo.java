package com.hr.dto;

import java.util.List;
import lombok.Data;

@Data
public class UserPojo {
	int userId;
	String userName;
	String userPassword;
	List<RolesPojo> allRolesPojo;
}
