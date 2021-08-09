package com.fss.TokenMaintenance.token.create.Controller;

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
@DataLoader(filePaths = { "TestTokenCreation.xls" }, loaderType = LoaderType.EXCEL)
public class DDTokenCreationController {

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
	public void testTokenCreationControllerPos(@Param(name="Token") String tokenNo, @Param(name="ExpryDt") String expryDt, @Param(name="TokenStatus") String tokenStatus, @Param(name="TokenId") String tokenId) {
		Token token = new Token();
		token.setExpryDate(expryDt);
		token.setTokenStatus(tokenStatus);
		token.setToken(tokenNo);
		Mockito.when(tokenMaintenanceValidator.tokenCreationValidator(token)).thenReturn(true);
		Mockito.when(tokenMaintenanceService.createToken(token)).thenReturn(createToken(token,tokenId));
		assertEquals(tokenMaintenanceController.createToken(token).getTokenId(), token.getTokenId());
	}
	
	@Test
	public void negVaidatorTest(@Param(name="Token") String tokenNo, @Param(name="ExpryDt") String expryDt, @Param(name="TokenStatus") String tokenStatus, @Param(name="TokenId") String tokenId) {
		Token token = new Token();
		token.setExpryDate(expryDt);
		token.setTokenStatus(tokenStatus);
		token.setToken(tokenNo);
		Mockito.when(tokenMaintenanceValidator.tokenCreationValidator(token)).thenReturn(false);
		assertEquals(tokenMaintenanceController.createToken(token).getTokenId(), null);
	}
	
	@Test
	public void negTestController(@Param(name="Token") String tokenNo, @Param(name="ExpryDt") String expryDt, @Param(name="TokenStatus") String tokenStatus) {
		Token token = new Token();
		token.setExpryDate(expryDt);
		token.setTokenStatus(tokenStatus);
		token.setToken(tokenNo);
		Mockito.when(tokenMaintenanceValidator.tokenCreationValidator(token)).thenReturn(true);
		Mockito.when(tokenMaintenanceService.createToken(token)).thenReturn(token);
		assertEquals(tokenMaintenanceController.createToken(token).getTokenId(), null);
	}
	
	private Token createToken(Token token, String tokenId) {
		token.setTokenId(tokenId);
		return token;
	}
	
}
