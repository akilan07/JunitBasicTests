package com.fss.TokenMaintenance.token.delete.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

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
import com.fss.TokenMaintenance.dao.TokenMaintenanceDAO;
import com.fss.TokenMaintenance.service.TokenMaintenanceService;

@RunWith(DataDrivenTestRunner.class)
@Report(outputLocation = "file:target")
@ContextConfiguration(classes = { RoutingConfiguration.class,
		JunitConfig.class }, loader = AnnotationConfigContextLoader.class)
@DataLoader(filePaths = { "TestTokenDeletion.xls" }, loaderType = LoaderType.EXCEL)
public class DDTokenDeletionService {

	@Mock
	TokenMaintenanceDAO tokenMaintenanceDAO;

	@InjectMocks
	TokenMaintenanceService tokenMaintenanceService;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Before
	public void datadrivenclass() throws Exception {
		new TestContextManager(getClass()).prepareTestInstance(this);
	}
	
	@Test
	public void tokenDeletionServicePosTest(@Param(name="TokenId") String tokenId) {
		Mockito.when(tokenMaintenanceDAO.deleteToken(tokenId)).thenReturn(true);
		assertTrue(tokenMaintenanceService.deleteToken(tokenId));
	}
	
	@Test
	public void tokenDeletionServiceNegTest(@Param(name="TokenId") String tokenId) {
		Mockito.when(tokenMaintenanceDAO.deleteToken(tokenId)).thenReturn(false);
		assertTrue(!tokenMaintenanceService.deleteToken(tokenId));
	}
	
}
