package com.fss.TokenMaintenance.config;
import java.io.Serializable;

public class DBContextLoader implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final ThreadLocal<DBSchemaType> contextHolder =

	new ThreadLocal<DBSchemaType>();

	public static void setSchemaType(DBSchemaType cmsSchemaType) {
		contextHolder.set(cmsSchemaType);
	}

	public static DBSchemaType getSchemaType() {
		return (DBSchemaType) contextHolder.get();
	}

	public static void clearSchemaType() {
		contextHolder.remove();
	}

}
