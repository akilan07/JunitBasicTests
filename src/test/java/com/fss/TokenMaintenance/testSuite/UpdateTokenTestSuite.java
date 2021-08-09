package com.fss.TokenMaintenance.testSuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.fss.TokenMaintenance.config.RoutingConfiguration;
import com.fss.TokenMaintenance.token.update.dao.DDTokenUpdateDAO;
import com.fss.TokenMaintenance.token.update.service.DDUpdateTokenService;
import com.fss.TokenMaintenance.token.update.validationService.DDUpdateTokenValdition;

@RunWith(Suite.class)
@SuiteClasses({ DDTokenUpdateDAO.class, DDUpdateTokenService.class, DDUpdateTokenValdition.class })
@ContextConfiguration(classes = { RoutingConfiguration.class }, loader = AnnotationConfigContextLoader.class)
public class UpdateTokenTestSuite {

}
