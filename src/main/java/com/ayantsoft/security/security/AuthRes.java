package com.ayantsoft.security.security;

public class AuthRes {

	private String jwt;

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	public AuthRes(String jwt) {
		
		this.jwt = jwt;
	}
	
	
	

}
