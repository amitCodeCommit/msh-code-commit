package com.service;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.domain.Employee;
import com.model.DeptRespModel;
import com.model.DetailRespModel;
import com.model.EmployeeModel;
import com.model.OrgRespModel;
import com.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository repository;

	public EmployeeModel getEmployeeById(final Long id) {
		Optional<Employee> optEntity = repository.findById(id);
		if (!optEntity.isPresent())
			return null;

		return populateEntityToModel(optEntity.get());
	}

	@Transactional
	public EmployeeModel createOrUpdateEmployee(final EmployeeModel model) {
		Optional<Employee> employee = repository.findById(model.getId());
		Employee entity = null;
		if (employee.isPresent()) {
			entity = employee.get();
			entity.setName(model.getName());
			entity.setDeptId(model.getDeptId());

			final Employee savedEntity = repository.save(entity);

			return populateEntityToModel(savedEntity);
		} else {
			entity = new Employee();
			entity.setId(model.getId());
			entity.setName(model.getName());
			entity.setDeptId(model.getDeptId());
			final Employee savedEntity = repository.save(entity);

			return populateEntityToModel(savedEntity);
		}
	}

	@Transactional
	public void deleteEmployeeById(final Long id) {
		Optional<Employee> optEntity = repository.findById(id);

		if (optEntity.isPresent()) {
			repository.deleteById(id);
		}
	}

	private EmployeeModel populateEntityToModel(final Employee entity) {
		EmployeeModel model = new EmployeeModel();
		model.setId(entity.getId());
		model.setName(entity.getName());
		model.setDeptId(entity.getDeptId());

		return model;
	}

	public DetailRespModel getDetailResponse(final Long id) {

		RestTemplate restTemplate = new RestTemplate();
		DetailRespModel detailRespModel = new DetailRespModel();

		final EmployeeModel empModel = getEmployeeById(id);

		if (!Objects.isNull(empModel)) {
			DeptRespModel deptResp = restTemplate
					.getForObject("http://localhost:8082/department/api/" + empModel.getDeptId(), DeptRespModel.class);

			OrgRespModel orgResp = restTemplate
					.getForObject("http://localhost:8083/organization/api/" + deptResp.getId(), OrgRespModel.class);

			detailRespModel.setEMPLOYEE_ID(empModel.getId());
			detailRespModel.setEMPLOYEE_NAME(empModel.getName());
			detailRespModel.setDEPARTMENT_NAME(deptResp.getName());
			detailRespModel.setORGANIZATION_ADDRESS(orgResp.getAddress());
		}

		return detailRespModel;
	}

}