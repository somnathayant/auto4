package com.ayantsoft.security.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import java.util.Arrays;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import com.ayantsoft.security.security.repository.User;


public class MyUserDetails implements UserDetails{

	private User user;
	public MyUserDetails(User user) {
		this.user=user;
	}
	
	
	
	@Override 
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		
		System.out.println(this.user.getUserRoles());
		
		
		List<GrantedAuthority>roles=Arrays.stream(this.user.getUserRoles().split(","))
				.map(SimpleGrantedAuthority::new)
				 .collect(Collectors.toList());
		return roles;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.user.getUserId();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
