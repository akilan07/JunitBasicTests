package com.fss.TokenMaintenance.token.delete.validationService;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.fss.TokenMaintenance.config.JunitConfig;
import com.fss.TokenMaintenance.config.RoutingConfiguration;
import com.fss.TokenMaintenance.validationService.TokenMaintenanceValidator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RoutingConfiguration.class, JunitConfig.class},loader=AnnotationConfigContextLoader.class)
public class DeleteTokenValdServiceTest {

	@Autowired
	TokenMaintenanceValidator tokenMaintenanceValidator;
	
	@Test
	public void testDeleteTokenValidatorPos() {
		String tokenId = "15";
		assertEquals(tokenMaintenanceValidator.tokenDeleteValidator(tokenId), true);
	}
	
	@Test
	public void testDeleteTokenValidatorNeg() {
		String tokenId = "";
		assertEquals(tokenMaintenanceValidator.tokenDeleteValidator(tokenId), false);
	}
	
}
