package com.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.domain.Department;
import com.model.DepartmentModel;
import com.repository.DepartmentRepository;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentRepository repository;

	public DepartmentModel getDepartmentById(final Long id) {
		Optional<Department> optEntity = repository.findById(id);
		if(!optEntity.isPresent())
			return null;
			
		return populateEntityToModel(optEntity.get());
	}

	@Transactional
	public DepartmentModel createOrUpdateDepartment(final DepartmentModel model) {
		Optional<Department> employee = repository.findById(model.getId());
		Department entity = null;
		if (employee.isPresent()) {
			entity = employee.get();
			entity.setName(model.getName());
			entity.setOrgId(model.getOrgId());

			final Department savedEntity = repository.save(entity);

			return populateEntityToModel(savedEntity);
		} else {
			entity = new Department();
			entity.setId(model.getId());
			entity.setName(model.getName());
			entity.setOrgId(model.getOrgId());
			final Department savedEntity = repository.save(entity);

			return populateEntityToModel(savedEntity);
		}
	}

	@Transactional
	public void deleteDepartmentById(final Long id) {
		Optional<Department> optEntity = repository.findById(id);
		if(optEntity.isPresent()) {
			repository.deleteById(id);
		}
	}

	private DepartmentModel populateEntityToModel(final Department entity) {
		DepartmentModel model = new DepartmentModel();
		model.setId(entity.getId());
		model.setName(entity.getName());
		model.setOrgId(entity.getOrgId());

		return model;
	}

}