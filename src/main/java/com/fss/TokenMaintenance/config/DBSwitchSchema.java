package com.fss.TokenMaintenance.config;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBSwitchSchema implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static void switchContext(String schemaType) throws Exception {
		DBSchemaType cmsSchemaType[] = DBSchemaType.values();
		if (cmsSchemaType.length > 0) {
			if (!schemaType.equals("DEF")) {
				for (int i = 0; i < cmsSchemaType.length; i++) {
					if (cmsSchemaType[i].getValue().equalsIgnoreCase(schemaType)) {
						DBContextLoader.setSchemaType(cmsSchemaType[i]);
					}
				}
			} else {
				DBContextLoader.setSchemaType(DBSchemaType.DEF_INSTNAME);
			}
		} else {
			throw new Exception("Schema type is empty");
		}
	}

	public static boolean closeAll(Connection connection, PreparedStatement ps,
			ResultSet res) throws SQLException {
		boolean connctnClsStatus = false;
		if (res != null) {
			res.close();
			res = null;
		}
		if (ps != null) {
			ps.close();
			ps = null;
		}
		if (connection != null) {
			connection.close();
			connection = null;
		}
		connctnClsStatus = true;
		return connctnClsStatus;
	}

	public static boolean closeAll(Connection connection, PreparedStatement ps)
			throws SQLException {
		boolean connctnClsStatus = false;

		if (ps != null) {
			ps.close();
			ps = null;
		}
		if (connection != null) {
			connection.close();
			connection = null;
		}
		connctnClsStatus = true;
		return connctnClsStatus;
	}

	public static boolean closeAllConnections(Connection connection,
			Statement ps, ResultSet res) throws SQLException {
		boolean connctnClsStatus = false;
		if (res != null) {
			res.close();
			res = null;
		}
		if (ps != null) {
			ps.close();
			ps = null;
		}
		if (connection != null) {
			connection.close();
			connection = null;
		}
		connctnClsStatus = true;
		return connctnClsStatus;
	}

	public static boolean closeConnection(Connection connection)
			throws SQLException {
		boolean connctnClsStatus = false;
		if (connection != null) {
			connection.close();
			connection = null;
		}
		connctnClsStatus = true;
		return connctnClsStatus;
	}

	public static boolean closeResultset(ResultSet res) throws SQLException {
		boolean resltsetClsStatus = false;
		if (res != null) {
			res.close();
			res = null;
		}
		resltsetClsStatus = true;
		return resltsetClsStatus;
	}

	public static boolean closePreprdStatmnt(PreparedStatement ps)
			throws SQLException {
		boolean preprdStatmntStatus = false;
		if (ps != null) {
			ps.close();
			ps = null;
		}
		preprdStatmntStatus = true;
		return preprdStatmntStatus;
	}
}
