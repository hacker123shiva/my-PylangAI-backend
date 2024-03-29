package com.skyline.bert.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

 
import com.skyline.bert.security.CustomerUserDetailsService;
import com.skyline.bert.security.JwtAuthenticationEntryPoint;
import com.skyline.bert.security.JwtAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		  http
		     .csrf()
		     .disable()
		     .authorizeHttpRequests()
		     .antMatchers("/api/v1/auth/**").permitAll()
		     .antMatchers(HttpMethod.GET).permitAll()
		     .anyRequest()
		     .authenticated()
		     .and()
		     .exceptionHandling()
		     .authenticationEntryPoint(jwtAuthenticationEntryPoint)
		     .and()
		     .sessionManagement()
		     .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		     
		     http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//   auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
   auth.userDetailsService(userDetailsService).passwordEncoder(NoOpPasswordEncoder.getInstance());
	}

	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	   public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration configuration) throws Exception {
     return configuration.getAuthenticationManager();
 }
}
