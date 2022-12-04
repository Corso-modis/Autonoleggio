package com.agg.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agg.security.ProviderJwt;
import com.agg.service.UtenteService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class RefreshController {
	private ProviderJwt providerJwt;
	private UtenteService utenteService;
	
	
	
	public RefreshController(ProviderJwt providerJwt, UtenteService utenteService) {
		super();
		this.providerJwt = providerJwt;
		this.utenteService = utenteService;
	}



	@PostMapping("/api/token/refresh")
	public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String authorizationHeaderValue = request.getHeader(HttpHeaders.AUTHORIZATION);
		
		if(authorizationHeaderValue != null && authorizationHeaderValue.startsWith("Bearer ")) {
			try {
				String refresh_token = authorizationHeaderValue.substring("Bearer ".length());
				String username = providerJwt.getUsernameFromToken(refresh_token);
				UserDetails userDetails = utenteService.loadUtenteByUsername(username);
				String nuovo_access_token = providerJwt.generateToken(userDetails, request.getRequestURL().toString());
				Map<String, String> tokens = new HashMap<>();
				tokens.put("access_token", nuovo_access_token);
				tokens.put("refresh_token", refresh_token);
				response.setContentType(MediaType.APPLICATION_JSON_VALUE);
				new ObjectMapper().writeValue(response.getOutputStream(), tokens);
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
			throw new RuntimeException("Non è stato fornito il refresh token");
		}
	}

}
