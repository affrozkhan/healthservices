package com.health.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
	

	@Value("${username.apppatientsui}")
	private String apppatientsuiUserName;
	
	@Value("${password.apppatientsui}")
	private String apppatientsuiPass;
	
	
	@Value("${username.appassistantsui}")
	private String appassistantsuiUserName;
	
	@Value("${password.appassistantsui}")
	private String appassistantsuiPass;
	
	@Value("${username.webui}")
	private String webuiUserName;
	
	@Value("${password.webui}")
	private String webuiPass;
	
	
	@Bean
	public UserDetailsService userDetailsService() {
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

		manager.createUser(User
				.withUsername(apppatientsuiUserName)
				.password(passwordEncoder().encode(apppatientsuiPass))
				.roles("PATIENT_APP").build());
		manager.createUser(User
				.withUsername(appassistantsuiUserName)
				.password(passwordEncoder().encode(appassistantsuiPass))
				.roles("ASSISTANT_APP").build());
		manager.createUser(User
				.withUsername("admin123")
				.password(passwordEncoder().encode("admin123"))
				.roles("ADMIN").build());
		manager.createUser(User
				.withUsername(webuiUserName)
				.password(passwordEncoder().encode(webuiPass))
				.roles("WEB_ENTRY").build());
		return manager;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	
	/*
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		 http
	        .authorizeRequests()
	           .antMatchers("/health/appassistantsui/**").hasAnyRole("ADMIN","ASSISTANT_APP")
	           .antMatchers("/health/apppatientsui/**").hasAnyRole("ADMIN","PATIENT_APP")
	           .antMatchers("/health/webui/**").hasAnyRole("ADMIN","WEB_ENTRY")
	           .anyRequest().authenticated()  .and()
	           .csrf().disable();
    }*/
	
	
	
	
	@Configuration
	@Order(1)
	public static class AssistantAppConfigurationAdapter extends WebSecurityConfigurerAdapter {

	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http.csrf().disable().antMatcher("/health/appassistantsui/**")
	            .authorizeRequests().anyRequest().hasAnyRole("ADMIN","ASSISTANT_APP")
	            .and().httpBasic().authenticationEntryPoint(authenticationEntryPoint());
	    }

	    @Bean
	    public AuthenticationEntryPoint authenticationEntryPoint(){
	        BasicAuthenticationEntryPoint entryPoint = 
	          new BasicAuthenticationEntryPoint();
	        entryPoint.setRealmName("ASSISTANT APP");
	        return entryPoint;
	    }
	}
	
	
	@Configuration
	@Order(2)
	public static class PatientAppConfigurationAdapter extends WebSecurityConfigurerAdapter {

	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	    	 http.csrf().disable().antMatcher("/health/apppatientsui/**")
	            .authorizeRequests().anyRequest().hasAnyRole("ADMIN","PATIENT_APP")
	            .and().httpBasic().authenticationEntryPoint(authenticationEntryPoint());
	      }

	    @Bean
	    public AuthenticationEntryPoint authenticationEntryPoint(){
	        BasicAuthenticationEntryPoint entryPoint = 
	          new BasicAuthenticationEntryPoint();
	        entryPoint.setRealmName("PATIENT APP");
	        return entryPoint;
	    }
	}
	

	
	@Configuration
	@Order(3)
	public static class WebDataEntryAdapter extends WebSecurityConfigurerAdapter {

	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	    	 http.csrf().disable().antMatcher("/health/webui/**")
	            .authorizeRequests().anyRequest().hasAnyRole("ADMIN","WEB_ENTRY")
	            .and().httpBasic().authenticationEntryPoint(authenticationEntryPoint());
	    }

	    @Bean
	    public AuthenticationEntryPoint authenticationEntryPoint(){
	        BasicAuthenticationEntryPoint entryPoint = 
	          new BasicAuthenticationEntryPoint();
	        entryPoint.setRealmName("ENTRY WEB");
	        return entryPoint;
	    }
	}
	
}

