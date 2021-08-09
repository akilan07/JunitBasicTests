package com.fss.TokenMaintenance.token.create.validationService;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.fss.TokenMaintenance.config.JunitConfig;
import com.fss.TokenMaintenance.config.RoutingConfiguration;
import com.fss.TokenMaintenance.token.Token;
import com.fss.TokenMaintenance.validationService.TokenMaintenanceValidator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RoutingConfiguration.class, JunitConfig.class},loader=AnnotationConfigContextLoader.class)
public class CreateTokenValdServiceTest {

	@Autowired
	TokenMaintenanceValidator tokenMaintenanceValidator;
	
	@Test
	public void testCreateTokenValidatorPos() {
		Token token = new Token();
		token.setTokenStatus("Create");
		token.setTokenId("12");
		token.setExpryDate("2215");
		token.setToken("456515478468978");
		assertEquals(tokenMaintenanceValidator.tokenCreationValidator(token), true);
	}
	
	@Test
	public void testCreateTokenValidatorNeg() {
		Token token = new Token();
		token.setTokenStatus("Create");
		token.setTokenId("12");
		token.setExpryDate("2215");
		token.setToken("");
		assertEquals(tokenMaintenanceValidator.tokenCreationValidator(token), false);
	}
	
}
