package com.fss.TokenMaintenance.token.update.dao;

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
public class TokenUpdateTest {

	@Autowired
	TokenMaintenanceDAO tokenMaintenanceDAO;
	
	@Test
	public void tokenUpdatePosTest() {
		Token token = new Token();
		token.setCustId("10144682");
		token.setStan("002314");
		token.setToken("5264081235001200");
		token.setTokenStatus("Update");
		token.setTokenId("67");
		token.setExpryDate("2412");
		
		assertTrue(tokenMaintenanceDAO.updateToken(token));
	}
	
	@Test
	public void tokenUpdateNegTest() {
		Token token = new Token();
		token.setCustId("10144682");
		token.setStan("002314");
		token.setToken("5264081235001200");
		token.setTokenStatus("Update");
		token.setTokenId("ta");
		token.setExpryDate("2412");
		
		assertTrue(!tokenMaintenanceDAO.updateToken(token));
	}
	
}
