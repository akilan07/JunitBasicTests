package com.fss.TokenMaintenance.token.create.service;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.fss.TokenMaintenance.config.JunitConfig;
import com.fss.TokenMaintenance.config.RoutingConfiguration;
import com.fss.TokenMaintenance.dao.TokenMaintenanceDAO;
import com.fss.TokenMaintenance.service.TokenMaintenanceService;
import com.fss.TokenMaintenance.token.Token;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RoutingConfiguration.class,
		JunitConfig.class }, loader = AnnotationConfigContextLoader.class)
public class TokenCreationServiceTest {

	@Mock
	TokenMaintenanceDAO tokenMaintenanceDAO;

	@InjectMocks
	TokenMaintenanceService tokenMaintenanceService;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void tokenServicePosTest() {
		Token token = new Token();
		token.setExpryDate("2610");
		token.setToken("465245488415484");
		token.setTokenStatus("Create");
		Mockito.when(tokenMaintenanceDAO.createToken(token)).thenReturn(createToken(token, "55"));
		assertEquals(tokenMaintenanceService.createToken(token).getTokenId(), "55");
	}

	private Token createToken(Token token, String tokenId) {
		token.setTokenId(tokenId);
		return token;
	}
	
	@Test
	public void tokenServiceNegTest() {
		Token token = new Token();
		token.setExpryDate("2610");
		token.setToken("465245488415484");
		token.setTokenStatus("Create");
		Mockito.when(tokenMaintenanceDAO.createToken(token)).thenReturn(createToken(token, null));
		assertEquals(tokenMaintenanceService.createToken(token).getTokenId(), null);
	}
}
