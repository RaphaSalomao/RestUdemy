package br.com.erudio.security.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JwtConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>{

	@Autowired
	private JwtTokenProvider provider;

	public JwtConfigurer(JwtTokenProvider provider) {
		this.provider = provider;
	}
	
	public void configure(HttpSecurity httpSecurity) throws Exception {
		JwtTokenFilter customFilter = new JwtTokenFilter(provider);
		httpSecurity.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
}
