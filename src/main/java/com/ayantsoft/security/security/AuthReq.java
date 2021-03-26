package com.ayantsoft.security.security;

import java.io.Serializable;

public class AuthReq implements Serializable{

	/**
	 * serialVersionUID = 4017182565189596515L;
	 */
	private static final long serialVersionUID = 4017182565189596515L;
	private String userId;
	private String password;
	
	//setter and getter
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
	
}
