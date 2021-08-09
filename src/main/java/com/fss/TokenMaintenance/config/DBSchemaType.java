package com.fss.TokenMaintenance.config;

import java.util.Properties;

public enum DBSchemaType {
	DEF_INSTNAME, FIID_INSTNAME;
	private static final String PATH = "/application.properties";
	private static Properties properties;
	private String value;

	private void init() {
		if (properties == null) {
			properties = new Properties();
			try {
				properties.load(DBSchemaType.class.getResourceAsStream(PATH));
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(1);
				
			}
		}
		value = (String) properties.get(this.toString());
	}

	public String getValue() {
		if (value == null) {
			init();
		}
		return value;
	}

}
