package com.fss.TokenMaintenance.token.fetchToken.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.annotation.Report;
import org.easetech.easytest.loader.LoaderType;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContextManager;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.fss.TokenMaintenance.config.JunitConfig;
import com.fss.TokenMaintenance.config.RoutingConfiguration;
import com.fss.TokenMaintenance.controller.TokenMaintenanceController;
import com.fss.TokenMaintenance.service.TokenMaintenanceService;
import com.fss.TokenMaintenance.token.Token;
import com.fss.TokenMaintenance.validationService.TokenMaintenanceValidator;

@RunWith(DataDrivenTestRunner.class)
@Report(outputLocation = "file:target")
@ContextConfiguration(classes = { RoutingConfiguration.class,
		JunitConfig.class }, loader = AnnotationConfigContextLoader.class)
@DataLoader(filePaths = { "TestFetchToken.xls" }, loaderType = LoaderType.EXCEL)
public class DDFetchTokenController {

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
	
	@Before
	public void datadrivenclass() throws Exception {
		new TestContextManager(getClass()).prepareTestInstance(this);
	}
	
	@Test
	public void testFetchTokenContollerPos(@Param(name="TokenId") String tokenId, @Param(name="ResTokenId") String resTokenId) {
		Token token = new Token();
		token.setTokenId(tokenId);
		Mockito.when(tokenMaintenanceService.getTokensById(tokenId)).thenReturn(token);
		assertEquals(tokenMaintenanceController.getTokensById(tokenId).getTokenId(),tokenId);
	}
	
	@Test
	public void testFetchTokenControllerNeg(@Param(name="TokenId") String tokenId, @Param(name="ResTokenId") String resTokenId) {
		Token token = new Token();
		token.setTokenId("0");

		Mockito.when(tokenMaintenanceService.getTokensById(tokenId)).thenReturn(token);
		
		assertEquals(tokenMaintenanceController.getTokensById(tokenId).getTokenId(),resTokenId);
	}
	
}
