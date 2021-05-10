package com.health.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@Configuration
@EnableWebSecurity

public class SecurityConfig extends WebSecurityConfigurerAdapter
{
	

	@Value("${appUserName}")
	private String appUserName;
	
	@Value("${appPassword}")
	private String appPassword;
	
	@Value("${webUserName}")
	private String webUserName;
	
	@Value("${webPassword}")
	private String webPassword;
	
	@Value("${adminUserName}")
	private String adminUserName;
	
	@Value("${adminPassword}")
	private String adminPassword;
	
	
	@Bean
	public UserDetailsService userDetailsService() {
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

		manager.createUser(User
				.withUsername(adminUserName)
				.password(passwordEncoder().encode(adminPassword))
				.roles("ADMIN").build());
		manager.createUser(User
				.withUsername(appUserName)
				.password(passwordEncoder().encode(appPassword))
				.roles("APP_ENTRY").build());
		manager.createUser(User
				.withUsername(webUserName)
				.password(passwordEncoder().encode(webPassword))
				.roles("WEB_ENTRY").build());
		return manager;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	
	
	
	
	
	
	@Configuration
	@Order(1)
	public static class AppConfigurationAdapter extends WebSecurityConfigurerAdapter {

	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	    	 http.csrf().disable().cors().disable().antMatcher("/health/appui/**")
	            .authorizeRequests().anyRequest().hasAnyRole("ADMIN","APP_ENTRY")
	            .and().httpBasic().authenticationEntryPoint(authenticationEntryPoint());
	      }
	    
	    @Bean
	    protected CorsConfigurationSource corsConfigurationSource() {
	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        CorsConfiguration config = new CorsConfiguration();
	        config.setAllowCredentials(true);
	        //config.addAllowedOrigin(corsDomainAllowance); //see comments above.
	        config.addAllowedHeader("*");
	        config.addAllowedMethod("*");
	        source.registerCorsConfiguration("/**", config);
	        return source;
	    }

	    @Bean
	    public AuthenticationEntryPoint authenticationEntryPoint(){
	        BasicAuthenticationEntryPoint entryPoint = 
	          new BasicAuthenticationEntryPoint();
	        entryPoint.setRealmName("APP GATEWAY");
	        return entryPoint;
	    }
	}
	

	
	@Configuration
	@Order(3)
	public static class WebDataEntryAdapter extends WebSecurityConfigurerAdapter {

	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	    	 http.csrf().disable().cors().disable().antMatcher("/health/webui/**")
	            .authorizeRequests().anyRequest().hasAnyRole("ADMIN","WEB_ENTRY")
	            .and().httpBasic().authenticationEntryPoint(authenticationEntryPoint());
	    }
	    
	    @Bean
	    protected CorsConfigurationSource corsConfigurationSource() {
	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        CorsConfiguration config = new CorsConfiguration();
	        config.setAllowCredentials(true);
	        config.addAllowedHeader("*");
	        config.addAllowedMethod("*");
	        source.registerCorsConfiguration("/**", config);
	        return source;
	    }

	    @Bean
	    public AuthenticationEntryPoint authenticationEntryPoint(){
	        BasicAuthenticationEntryPoint entryPoint = 
	          new BasicAuthenticationEntryPoint();
	        entryPoint.setRealmName("WEB GATEWAY");
	        return entryPoint;
	    }
	}
	
}

