package com.fss.TokenMaintenance.testSuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.fss.TokenMaintenance.config.RoutingConfiguration;
import com.fss.TokenMaintenance.token.delete.controller.DDTokenDeletionController;
import com.fss.TokenMaintenance.token.delete.dao.DDTokenDeletionDAO;
import com.fss.TokenMaintenance.token.delete.service.DDTokenDeletionService;
import com.fss.TokenMaintenance.token.delete.validationService.DDDeleteTokenValdService;

@RunWith(Suite.class)
@SuiteClasses({ DDTokenDeletionController.class, DDTokenDeletionDAO.class, DDTokenDeletionService.class, DDDeleteTokenValdService.class })
@ContextConfiguration(classes = { RoutingConfiguration.class }, loader = AnnotationConfigContextLoader.class)
public class DeleteTokenTestSuite {

}
