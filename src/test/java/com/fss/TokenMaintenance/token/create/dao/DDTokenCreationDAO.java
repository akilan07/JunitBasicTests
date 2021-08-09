package com.fss.TokenMaintenance.token.create.dao;

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
@DataLoader(filePaths = { "TestTokenCreation.xls" }, loaderType = LoaderType.EXCEL)
public class DDTokenCreationDAO {
	@Autowired
	TokenMaintenanceDAO tokenMaintenanceDAO;
	
	@Before
	public void datadrivenclass() throws Exception {
		new TestContextManager(getClass()).prepareTestInstance(this);
	}
	
	@Test
	public void testTokenDaoPos(@Param(name="CustId")String custId, @Param(name="STAN")String stan,  @Param(name="Token")String tkn,  @Param(name="TokenStatus")String tokenStatus ) {
		Token token = new Token();
		token.setCustId(custId);
		token.setStan(stan);
		token.setToken(tkn);
		token.setTokenStatus(tokenStatus);
		assertTrue(!tokenMaintenanceDAO.createToken(token).getTokenId().isEmpty());
	}
	
	@Test
	public void testTokenDaoNeg(@Param(name="CustId")String custId, @Param(name="STAN")String stan,  @Param(name="Token")String tkn,  @Param(name="TokenStatus")String tokenStatus ) {
		Token token = new Token();
		token.setCustId(custId);
		token.setStan(stan);
		token.setToken(tkn);
		token.setTokenStatus(tokenStatus);
		assertTrue(tokenMaintenanceDAO.createToken(token).getTokenId()== null);
	}
}
