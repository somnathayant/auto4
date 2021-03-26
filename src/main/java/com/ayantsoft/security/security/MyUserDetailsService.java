package com.ayantsoft.security.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ayantsoft.security.security.repository.User;
import com.ayantsoft.security.security.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired 
	UserRepository userRepository; 
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		System.out.print("in userdetails service"+username);
		
		User user=userRepository.findByUserId(username);
		
		System.out.print("in userdetails service"+user.getPassword());
		
		return new MyUserDetails(user);
	}

}
