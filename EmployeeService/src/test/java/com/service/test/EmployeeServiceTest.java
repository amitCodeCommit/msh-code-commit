package com.service.test;

import static org.assertj.core.api.Assertions.assertThat;
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
import org.springframework.web.client.RestTemplate;

import com.domain.Employee;
import com.model.DeptRespModel;
import com.model.DetailRespModel;
import com.model.EmployeeModel;
import com.model.OrgRespModel;
import com.repository.EmployeeRepository;
import com.service.EmployeeService;

@RunWith(SpringJUnit4ClassRunner.class)
public class EmployeeServiceTest {

	@Mock
	private EmployeeRepository employeeRepository;

	@InjectMocks
	EmployeeService empService = new EmployeeService();

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@After
	public void tearDown() {
	}

	@Test
	public void testGetEmployeeById() {
		Employee entity = new Employee();
		entity.setId(Long.valueOf("1"));
		entity.setDeptId(Long.valueOf("1"));
		entity.setName("Amit");
		
		Optional<Employee> optEmp = Optional.of(entity);

		when(employeeRepository.findById(any())).thenReturn(optEmp);

		EmployeeModel respModel = empService.getEmployeeById(1L);

		assertNotNull(respModel);

	}

	@Test
	public void testGetNullEmployeeById() {

		Optional<Employee> emptyOrg = Optional.empty();

		when(employeeRepository.findById(any())).thenReturn(emptyOrg);
		EmployeeModel respModel = empService.getEmployeeById(0L);

		assertNull(respModel);
	}

	@Test
	public void testAddOrUpdate() {
		Employee entity = new Employee();
		entity.setId(Long.valueOf("1"));
		entity.setDeptId(Long.valueOf("1"));
		entity.setName("Amit");
		
		Optional<Employee> optOrg = Optional.of(entity);

		when(employeeRepository.findById(any())).thenReturn(optOrg);

		when(employeeRepository.save(any())).thenReturn(entity);

		EmployeeModel respModel = empService.createOrUpdateEmployee(populateDummyModel());

		assertNotNull(respModel);
	}

	@Test
	public void TestDeleteEmployeeById() {
		Employee entity = new Employee();
		entity.setId(Long.valueOf("1"));
		entity.setDeptId(Long.valueOf("1"));
		entity.setName("Amit");

		Optional<Employee> optOrg = Optional.of(entity);

		when(employeeRepository.findById(any())).thenReturn(optOrg);

		doNothing().when(employeeRepository).delete(entity);

		empService.deleteEmployeeById(1L);
	}
	
	@Test
	public void testGetDetailResponse() {
		RestTemplate restTemplate = new RestTemplate();
		Employee entity = new Employee();
		entity.setId(Long.valueOf("1"));
		entity.setDeptId(Long.valueOf("1"));
		entity.setName("Amit");

		Optional<Employee> optOrg = Optional.of(entity);

		when(employeeRepository.findById(any())).thenReturn(optOrg);
		
		DeptRespModel deptResp = restTemplate
				.getForObject("http://localhost:8082/department/api/" + 1, DeptRespModel.class);
		assertNotNull(deptResp);

		OrgRespModel orgResp = restTemplate
				.getForObject("http://localhost:8083/organization/api/" + 1, OrgRespModel.class);
		assertNotNull(orgResp);
		
		DetailRespModel detail = empService.getDetailResponse(1L);
		assertThat(detail);
	
	}

	private EmployeeModel populateDummyModel() {
		EmployeeModel model = new EmployeeModel();
		model.setDeptId(Long.valueOf("1"));
		model.setId(Long.valueOf("1"));
		model.setName("Amit");

		return model;
	}

}
