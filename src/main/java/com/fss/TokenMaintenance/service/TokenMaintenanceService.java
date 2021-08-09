package com.fss.TokenMaintenance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fss.TokenMaintenance.dao.TokenMaintenanceDAO;
import com.fss.TokenMaintenance.token.Token;

@Service
public class TokenMaintenanceService {
	
	@Autowired
	TokenMaintenanceDAO tokenMaintenanceDAO;

	public Token createToken(Token token) {
		token = tokenMaintenanceDAO.createToken(token);
		return token;
	}
	
	public boolean updateToken(Token token) {
		return tokenMaintenanceDAO.updateToken(token);
	}
	
	public boolean deleteToken(String tokenId) {
		return tokenMaintenanceDAO.deleteToken(tokenId);
	}
	
	public List<Token> getTokens(){
		return tokenMaintenanceDAO.getTokens();
	}

	public Token getTokensById(String tokenid) {
		Token token = tokenMaintenanceDAO.getTokensById(tokenid);
		if(token != null && token.getTokenId() != null && !token.getTokenId().equals("0")) {
			System.out.println("Token details are fetched");
		}else {
			token.setResMsg("No token is found");
		}
		return token;
	}
}
