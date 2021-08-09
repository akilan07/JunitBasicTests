package com.fss.TokenMaintenance.token.delete.validationService;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.annotation.Report;
import org.easetech.easytest.loader.LoaderType;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContextManager;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.fss.TokenMaintenance.config.JunitConfig;
import com.fss.TokenMaintenance.config.RoutingConfiguration;
import com.fss.TokenMaintenance.validationService.TokenMaintenanceValidator;

@RunWith(DataDrivenTestRunner.class)
@Report(outputLocation = "file:target")
@ContextConfiguration(classes = { RoutingConfiguration.class,
		JunitConfig.class }, loader = AnnotationConfigContextLoader.class)
@DataLoader(filePaths = { "TestTokenDeletion.xls" }, loaderType = LoaderType.EXCEL)
public class DDDeleteTokenValdService {

	@Autowired
	TokenMaintenanceValidator tokenMaintenanceValidator;
	
	@Before
	public void datadrivenclass() throws Exception {
		new TestContextManager(getClass()).prepareTestInstance(this);
	}

	@Test
	public void testDeleteTokenValidatorPos(@Param(name="TokenId") String tokenId) {
		assertEquals(tokenMaintenanceValidator.tokenDeleteValidator(tokenId), true);
	}
	
	@Test
	public void testDeleteTokenValidatorNeg(@Param(name="TokenId") String tokenId) {
		assertEquals(tokenMaintenanceValidator.tokenDeleteValidator(tokenId), false);
	}
	
}
