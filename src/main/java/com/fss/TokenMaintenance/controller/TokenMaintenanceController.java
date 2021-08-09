package com.fss.TokenMaintenance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fss.TokenMaintenance.service.TokenMaintenanceService;
import com.fss.TokenMaintenance.token.Token;
import com.fss.TokenMaintenance.validationService.TokenMaintenanceValidator;

@RestController
@RequestMapping("/token")
@CrossOrigin
public class TokenMaintenanceController {

	@Autowired
	TokenMaintenanceValidator tokenMaintenanceValidator;

	@Autowired
	TokenMaintenanceService tokenMaintenanceService;

	@RequestMapping(value = "/createToken", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public Token createToken(@RequestBody Token token) {
		System.out.println("Validation");
		if (tokenMaintenanceValidator.tokenCreationValidator(token)) {
			tokenMaintenanceService.createToken(token);
		}
		System.out.println("Returing Token :: " + token.toString());
		return token;
	}

	@RequestMapping(value = "/updateToken", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public Token updateToken(@RequestBody Token token) {
		System.out.println("Validation");
		if (tokenMaintenanceValidator.tokenUpdateValidator(token)) {
			if (tokenMaintenanceService.updateToken(token)) {
				token.setResCode(0);
				token.setResMsg("Updated Successfully");
			} else {
				token.setResCode(1);
				token.setResMsg("Updated Failed");
			}
		} else {
			token.setResCode(1);
			token.setResMsg("Updated Failed");
		}
		System.out.println("update Token status :: " + token);
		return token;
	}

	@RequestMapping(value = "/deleteToken", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public Token deleteToken(@RequestParam String tokenId) {
		System.out.println("Validation");
		Token token = new Token();
		token.setTokenId(tokenId);
		if (tokenMaintenanceValidator.tokenDeleteValidator(tokenId)) {
			if (tokenMaintenanceService.deleteToken(tokenId)) {
				token.setResCode(0);
				token.setResMsg("Deleted Successfully");
			} else {
				token.setResCode(1);
				token.setResMsg("Deleted failed");
			}
		} else {
			token.setResCode(1);
			token.setResMsg("Deleted failed");
		}
		System.out.println("Delete Token status :: " + token);
		return token;
	}
	
	@RequestMapping(value = "/getToken", method = RequestMethod.GET, produces = "application/json", consumes = "application/json")
	public List<Token> getTokens() {
		return tokenMaintenanceService.getTokens();
	}
	
	@RequestMapping(value = "/getToken", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public Token getTokensById(@RequestParam String tokenId) {
		return tokenMaintenanceService.getTokensById(tokenId);
	}
}
