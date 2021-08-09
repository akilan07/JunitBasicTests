package com.fss.TokenMaintenance.testSuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.fss.TokenMaintenance.config.RoutingConfiguration;
import com.fss.TokenMaintenance.token.create.dao.DDTokenCreationDAO;
import com.fss.TokenMaintenance.token.delete.dao.DeleteTokenDAOTestSuite;

@RunWith(Suite.class)
@SuiteClasses({ DDTokenCreationDAO.class, DeleteTokenDAOTestSuite.class })
@ContextConfiguration(classes = { RoutingConfiguration.class }, loader = AnnotationConfigContextLoader.class)
public class TestSuiteSample {

}
