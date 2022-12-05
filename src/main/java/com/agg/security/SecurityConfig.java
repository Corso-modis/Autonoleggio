package com.agg.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

<<<<<<< HEAD
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
=======

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

>>>>>>> 226b4bee0e925d3561bb6f66d3feef4444c3b097

@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class SecurityConfig {

	@Autowired
	AuthenticationConfiguration authenticationConfiguration;

	// Lui in automatico si configurera' con lo userDetailsService e la passwEncoder
	// dichiarati come beans nel resto del progetto
	@Bean
	public AuthenticationManager authenticationManager() throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public ProviderJwt providerJwt() {
		return new ProviderJwt();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

//	@Bean
//	public CorsConfigurationSource corsConfigurationSource() {
//		CorsConfiguration conf = new CorsConfiguration();
//		conf.setAllowedOrigins(Arrays.asList("http://127.0.0.1:5500"));
//		conf.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE"));
//		conf.setAllowedHeaders(Arrays.asList("*"));
//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		source.registerCorsConfiguration("/**", conf);
//		return source;
//	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		// http.cors(Customizer.withDefaults());

		CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(providerJwt(),
				authenticationManager());
		customAuthenticationFilter.setFilterProcessesUrl("/api/login");
		// decido con quale uri i client potranno fare la login

		http.addFilter(customAuthenticationFilter);

		http.addFilterBefore(new CustomAuthorizationFilter(providerJwt()), UsernamePasswordAuthenticationFilter.class);

		http.csrf().disable(); // servizi rest non sono affetti da questo tipo di attacco
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authorizeHttpRequests(authorization -> authorization
				.antMatchers(HttpMethod.POST, "/api/login", "/api/token/refresh").permitAll()
				.antMatchers(HttpMethod.GET, "/automobile/id", "/automobile/all").hasAnyAuthority("user", "admin")
				// uso hasAnyAuthority perche' in automcatico aggiunge il prefisso "ROLE_" sul
				// ruolo che indico.Con HasRole invece avrei dovuto scrivere
				// HasRole("ROLE_user").
				// Da spring 4 ci vuole il prefisso.
				.antMatchers(HttpMethod.POST, "/automobile/save").hasAuthority("admin").anyRequest().authenticated());
		return http.build();

	}
}