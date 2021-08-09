package com.fss.TokenMaintenance.testSuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.fss.TokenMaintenance.config.RoutingConfiguration;
import com.fss.TokenMaintenance.token.fetchToken.controller.DDFetchTokenController;
import com.fss.TokenMaintenance.token.fetchToken.dao.DDFetchTokenDAO;
import com.fss.TokenMaintenance.token.fetchToken.service.DDFetchTokenService;

@RunWith(Suite.class)
@SuiteClasses({ DDFetchTokenController.class, DDFetchTokenDAO.class, DDFetchTokenService.class })
@ContextConfiguration(classes = { RoutingConfiguration.class }, loader = AnnotationConfigContextLoader.class)
public class FetchTokenTestSuite {

}
