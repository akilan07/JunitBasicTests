package com.fss.TokenMaintenance.dao;

import java.util.List;

import com.fss.TokenMaintenance.token.Token;

public interface TokenMaintenanceDAO {

	public Token createToken(Token token);
	
	public boolean updateToken(Token token);
	
	public boolean deleteToken(String tokenId);
	
	public List<Token> getTokens();

	public Token getTokensById(String tokenid);
}
