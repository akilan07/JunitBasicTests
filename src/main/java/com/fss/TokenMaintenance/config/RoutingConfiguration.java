package com.fss.TokenMaintenance.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@PropertySource({ ("classpath:DBconfig.properties") })
public class RoutingConfiguration {

	@Autowired
	private Environment env;

	@Autowired
	@Bean(name = "dataSource")
	public DataSource getDataSource(DataSource dataSource1, DataSource dataSource2) {
		System.out.println("## Create DataSource from dataSource1 & dataSource2");
		DBRoutingDataSource dataSource = new DBRoutingDataSource();
		dataSource.initDataSources(dataSource1, dataSource2);
		return dataSource;
	}

	@Bean(name = "dataSource1")
	public DataSource defDataSource() throws SQLException {

		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
		dataSource.setUrl(env.getProperty("spring.datasource.url"));
		dataSource.setUsername(env.getProperty("cmsusername"));
		dataSource.setPassword(env.getProperty("cmspassword"));
		return dataSource;

	}

	@Bean(name = "dataSource2")
	public DataSource fiidDataSource() throws SQLException {

		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
		dataSource.setUrl(env.getProperty("spring.datasource.url"));
		dataSource.setUsername(env.getProperty("fiidusername"));
		dataSource.setPassword(env.getProperty("fiidpassword"));
		return dataSource;

	}

}
