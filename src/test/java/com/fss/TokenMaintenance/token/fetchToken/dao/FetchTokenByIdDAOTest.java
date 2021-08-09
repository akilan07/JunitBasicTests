package com.fss.TokenMaintenance.token.fetchToken.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.fss.TokenMaintenance.config.JunitConfig;
import com.fss.TokenMaintenance.config.RoutingConfiguration;
import com.fss.TokenMaintenance.dao.TokenMaintenanceDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RoutingConfiguration.class, JunitConfig.class},loader=AnnotationConfigContextLoader.class)
public class FetchTokenByIdDAOTest {
	@Autowired
	TokenMaintenanceDAO tokenMaintenanceDAO;
	
	@Test
	public void tokenDeletePosTest() {
		
		assertEquals(tokenMaintenanceDAO.getTokensById("74").getToken(), "65425454221512554");
	}
	
	@Test
	public void tokenDeleteNegTest() {
		
		assertEquals(tokenMaintenanceDAO.getTokensById("900"), null);
	}
}
