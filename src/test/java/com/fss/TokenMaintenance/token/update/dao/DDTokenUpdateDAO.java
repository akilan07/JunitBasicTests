package com.fss.TokenMaintenance.token.update.dao;

import static org.junit.Assert.assertTrue;

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
import com.fss.TokenMaintenance.token.Token;

@RunWith(DataDrivenTestRunner.class)
@Report(outputLocation = "file:target")
@ContextConfiguration(classes = { RoutingConfiguration.class,
		JunitConfig.class }, loader = AnnotationConfigContextLoader.class)
@DataLoader(filePaths = { "TestUpdateToken.xls" }, loaderType = LoaderType.EXCEL)
public class DDTokenUpdateDAO {
	@Autowired
	TokenMaintenanceDAO tokenMaintenanceDAO;

	@Before
	public void datadrivenclass() throws Exception {
		new TestContextManager(getClass()).prepareTestInstance(this);
	}

	@Test
	public void testTokenUpdateDaoPos(@Param(name = "TokenId") String tokenId, @Param(name = "TokenExpry") String expryDt, @Param(name = "TokenStatus") String tokenStatus) {
		Token token = new Token();
		token.setTokenId(tokenId);
		token.setExpryDate(expryDt);
		token.setTokenStatus(tokenStatus);
		assertTrue(tokenMaintenanceDAO.updateToken(token));
	}

	@Test
	public void testTokenUpdateDaoNeg(@Param(name = "TokenId") String tokenId, @Param(name = "TokenExpry") String expryDt, @Param(name = "TokenStatus") String tokenStatus) {
		Token token = new Token();
		token.setTokenId(tokenId);
		token.setExpryDate(expryDt);
		token.setTokenStatus(tokenStatus);
		assertTrue(!tokenMaintenanceDAO.updateToken(token));
	}
}
