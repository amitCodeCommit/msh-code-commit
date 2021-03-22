package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.DetailRespModel;
import com.model.EmployeeModel;
import com.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeController {
	@Autowired
	EmployeeService service;

	@GetMapping("/{id}")
	public EmployeeModel getEmployeeById(@PathVariable("id") Long id) {
		final EmployeeModel model = service.getEmployeeById(id);

		return model;
	}

	@PostMapping("/save")
	public EmployeeModel createOrUpdateEmployee(@RequestBody EmployeeModel model) {
		final EmployeeModel respModel = service.createOrUpdateEmployee(model);
		
		return respModel;
	}

	@DeleteMapping("/{id}")
	public String deleteEmployeeById(@PathVariable("id") Long id) {
		service.deleteEmployeeById(id);
		
		return "DELETED SUCCESSFULLY";
	}
	
	@GetMapping("/detail/{id}")
	public DetailRespModel getDetailResponse(@PathVariable("id") Long id) {
		
		return service.getDetailResponse(id);
	}

}