package com.fss.TokenMaintenance.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DBRoutingDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		return DBContextLoader.getSchemaType().getValue();
	}
	
	public void initDataSources(DataSource dataSource1, DataSource dataSource2) {
        Map<Object, Object> dsMap = new HashMap<Object, Object>();
        dsMap.put(DBSchemaType.DEF_INSTNAME.getValue(), dataSource1);
        dsMap.put(DBSchemaType.FIID_INSTNAME.getValue(), dataSource2);
        /*dsMap.entrySet().forEach(entry -> {
            System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
        }); */
   
        this.setTargetDataSources(dsMap);
        this.setDefaultTargetDataSource(dataSource1);
     }
}
