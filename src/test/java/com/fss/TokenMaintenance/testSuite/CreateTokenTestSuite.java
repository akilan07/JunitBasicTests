package com.fss.TokenMaintenance.testSuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.fss.TokenMaintenance.config.JunitConfig;
import com.fss.TokenMaintenance.config.RoutingConfiguration;
import com.fss.TokenMaintenance.token.create.Controller.DDTokenCreationController;
import com.fss.TokenMaintenance.token.create.dao.DDTokenCreationDAO;
import com.fss.TokenMaintenance.token.create.service.DDTokenCreationService;
import com.fss.TokenMaintenance.token.create.validationService.DDCreateTokenValdService;

@RunWith(Suite.class)
@SuiteClasses({ DDTokenCreationController.class, DDTokenCreationDAO.class, DDTokenCreationService.class,
		DDCreateTokenValdService.class })
@ContextConfiguration(classes = { RoutingConfiguration.class,
		JunitConfig.class }, loader = AnnotationConfigContextLoader.class)
public class CreateTokenTestSuite {

}
