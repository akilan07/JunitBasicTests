package com.fss.TokenMaintenance.token.fetchToken.service;

import static org.junit.Assert.assertEquals;

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
public class FetchTokenByIdServiceTest {

	@Mock
	TokenMaintenanceDAO tokenMaintenanceDAO;

	@InjectMocks
	TokenMaintenanceService tokenMaintenanceService;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void fetchTokenServicePosTest() {
		Token token = new Token();
		token.setExpryDate("2506");
		token.setTokenId("15");
		token.setToken("5452154544561244");
		token.setTokenStatus("Create");
		Mockito.when(tokenMaintenanceDAO.getTokensById("15")).thenReturn(token);
		assertEquals(tokenMaintenanceService.getTokensById("15").getToken(),"5452154544561244");
	}
	
	@Test
	public void fetchTokenServiceNegTest() {
		Token token = new Token();
		token.setTokenId("500");
		Mockito.when(tokenMaintenanceDAO.getTokensById("500")).thenReturn(token);
		assertEquals(tokenMaintenanceService.getTokensById("500").getToken(),null);
	}
	
}
