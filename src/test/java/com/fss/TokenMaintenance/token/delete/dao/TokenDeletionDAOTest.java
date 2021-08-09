package com.fss.TokenMaintenance.token.delete.dao;

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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RoutingConfiguration.class,
		JunitConfig.class }, loader = AnnotationConfigContextLoader.class)
public class TokenDeletionDAOTest {

	@Autowired
	TokenMaintenanceDAO tokenMaintenanceDAO;

	@Test
	public void tokenDeletePosTest() {
		assertTrue(tokenMaintenanceDAO.deleteToken("67"));
	}

	@Test
	public void tokenDeleteNegTest() {
		assertTrue(!tokenMaintenanceDAO.deleteToken("900"));
	}

}
