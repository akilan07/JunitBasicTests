package com.fss.TokenMaintenance.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fss.TokenMaintenance.config.DBSwitchSchema;
import com.fss.TokenMaintenance.dao.TokenMaintenanceDAO;
import com.fss.TokenMaintenance.token.Token;

@Repository
public class TokenMaintenanceDAOImpl implements TokenMaintenanceDAO {

	@Autowired
	DataSource dataSource;

	@Override
	public Token createToken(Token token) {
		Connection connection = null;
		CallableStatement preStmt = null;
		int res = 0;
		try {
			DBSwitchSchema.switchContext("ADC");
			connection = dataSource.getConnection();
			connection.setAutoCommit(false);
			preStmt = connection.prepareCall(
					"BEGIN INSERT into CMS_CRD_TOKN_DETL (CXG_TKN_ID, CXG_CUST_ID, CXG_STAN, CXG_TKN,CXG_TKN_EVNT_INDCTR,CXG_TKN_DELT_STAT,CXG_TKN_EXP_DT) VALUES(seq_crd_tokn_detl.nextval, ?, ?,?,?,?,?) RETURNING CXG_TKN_ID INTO ?; END;");

			preStmt.setString(1, token.getCustId());
			preStmt.setString(2, token.getStan());
			preStmt.setString(3, token.getToken());
			preStmt.setString(4, token.getTokenStatus());
			if (token.getTokenStatus().equalsIgnoreCase("create")) {
				preStmt.setInt(5, 1);
			}
			preStmt.setString(6, token.getExpryDate());
			preStmt.registerOutParameter(7, java.sql.Types.VARCHAR);
			res = preStmt.executeUpdate();

			token.setTokenId(preStmt.getString(7));
			System.out.println("Query Result :: " + res);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (res == 1) {
					connection.commit();
				} else {
					connection.rollback();
				}
				DBSwitchSchema.closeAll(connection, preStmt, null);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Created Token Id : " + token.getTokenId());
		return token;
	}

	@Override
	public boolean updateToken(Token token) {
		Connection connection = null;
		PreparedStatement ps = null;
		int rs = 0;
		try {
			DBSwitchSchema.switchContext("ADC");
			connection = dataSource.getConnection();
			connection.setAutoCommit(false);

			ps = connection.prepareStatement(
					"UPDATE CMS_CRD_TOKN_DETL SET CXG_TKN_EVNT_INDCTR = ?, CXG_TKN_EXP_DT = ? WHERE CXG_TKN_ID = ?");

			ps.setString(1, token.getTokenStatus());
			ps.setString(2, token.getExpryDate());
			ps.setInt(3, Integer.parseInt(token.getTokenId()));

			rs = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs == 1) {
					connection.commit();
				} else {
					connection.rollback();
				}
				DBSwitchSchema.closeAll(connection, ps, null);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return (rs == 1);
	}

	@Override
	public boolean deleteToken(String tokenId) {
		Connection connection = null;
		PreparedStatement ps = null;
		int rs = 0;
		try {
			DBSwitchSchema.switchContext("ADC");
			connection = dataSource.getConnection();
			connection.setAutoCommit(false);

			ps = connection.prepareStatement("UPDATE CMS_CRD_TOKN_DETL SET CXG_TKN_DELT_STAT = ? WHERE CXG_TKN_ID = ?");

			ps.setInt(1, 2);

			ps.setInt(2, Integer.parseInt(tokenId));

			rs = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs == 1) {
					connection.commit();
				} else {
					connection.rollback();
				}
				DBSwitchSchema.closeAll(connection, ps, null);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return (rs == 1);
	}

	@Override
	public List<Token> getTokens() {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Token token = null;
		List<Token> tokens = null;
		try {
			DBSwitchSchema.switchContext("ADC");
			connection = dataSource.getConnection();

			ps = connection.prepareStatement(
					"SELECT CXG_TKN_ID,CXG_STAN,CXG_TKN,CXG_TKN_EVNT_INDCTR,CXG_TKN_EXP_DT FROM CMS_CRD_TOKN_DETL WHERE CXG_TKN_DELT_STAT = ?");
			ps.setInt(1, 1);
			rs = ps.executeQuery();

			tokens = new ArrayList<Token>();

			while (rs.next()) {
				token = new Token();
				token.setTokenId(rs.getString("CXG_TKN_ID"));
				token.setStan(rs.getString("CXG_STAN"));
				token.setToken(rs.getString("CXG_TKN"));
				token.setTokenStatus(rs.getString("CXG_TKN_EVNT_INDCTR"));
				token.setExpryDate(rs.getString("CXG_TKN_EXP_DT"));
				tokens.add(token);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DBSwitchSchema.closeAll(connection, ps, null);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return tokens;
	}

	@Override
	public Token getTokensById(String tokenid) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Token token = null;
		try {
			DBSwitchSchema.switchContext("ADC");
			connection = dataSource.getConnection();

			ps = connection.prepareStatement(
					"SELECT CXG_TKN_ID, CXG_STAN,CXG_TKN,CXG_TKN_EVNT_INDCTR,CXG_TKN_EXP_DT FROM CMS_CRD_TOKN_DETL WHERE CXG_TKN_DELT_STAT = ? AND CXG_TKN_ID = ?");
			ps.setInt(1, 1);
			ps.setInt(2, Integer.parseInt(tokenid));
			rs = ps.executeQuery();

			while (rs.next()) {
				token = new Token();
				token.setTokenId(rs.getString("CXG_TKN_ID"));
				token.setStan(rs.getString("CXG_STAN"));
				token.setToken(rs.getString("CXG_TKN"));
				token.setTokenStatus(rs.getString("CXG_TKN_EVNT_INDCTR"));
				token.setExpryDate(rs.getString("CXG_TKN_EXP_DT"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DBSwitchSchema.closeAll(connection, ps, null);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return token;
	}
}
