package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.DepartmentModel;
import com.service.DepartmentService;

@RestController
@RequestMapping("/api")
public class DepartmentController {
	@Autowired
	DepartmentService service;

	@GetMapping("/{id}")
	public DepartmentModel getDepartmentById(@PathVariable("id") Long id) {
		final DepartmentModel model = service.getDepartmentById(id);

		return model;
	}

	@PostMapping("/save")
	public DepartmentModel createOrUpdateDepartment(@RequestBody DepartmentModel model) {
		final DepartmentModel respModel = service.createOrUpdateDepartment(model);
		
		return respModel;
	}

	@DeleteMapping("/{id}")
	public String deleteDepartmentById(@PathVariable("id") Long id) {
		service.deleteDepartmentById(id);
		
		return "DELETED SUCCESSFULLY";
	}

}