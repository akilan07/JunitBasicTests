package com.fss.TokenMaintenance.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fss.TokenMaintenance.dao.TokenMaintenanceDAO;
import com.fss.TokenMaintenance.dao.impl.TokenMaintenanceDAOImpl;
import com.fss.TokenMaintenance.service.TokenMaintenanceService;
import com.fss.TokenMaintenance.validationService.TokenMaintenanceValidator;

@Configuration
public class JunitConfig {

	@Bean(name = "tokenMaintenanceDAO")
	public TokenMaintenanceDAO getTokenMaintenanceDAO() {
		return new TokenMaintenanceDAOImpl();
	}
	
	@Bean(name="tokenMaintenanceService")
	public TokenMaintenanceService getTokenMaintenanceService() {
		return new TokenMaintenanceService();
	}
	
	@Bean(name="tokenMaintenanceValidator")
	public TokenMaintenanceValidator getTokenMaintenanceValidator() {
		return new TokenMaintenanceValidator();
	}
	
}
