package com.agg.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomAuthorizationFilter extends OncePerRequestFilter{

	private ProviderJwt providerJwt;
	
	public CustomAuthorizationFilter(ProviderJwt providerJwt) {
		super();
		this.providerJwt = providerJwt;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if(request.getServletPath().equals("/api/login")
				|| request.getServletPath().equals("/api/token/refresh")) {
			//se la request è verso questi endpoint publici allora la lascio passare
			filterChain.doFilter(request, response);
		}else {
			// 	/admin/xxx
			String authorizationHeaderValue = request.getHeader(HttpHeaders.AUTHORIZATION);
			if(authorizationHeaderValue != null && authorizationHeaderValue.startsWith("Bearer ")) {
				try {
					String token = authorizationHeaderValue.substring("Bearer ".length());
					String username = providerJwt.getUsernameFromToken(token);
					String[] roles = providerJwt.getRolesFromToken(token);
					Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
					Stream.of(roles).forEach(role -> {
						authorities.add(new SimpleGrantedAuthority(role));
					});
					UsernamePasswordAuthenticationToken authenticationToken = 
							new UsernamePasswordAuthenticationToken(username, null,authorities);
					SecurityContextHolder.getContext().setAuthentication(authenticationToken);
					filterChain.doFilter(request, response);
				}catch(Exception ex) {
					ex.printStackTrace();
					//token non valido per vari motivi(scaduta, formato male ecc..)
					response.setHeader("errore", ex.getMessage());
					response.setStatus(HttpStatus.FORBIDDEN.value());
					response.setContentType(MediaType.APPLICATION_JSON_VALUE);
					Map<String, String> errore = new HashMap<>();
					errore.put("error", ex.getMessage());
					new ObjectMapper().writeValue(response.getOutputStream(), errore);
				}
			}else {
				//se sto richiedendo un endpoint privato senza aver messo l'header authorization,
				//lo lascio passare ma tanto verra' bloccato dopo. Perche' nella configurazione definita
				//nel securityFilterChain con il metodo hasAuthority non permettiamo l'accesso
				filterChain.doFilter(request, response);
			}
		}
	}

}
