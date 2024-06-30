package com.ftn.modul3.zavrsni.jwd.Trotineti.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.session.RedisSessionProperties.ConfigureAction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.Arrays;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	public void configureAuthentication(
			AuthenticationManagerBuilder authenticationManagerBuilder)
			throws Exception {

		authenticationManagerBuilder
				.userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public AuthenticationTokenFilter authenticationTokenFilterBean()
			throws Exception {
		AuthenticationTokenFilter authenticationTokenFilter = new AuthenticationTokenFilter();
		authenticationTokenFilter
				.setAuthenticationManager(authenticationManagerBean());
		return authenticationTokenFilter;
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.headers().cacheControl().disable();
		httpSecurity
				.cors().and()
				.csrf().disable()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
			/*	.and()
				.authorizeRequests()
				.antMatchers(HttpMethod.POST, "/api/korisnici/auth")
					.permitAll()
				 .antMatchers(HttpMethod.GET, "/api/filmovi")
					 .permitAll()
				.anyRequest().authenticated();*/

		// Custom JWT based authentication
		httpSecurity.addFilterBefore(authenticationTokenFilterBean(),
				UsernamePasswordAuthenticationFilter.class);

	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("http://www.ScooterRental.com", "http://localhost:3000"));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS", "DELETE", "PUT"));
		configuration.setAllowedHeaders(Arrays.asList("Content-Type", "content-type", "x-requested-with", "Access-Control-Allow-Origin", "Access-Control-Allow-Headers", "x-auth-token", "x-app-id", "Origin","Accept", "X-Requested-With", "Access-Control-Request-Method", "Access-Control-Request-Headers"));
		configuration.setAllowCredentials(true);
		configuration.setExposedHeaders(Arrays.asList("Total-pages"));
		// number of seconds for how long the response to the preflight request can be cached without sending another preflight request
				configuration.setMaxAge(Long.valueOf(3600));
				
				 configuration.addAllowedHeader("Authorization"); // Dozvoli Authorization zaglavlje
			     configuration.addAllowedMethod("*");		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	/*@Configuration
	public static class WebConfig implements WebMvcConfigurer {
		@Override
		public void addCorsMappings(CorsRegistry registry) {
			/*registry.addMapping("/api/filmovi")
					.allowedOrigins("https://localhost:8080", "https://localhost:3000")
					.allowedMethods("OPTIONS", "HEAD", "GET", "PUT", "POST", "DELETE", "PATCH")
					.allowCredentials(true)
					.maxAge(3600);*/

			/*registry.addMapping("/**")
					.allowedOrigins("*")
					.allowedMethods("OPTIONS", "HEAD", "GET", "PUT", "POST", "DELETE", "PATCH")
					.allowCredentials(true)
					.maxAge(3600);
		}
	}*/
}
