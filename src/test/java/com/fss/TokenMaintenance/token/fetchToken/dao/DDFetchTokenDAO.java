package com.fss.TokenMaintenance.token.fetchToken.dao;

import static org.junit.Assert.assertEquals;

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
import com.fss.TokenMaintenance.dao.TokenMaintenanceDAO;

@RunWith(DataDrivenTestRunner.class)
@Report(outputLocation = "file:target")
@ContextConfiguration(classes = { RoutingConfiguration.class,
		JunitConfig.class }, loader = AnnotationConfigContextLoader.class)
@DataLoader(filePaths = { "TestFetchToken.xls" }, loaderType = LoaderType.EXCEL)
public class DDFetchTokenDAO {

	@Autowired
	TokenMaintenanceDAO tokenMaintenanceDAO;

	@Before
	public void datadrivenclass() throws Exception {
		new TestContextManager(getClass()).prepareTestInstance(this);
	}

	@Test
	public void fetchTokenDaoPos(@Param(name = "TokenId") String tokenId, @Param(name = "Token") String token) {
		assertEquals(tokenMaintenanceDAO.getTokensById(tokenId).getToken(), token);
	}

	@Test
	public void fetchTokenDaoNeg(@Param(name = "TokenId") String tokenId) {
		assertEquals(tokenMaintenanceDAO.getTokensById(tokenId), null);
	}

}
