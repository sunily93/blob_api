package com.example.blob.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Autowired
	private UserDetailsService userDetailService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
			
		//1. get token
		String requestToken = request.getHeader("Authorization");
		
		//Bearer 2352234ddf
		System.out.println(requestToken);
		
		String username=null;
		
		String token=null;
		
		if(requestToken!=null && requestToken.startsWith("Bearer"))
		{
			
			token=requestToken.substring(7);
			
			try {				
				username=this.jwtTokenHelper.getUsernameFromToken(token);
			}catch (IllegalArgumentException e) {
				System.out.println("Unable to get jwt token ");
			}catch (ExpiredJwtException e) {
				System.out.println("Jwt token has expired");
			}catch (MalformedJwtException e) {
				System.out.println("invalid jwt");
			}
			
			System.out.println("user Name :-"+username   +"Token :"+ token);
			
		}else {
			System.out.println("Jwt token dose not start with Bearer");
		}
		
		//once we get the token ,now validate
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null)
		{
			UserDetails userDetails = this.userDetailService.loadUserByUsername(username);
			System.out.println(userDetails+" user details ============");
			
			if(this.jwtTokenHelper.validateToken(token, userDetails))
			{
				//shi chl rha hai
				//authenticate karna hai
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken= new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
				
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}else
			{
				System.out.println("invalid token hai");
			}
			
		}else {
			System.out.println("User name is null or context is null");
		}
		
		filterChain.doFilter(request, response);
	}

}
