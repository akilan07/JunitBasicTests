package com.fss.TokenMaintenance.token;

public class Token {

	private String tokenId;
	private String tokenStatus;
	private String token;
	private String cardnumber;
	private String stan;
	private String custId;
	private String expryDate;
	private String resMsg;
	private int resCode;
	
	public String getResMsg() {
		return resMsg;
	}

	public void setResMsg(String resMsg) {
		this.resMsg = resMsg;
	}

	public int getResCode() {
		return resCode;
	}

	public void setResCode(int resCode) {
		this.resCode = resCode;
	}

	@Override
	public String toString() {
		return "Token [tokenId=" + tokenId + ", tokenStatus=" + tokenStatus + ", token=" + token + ", cardnumber="
				+ cardnumber + ", stan=" + stan + ", custId=" + custId + ", expryDate=" + expryDate + ", resMsg="
				+ resMsg + "]";
	}
	
	public String getExpryDate() {
		return expryDate;
	}

	public void setExpryDate(String expryDate) {
		this.expryDate = expryDate;
	}

	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getTokenId() {
		return tokenId;
	}
	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}
	public String getTokenStatus() {
		return tokenStatus;
	}
	public void setTokenStatus(String tokenStatus) {
		this.tokenStatus = tokenStatus;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getCardnumber() {
		return cardnumber;
	}
	public void setCardnumber(String cardnumber) {
		this.cardnumber = cardnumber;
	}
	public String getStan() {
		return stan;
	}
	public void setStan(String stan) {
		this.stan = stan;
	}

	
}
