package com.fss.TokenMaintenance.token.update.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
@ContextConfiguration(classes = { RoutingConfiguration.class,
		JunitConfig.class }, loader = AnnotationConfigContextLoader.class)
public class UpdateTokenControllerTest {

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
	public void testUpdateControllerPos() {
		Token token = new Token();
		token.setTokenId("120");
		Mockito.when(tokenMaintenanceValidator.tokenUpdateValidator(token)).thenReturn(true);
		Mockito.when(tokenMaintenanceService.updateToken(token)).thenReturn(true);
		assertEquals(tokenMaintenanceController.updateToken(token).getResCode(), 0);
	}
	
	@Test
	public void testUpdateControllerNeg() {
		Token token = new Token();
		token.setTokenId("120");
		Mockito.when(tokenMaintenanceValidator.tokenUpdateValidator(token)).thenReturn(true);
		Mockito.when(tokenMaintenanceService.updateToken(token)).thenReturn(false);
		assertEquals(tokenMaintenanceController.updateToken(token).getResCode(), 1);
	}
	
	@Test
	public void testUpdateControllerNegVald() {
		Token token = new Token();
		token.setTokenId("120");
		Mockito.when(tokenMaintenanceValidator.tokenUpdateValidator(token)).thenReturn(false);
		assertEquals(tokenMaintenanceController.updateToken(token).getResCode(), 1);
	}
	
}
