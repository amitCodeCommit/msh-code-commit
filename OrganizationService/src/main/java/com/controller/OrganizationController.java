package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.OrganizationModel;
import com.service.OrganizationService;

@RestController
@RequestMapping("/api")
public class OrganizationController {

	@Autowired
	OrganizationService service;

	@GetMapping("/{id}")
	public OrganizationModel getOrganizationById(@PathVariable("id") Long id) {
		final OrganizationModel model = service.getOrganizationById(id);

		return model;
	}

	@PostMapping("/save")
	public OrganizationModel createOrUpdateOrganization(@RequestBody OrganizationModel model) {
		final OrganizationModel respModel = service.createOrUpdateOrganization(model);

		return respModel;
	}

	@DeleteMapping("/{id}")
	public String deleteOrganizationById(@PathVariable("id") Long id) {
		service.deleteOrganizationById(id);

		return "DELETED SUCCESSFULLY";
	}

}