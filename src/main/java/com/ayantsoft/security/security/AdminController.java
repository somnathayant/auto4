package com.ayantsoft.security.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	@Autowired
	JwtUtil  JwtUtil; 
	
	@PostMapping("/")
	public ResponseEntity<?> ping(@RequestBody AuthReq authenticationReq) {
		String st=authenticationReq.getUserId();
		System.out.println(st);
		return new ResponseEntity<String>(authenticationReq.getUserId(),HttpStatus.OK);
	}
	
	//@PreAuthorize("hasRole('M')")
	@GetMapping("/getAdmin")
	public String ping2() {
		
		return "admin";
	}
	
	//@PreAuthorize("hasRole('U')")
	@GetMapping("/getUser")
	public String ping1() {
		
		return "User";
	}
	
	
	@GetMapping("/getAny")
	public String ping3() {
		
		return "user or admin";
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<?>Authenticate(@RequestBody AuthReq authenticationReq){
		try {
			authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authenticationReq.getUserId(),authenticationReq.getPassword())
				);
		}catch(Exception e) {
			
		}
		
		
		
		UserDetails userDetails=myUserDetailsService.loadUserByUsername(authenticationReq.getUserId());
		
		
		String jwt=JwtUtil.generateToken(userDetails);
		
		
		AuthRes authRes=new AuthRes(jwt);
		
			return new ResponseEntity<AuthRes>(authRes,HttpStatus.OK);
	
	}
	
	
}
