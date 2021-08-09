package com.fss.TokenMaintenance.token.create.dao;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.fss.TokenMaintenance.config.JunitConfig;
import com.fss.TokenMaintenance.config.RoutingConfiguration;
import com.fss.TokenMaintenance.dao.TokenMaintenanceDAO;
import com.fss.TokenMaintenance.token.Token;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RoutingConfiguration.class, JunitConfig.class},loader=AnnotationConfigContextLoader.class)
public class TokenCreationDAOTest {

	@Autowired
	TokenMaintenanceDAO tokenMaintenanceDAO;
	
	@Test
	public void testTokenDaoPos() {
		Token token = new Token();
		token.setCustId("10144682");
		token.setStan("002314");
		token.setToken("5264081235001200");
		token.setTokenStatus("Create");
		assertTrue(!tokenMaintenanceDAO.createToken(token).getTokenId().isEmpty());
	}
	
	
	@Test
	public void testTokenDaoNeg() {
		Token token = new Token();
		token.setCustId("10144682");
		token.setStan("002314");
		token.setToken("5264081235001200");
		assertTrue(tokenMaintenanceDAO.createToken(token).getTokenId()== null);
	}
	
}
