package com.fss.TokenMaintenance.token.update.validationService;

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
public class UpdateTokenValidatorTest {

	@Autowired
	TokenMaintenanceValidator tokenMaintenanceValidator;
	
	@Test
	public void testUpdateTokenValidatorPos() {
		Token token = new Token();
		token.setTokenStatus("Update");
		token.setTokenId("12");
		token.setExpryDate("2215");
		token.setToken("456515478468978");
		assertEquals(tokenMaintenanceValidator.tokenUpdateValidator(token), true);
	}
	
	@Test
	public void testUpdateTokenValidatorNeg() {
		Token token = new Token();
		token.setTokenStatus("Create");
		token.setTokenId("12");
		token.setExpryDate("2215");
		token.setToken("456515478468978");
		assertEquals(tokenMaintenanceValidator.tokenUpdateValidator(token), false);
	}
	
}
