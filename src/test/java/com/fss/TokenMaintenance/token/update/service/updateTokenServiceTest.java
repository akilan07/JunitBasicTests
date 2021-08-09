package com.fss.TokenMaintenance.token.update.service;

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
import com.fss.TokenMaintenance.token.Token;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RoutingConfiguration.class,
		JunitConfig.class }, loader = AnnotationConfigContextLoader.class)
public class updateTokenServiceTest {

	@Mock
	TokenMaintenanceDAO tokenMaintenanceDAO;

	@InjectMocks
	TokenMaintenanceService tokenMaintenanceService;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void updateTokenServicePosTest() {
		Token token = new Token();
		token.setExpryDate("2506");
		token.setTokenId("15");
		token.setToken("5452154544561244");
		token.setTokenStatus("Update");
		Mockito.when(tokenMaintenanceDAO.updateToken(token)).thenReturn(true);
		assertTrue(tokenMaintenanceService.updateToken(token));
	}
	
	@Test
	public void updateTokenServiceNegTest() {
		Token token = new Token();
		token.setExpryDate("2506");
		token.setTokenId("15");
		token.setToken("5452154544561244");
		token.setTokenStatus("Update");
		Mockito.when(tokenMaintenanceDAO.updateToken(token)).thenReturn(false);
		assertTrue(!tokenMaintenanceService.updateToken(token));
	}
	
}
