package com.ftn.modul3.zavrsni.jwd.Trotineti.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class AuthenticationTokenFilter extends UsernamePasswordAuthenticationFilter {

	@Autowired
	private TokenUtils tokenUtils;
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String authToken = httpRequest.getHeader("Authorization");
		if(authToken != null) {
			if(authToken.length() > 7)
				authToken = authToken.substring(7);
		}
		String username = tokenUtils.getUsernameFromToken(authToken);

		if (username != null
				&& SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = this.userDetailsService
					.loadUserByUsername(username);
			if (tokenUtils.validateToken(authToken, userDetails)) {
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				authentication.setDetails(
						new WebAuthenticationDetailsSource()
						.buildDetails(httpRequest));
				SecurityContextHolder.getContext().setAuthentication(
						authentication);
			}
		}
		chain.doFilter(request, response);
	}
}