package com.hr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hr.dto.ManagerPojo;
import com.hr.entity.ManagerEntity;
import com.hr.service.ManagerService;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/employees")
public class ManagerController {

	@Autowired
	ManagerService managerService;
	
	@PostMapping("/manager/add")
	public ResponseEntity<String> addManager(@RequestBody ManagerPojo managerPojo) {
		return ResponseEntity.ok(managerService.addManager(managerPojo));
	}
	
	@PutMapping("/manager/update")
	public ResponseEntity<String> updateManager(@RequestBody ManagerPojo managerPojo) {
		return ResponseEntity.ok(managerService.updateManager(managerPojo));
	}
	
	@DeleteMapping("/manager/{managerId}")
	public ResponseEntity<String> deleteManager(@PathVariable int managerId) {
		return ResponseEntity.ok(managerService.deleteManager(managerId));
	}
	
	@GetMapping("manager/{id}")
	public ResponseEntity<ManagerEntity> findByManagerId(@PathVariable int id) {
		return ResponseEntity.ok(managerService.findByManagerId(id));
	}
}
