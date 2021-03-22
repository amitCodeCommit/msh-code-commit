package com.service.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.domain.Department;
import com.model.DepartmentModel;
import com.repository.DepartmentRepository;
import com.service.DepartmentService;

@RunWith(SpringJUnit4ClassRunner.class)
public class DepartmentServiceTest {

	@Mock
	private DepartmentRepository employeeRepository;

	@InjectMocks
	DepartmentService empService = new DepartmentService();

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@After
	public void tearDown() {
	}

	@Test
	public void testGetDepartmentById() {
		Department entity = new Department();
		entity.setId(Long.valueOf("1"));
		entity.setOrgId(Long.valueOf("1"));
		entity.setName("IT");
		
		Optional<Department> optDept = Optional.of(entity);
		
		when(employeeRepository.findById(any())).thenReturn(optDept);

		DepartmentModel respModel = empService.getDepartmentById(1L);

		assertNotNull(respModel);

	}

	@Test
	public void testGetNullDepartmentById() {
		
		Optional<Department> optDept = Optional.empty();

		when(employeeRepository.findById(any())).thenReturn(optDept);

		DepartmentModel respModel = empService.getDepartmentById(0L);

		assertNull(respModel);
	}

	@Test
	public void testAddOrUpdate() {
		Department entity = new Department();
		entity.setId(Long.valueOf("1"));
		entity.setOrgId(Long.valueOf("1"));
		entity.setName("IT");
		
		Optional<Department> optDept = Optional.of(entity);
		when(employeeRepository.findById(any())).thenReturn(optDept);

		when(employeeRepository.save(any())).thenReturn(entity);

		DepartmentModel respModel = empService.createOrUpdateDepartment(populateDummyModel());

		assertNotNull(respModel);
	}

	@Test
	public void TestDeleteDepartmentById() {
		Department entity = new Department();
		entity.setId(Long.valueOf("1"));
		entity.setOrgId(Long.valueOf("1"));
		entity.setName("IT");

		Optional<Department> optDept = Optional.of(entity);
		when(employeeRepository.findById(any())).thenReturn(optDept);

		doNothing().when(employeeRepository).delete(entity);

		empService.deleteDepartmentById(1L);
	}

	private DepartmentModel populateDummyModel() {
		DepartmentModel model = new DepartmentModel();
		model.setOrgId(Long.valueOf("1"));
		model.setId(Long.valueOf("1"));
		model.setName("IT");

		return model;
	}

}
