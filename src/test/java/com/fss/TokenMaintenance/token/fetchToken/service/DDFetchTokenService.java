package com.fss.TokenMaintenance.token.fetchToken.service;

import static org.junit.Assert.assertEquals;

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
import com.fss.TokenMaintenance.token.Token;

@RunWith(DataDrivenTestRunner.class)
@Report(outputLocation = "file:target")
@ContextConfiguration(classes = { RoutingConfiguration.class,
		JunitConfig.class }, loader = AnnotationConfigContextLoader.class)
@DataLoader(filePaths = { "TestFetchToken.xls" }, loaderType = LoaderType.EXCEL)
public class DDFetchTokenService {

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
	public void fetchTokenServicePosTest(@Param(name="ExpryDt") String expryDt, @Param(name="TokenId") String tokenId, @Param(name="Token") String tokenNo) {
		Token token = new Token();
		token.setExpryDate(expryDt);
		token.setTokenId(tokenId);
		token.setToken(tokenNo);
		token.setTokenStatus("Create");
		Mockito.when(tokenMaintenanceDAO.getTokensById(tokenId)).thenReturn(token);
		assertEquals(tokenMaintenanceService.getTokensById(tokenId).getToken(),tokenNo);
	}
	
	@Test
	public void fetchTokenServiceNegTest(@Param(name="TokenId") String tokenId) {
		Token token = new Token();
		token.setTokenId(tokenId);
		Mockito.when(tokenMaintenanceDAO.getTokensById(tokenId)).thenReturn(token);
		assertEquals(tokenMaintenanceService.getTokensById(tokenId).getToken(),null);
	}
}
