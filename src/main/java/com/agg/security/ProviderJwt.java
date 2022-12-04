package com.agg.security;

import java.util.Calendar;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class ProviderJwt {

	private final Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
	private final JWTVerifier verifier = JWT.require(algorithm).build();

	public String generateToken(UserDetails userAuth, String hostURL) {
		return JWT.create().withSubject(userAuth.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + 5 * 60 * 1000)) 
				// 5 minuti di durata validita
				.withIssuer(hostURL).withClaim("roles", userAuth.getAuthorities().stream()
						.map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.sign(algorithm);
	}

	public String generateRefreshToken(User userAuth, String hostURL) {
		return JWT.create().withSubject(userAuth.getUsername()).withExpiresAt(oneDurationFromNow(12))
				// 12 mesi di durata
				.withIssuer(hostURL).withClaim("roles", userAuth.getAuthorities().stream()
						.map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.sign(algorithm);
	}

	private Date oneDurationFromNow(int mesi) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, mesi);
		Date dataTraXMesi = cal.getTime();
		System.out.println(dataTraXMesi);
		return dataTraXMesi;
	}

	public String getUsernameFromToken(String token) {
		DecodedJWT decodedJWT = verifier.verify(token);
		return decodedJWT.getSubject();
	}

	public String[] getRolesFromToken(String token) {
		DecodedJWT decodedJWT = verifier.verify(token);
		return decodedJWT.getClaim("roles").asArray(String.class);
	}
}