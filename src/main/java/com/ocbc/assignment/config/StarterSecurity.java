package com.ocbc.assignment.config;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//@EnableGlobalMethodSecurity(prePostEnabled = false)
@EnableWebSecurity(debug=true)
public class StarterSecurity extends WebSecurityConfigurerAdapter{
	
	private static final String ACTUATOR_HEALTH="/actuator/health";
	private static final String ACTUATOR_METRICS="actuator/metrics";
	private static final String ACTUATOR_PROMETHEUS="/actuator/prometheus";
	
	@Override
	public void configure(WebSecurity webSecurity) {
		webSecurity.ignoring().mvcMatchers(ACTUATOR_HEALTH,ACTUATOR_METRICS,ACTUATOR_PROMETHEUS);
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.requestMatchers(r -> r.mvcMatchers(ACTUATOR_HEALTH,ACTUATOR_METRICS,ACTUATOR_PROMETHEUS))
		.csrf().disable();
	}
	

}
