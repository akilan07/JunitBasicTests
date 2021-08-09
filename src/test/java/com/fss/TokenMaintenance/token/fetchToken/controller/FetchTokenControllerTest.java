package com.fss.TokenMaintenance.token.fetchToken.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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
import com.fss.TokenMaintenance.controller.TokenMaintenanceController;
import com.fss.TokenMaintenance.service.TokenMaintenanceService;
import com.fss.TokenMaintenance.token.Token;
import com.fss.TokenMaintenance.validationService.TokenMaintenanceValidator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RoutingConfiguration.class, JunitConfig.class},loader=AnnotationConfigContextLoader.class)
public class FetchTokenControllerTest {

	@InjectMocks
	TokenMaintenanceController tokenMaintenanceController;

	@Mock
	TokenMaintenanceService tokenMaintenanceService;

	@Mock
	TokenMaintenanceValidator tokenMaintenanceValidator;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testFetchTokenContollerPos() {
		String tokenId = "16";
		Token token = new Token();
		token.setExpryDate("2506");
		token.setTokenId(tokenId);
		token.setToken("5264081235465987");
		token.setTokenStatus("Create");
		Mockito.when(tokenMaintenanceService.getTokensById(tokenId)).thenReturn(token);
		assertEquals(tokenMaintenanceController.getTokensById(tokenId).getTokenId(),tokenId);
	}
	
	@Test
	public void testFetchTokenControllerNeg() {
		String tokenId = "16";
		Token token = new Token();
		token.setExpryDate("2506");
		token.setTokenId("0");
		token.setToken("5264081235465987");
		token.setTokenStatus("Create");

		Mockito.when(tokenMaintenanceService.getTokensById(tokenId)).thenReturn(token);
		
		assertNotEquals(tokenMaintenanceController.getTokensById(tokenId).getTokenId(),tokenId);
	}
	
}
