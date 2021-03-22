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

import com.domain.Organization;
import com.model.OrganizationModel;
import com.repository.OrganizationRepository;
import com.service.OrganizationService;

@RunWith(SpringJUnit4ClassRunner.class)
public class OrganizationServiceTest {

	@Mock
	private OrganizationRepository orgRepository;

	@InjectMocks
	OrganizationService orgService = new OrganizationService();

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@After
	public void tearDown() {
	}

	@Test
	public void testGetEmployeeById() {
		Organization entity = new Organization();
		entity.setId(Long.valueOf("1"));
		entity.setName("STS Cons");
		entity.setAddress("Kolkata");

		Optional<Organization> optOrg = Optional.of(entity);

		when(orgRepository.findById(any())).thenReturn(optOrg);

		OrganizationModel respModel = orgService.getOrganizationById(1L);

		assertNotNull(respModel);

	}

	@Test
	public void testGetNullEmployeeById() {
		
		Optional<Organization> emptyOrg = Optional.empty();

		when(orgRepository.findById(any())).thenReturn(emptyOrg);

		OrganizationModel respModel = orgService.getOrganizationById(0L);

		assertNull(respModel);
	}

	@Test
	public void testAddOrUpdate() {
		Organization entity = new Organization();
		entity.setId(Long.valueOf("1"));
		entity.setName("STS Cons");
		entity.setAddress("Kolkata");

		Optional<Organization> optOrg = Optional.of(entity);

		when(orgRepository.findById(any())).thenReturn(optOrg);

		when(orgRepository.save(any())).thenReturn(entity);

		OrganizationModel respModel = orgService.createOrUpdateOrganization(populateDummyModel());

		assertNotNull(respModel);
	}

	@Test
	public void TestDeleteEmployeeById() {
		Organization entity = new Organization();
		entity.setId(Long.valueOf("1"));
		entity.setName("STS Cons");
		entity.setAddress("Kolkata");

		Optional<Organization> optOrg = Optional.of(entity);

		when(orgRepository.findById(any())).thenReturn(optOrg);

		doNothing().when(orgRepository).delete(entity);

		orgService.deleteOrganizationById(1L);
	}

	private OrganizationModel populateDummyModel() {
		OrganizationModel model = new OrganizationModel();
		model.setAddress("Kolkata");
		model.setId(Long.valueOf("1"));
		model.setName("STS Cons");

		return model;
	}

}
