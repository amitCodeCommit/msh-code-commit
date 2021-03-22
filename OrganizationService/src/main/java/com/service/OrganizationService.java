package com.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.domain.Organization;
import com.model.OrganizationModel;
import com.repository.OrganizationRepository;

@Service
public class OrganizationService {

	@Autowired
	private OrganizationRepository repository;

	public OrganizationModel getOrganizationById(final Long id) {
		Optional<Organization> optEntity = repository.findById(id);
		if(!optEntity.isPresent())
			return null;
			
		return populateEntityToModel(optEntity.get());
	}

	@Transactional
	public OrganizationModel createOrUpdateOrganization(final OrganizationModel model) {
		Optional<Organization> optEntity = repository.findById(model.getId());
		Organization entity = null;
		if (!optEntity.isPresent()) {
			entity = new Organization();
			entity.setId(model.getId());
			entity.setName(model.getName());
			entity.setAddress(model.getAddress());

			final Organization savedEntity = repository.save(entity);

			return populateEntityToModel(savedEntity);
		} else {
			entity = optEntity.get();
			entity.setName(model.getName());
			entity.setAddress(model.getAddress());
			final Organization savedEntity = repository.save(entity);

			return populateEntityToModel(savedEntity);
		}
	}

	@Transactional
	public void deleteOrganizationById(final Long id) {
		Optional<Organization> optEntity = repository.findById(id);

		if (optEntity.isPresent()) {
			repository.deleteById(id);
		}
	}

	private OrganizationModel populateEntityToModel(final Organization entity) {
		OrganizationModel model = new OrganizationModel();
		model.setId(entity.getId());
		model.setName(entity.getName());
		model.setAddress(entity.getAddress());
		return model;
	}

}