package com.fss.TokenMaintenance.token.create.Controller;

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
public class TokenCreationControllerTest {

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
	public void createTokenControllerTestPos() {
		Token token = new Token();
		token.setToken("426512012100221");
		token.setExpryDate("1209");
		Mockito.when(tokenMaintenanceService.createToken(token)).thenReturn(createToken(token, "55"));
		Mockito.when(tokenMaintenanceValidator.tokenCreationValidator(token)).thenReturn(true);
		assertEquals(tokenMaintenanceController.createToken(token).getTokenId(), token.getTokenId());
	}

	@Test
	public void createTokenControllerTestNegVald() {
		Token token = new Token();
		Mockito.when(tokenMaintenanceValidator.tokenCreationValidator(token)).thenReturn(false);
		assertEquals(tokenMaintenanceController.createToken(token).getTokenId(), null);
	}
	
	@Test
	public void createTokenControllerTestNeg() {
		Token token = new Token();
		Mockito.when(tokenMaintenanceService.createToken(token)).thenReturn(createToken(token, null));
		Mockito.when(tokenMaintenanceValidator.tokenCreationValidator(token)).thenReturn(true);
		assertEquals(tokenMaintenanceController.createToken(token).getTokenId(), null);
	}

	private Token createToken(Token token, String tokenId) {
		token.setTokenId(tokenId);
		return token;
	}
	
}

