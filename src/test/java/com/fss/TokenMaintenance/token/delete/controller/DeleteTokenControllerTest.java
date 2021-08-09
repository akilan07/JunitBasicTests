package com.fss.TokenMaintenance.token.delete.controller;

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
import com.fss.TokenMaintenance.validationService.TokenMaintenanceValidator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RoutingConfiguration.class,
		JunitConfig.class }, loader = AnnotationConfigContextLoader.class)
public class DeleteTokenControllerTest {

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
	public void testDeletionPos() {
		Mockito.when(tokenMaintenanceValidator.tokenDeleteValidator("16")).thenReturn(true);
		Mockito.when(tokenMaintenanceService.deleteToken("16")).thenReturn(true);
		assertEquals(tokenMaintenanceController.deleteToken("16").getResCode(), 0);
	
	}
	
	@Test
	public void testDeletionNegVald() {
		Mockito.when(tokenMaintenanceValidator.tokenDeleteValidator(null)).thenReturn(false);
		assertEquals(tokenMaintenanceController.deleteToken("16").getResCode(), 1);
	}
	
	@Test
	public void testDeletionNeg() {
		Mockito.when(tokenMaintenanceValidator.tokenDeleteValidator("16")).thenReturn(true);
		Mockito.when(tokenMaintenanceService.deleteToken("16")).thenReturn(false);
		assertEquals(tokenMaintenanceController.deleteToken("16").getResCode(), 1);
	}
	
}
