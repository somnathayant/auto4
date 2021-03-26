package com.ayantsoft.security.security;

import org.apache.coyote.http11.Http11AprProtocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class Security extends WebSecurityConfigurerAdapter{

	@Autowired
	MyUserDetailsService  userDetailsService;
	
	
	@Autowired
	private JwtFilter jwtFilter;
	
	protected void configure(AuthenticationManagerBuilder auth)throws Exception {
		/*auth.inMemoryAuthentication()
		.withUser("somnath")
		.password("{noop}som@123")
		.roles("USER")
		.and()
		.withUser("somnath1")
		.password("{noop}som@")
		.roles("ADMIN");*/
		
		auth.userDetailsService(userDetailsService).passwordEncoder(encodePWD());
		
	}	
	
	/*@Bean
	public PasswordEncoder getPasswordEncoder() {
		
		return NoOpPasswordEncoder.getInstance();
	}*/
	
	@Bean
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}
	
	@Override
	protected void configure(HttpSecurity http)throws Exception {
		/*http.httpBasic().and()
		
		.authorizeRequests()
		//.antMatchers("/authenticate").permitAll()
		
		.anyRequest().authenticated()
		.antMatchers("/getUser").access("hasRole('ROLE_USER')")
		.antMatchers("/getAdmin").access("hasRole('ROLE_ADMIN')")
		.antMatchers("/getAny").access("hasAnyRole('ROLE_USER','ADMIN')")
		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		*/
		/*.and()
		.formLogin();*/
		
		http.csrf().disable()
		.authorizeRequests().antMatchers("/authenticate").permitAll()
		.and().authorizeRequests().antMatchers("/").permitAll()
		.antMatchers("/getUser").access("hasRole('ROLE_U')")
		.antMatchers("/getAdmin").access("hasRole('ROLE_M')")
		.anyRequest().authenticated();
		http.addFilterBefore(jwtFilter,UsernamePasswordAuthenticationFilter.class);
		
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}	
	
	
}
