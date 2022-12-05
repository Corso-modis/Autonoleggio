package com.agg.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

//Grazie a questa classe non dovro' implementare nessun endpoint in un mio controller mappato con /login oppure /api/login ecc...
//Perche i metodi di questa classe verranno chiamati in automatico quando qualcuno proverà a fare la login
//con quale uri il client potra' chiamare la login? 
//il metodo chiamato(setFilterProccesseUrl) ereditato da UsernamePasswordAuthenticationFilter mi consente di modificare
//l'uri della login (es: oggettoDiQuestaClasse.setFilterProccesseUrl("/api/login")
//adesso i client per fare la login dovranno chimare "/api/login". di default è "/login"
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	public ProviderJwt ProviderJwt;

	// Lui interamente usa lo UserDetailsService, quindi se voglio vedere se un
	// utente esiste nel db dovro' usare lui
	public AuthenticationManager authManager;

	public CustomAuthenticationFilter(ProviderJwt providerJwt, AuthenticationManager authManager) {
		super();
		ProviderJwt = providerJwt;
		this.authManager = authManager;
	}

	// Questo metodo viene chiamato quando si prova a fare la login
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
		return authManager.authenticate(authToken);
	}

	// Questo metodo viene chiamato se la login è andata a buon fine
	// quindi è qui che generiamo il token(e refresh token nel caso) da mandare al
	// client
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		// Utente che ha superato la login
		User user = (User) authResult.getPrincipal();
		System.err.println(user);

		String access_token = ProviderJwt.generateToken(user, request.getRequestURL().toString());
		String refresh_token = ProviderJwt.generateRefreshToken(user, request.getRequestURL().toString());

		Map<String, String> tokens = new HashMap<>();
		tokens.put("access_token", access_token);
		tokens.put("refresh_token", refresh_token);

		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		new ObjectMapper().writeValue(response.getOutputStream(), tokens);
	}

}
