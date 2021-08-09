package com.fss.TokenMaintenance.validationService;

import org.springframework.stereotype.Service;

import com.fss.TokenMaintenance.token.Token;

@Service
public class TokenMaintenanceValidator {

	public boolean tokenCreationValidator(Token token) {
		if (token.getToken() != null && !token.getToken().isEmpty()) {
			if (!token.getTokenStatus().isEmpty() && token.getTokenStatus().equalsIgnoreCase("Create")
					&& token.getExpryDate() != null && !token.getExpryDate().isEmpty()) {
				System.out.println("Token validation Success");
				return true;
			}
		}
		System.out.println("Token validation failed");
		return false;
	}

	public boolean tokenUpdateValidator(Token token) {
		if (token.getTokenId() != null && !token.getTokenId().isEmpty()) {
			if (token.getTokenStatus() != null && !token.getTokenStatus().isEmpty()
					&& token.getTokenStatus().equalsIgnoreCase("Update") && token.getToken() != null
					&& !token.getToken().isEmpty() && token.getExpryDate() != null && !token.getExpryDate().isEmpty()) {
				System.out.println("Token validation Success");
				return true;
			}
		}
		System.out.println("Token validation failed");
		return false;
	}

	public boolean tokenDeleteValidator(String tokenId) {
		if (tokenId != null && !tokenId.isEmpty() &&!tokenId.equals("0")) {
			System.out.println("Token validation Success");
			return true;
		}
		System.out.println("Token validation failed");
		return false;
	}
}
