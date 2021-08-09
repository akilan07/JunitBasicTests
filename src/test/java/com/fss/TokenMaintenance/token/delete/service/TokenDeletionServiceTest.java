package com.fss.TokenMaintenance.token.delete.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.fss.TokenMaintenance.config.JunitConfig;
import com.fss.TokenMaintenance.config.RoutingConfiguration;
import com.fss.TokenMaintenance.dao.TokenMaintenanceDAO;
import com.fss.TokenMaintenance.service.TokenMaintenanceService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RoutingConfiguration.class,
		JunitConfig.class }, loader = AnnotationConfigContextLoader.class)
public class TokenDeletionServiceTest {

	@Mock
	TokenMaintenanceDAO tokenMaintenanceDAO;

	@InjectMocks
	TokenMaintenanceService tokenMaintenanceService;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void tokenDeletionServicePosTest() {
		Mockito.when(tokenMaintenanceDAO.deleteToken("15")).thenReturn(true);
		assertTrue(tokenMaintenanceService.deleteToken("15"));
	}
	
	@Test
	public void tokenDeletionServiceNegTest() {
		Mockito.when(tokenMaintenanceDAO.deleteToken("500")).thenReturn(false);
		assertTrue(!tokenMaintenanceService.deleteToken("500"));
	}
}
