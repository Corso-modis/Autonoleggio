package com.agg;

<<<<<<< HEAD
=======

>>>>>>> 226b4bee0e925d3561bb6f66d3feef4444c3b097
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.agg.entities.Ruolo;
import com.agg.entities.Utente;
import com.agg.service.impl.RuoloServiceImpl;
import com.agg.service.impl.UtenteServiceImpl;

@SpringBootApplication
public class AutonoleggioApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutonoleggioApplication.class, args);
	}
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Bean
	public CommandLineRunner runner(RuoloServiceImpl ruolo,
			UtenteServiceImpl utente
			) {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				Ruolo r1=new Ruolo();
				r1.setNome("admin");
				
				Ruolo r2=new Ruolo();
				r2.setNome("user");
				
				ruolo.save(r1);
				ruolo.save(r2);
				
				Utente dio=new Utente();
				dio.setEmail("KalashniRob98@yahoo.it");
				dio.setEta(24);
				dio.setPassword(passwordEncoder.encode("Grappa9_"));
				dio.setPatente("123456789");
				Set<Ruolo>ruoli= new HashSet<Ruolo>();
				ruoli.add(r1);
				ruoli.add(r2);
				dio.setRuoli(ruoli);
				
				Utente user=new Utente();
				user.setEmail("Gabrielepeperna98@gmail.com");
				user.setEta(24);
				user.setPassword(passwordEncoder.encode("Sambuca_8"));
				user.setPatente("987654321");
				Set<Ruolo>ruoli2= new HashSet<Ruolo>();
				ruoli.add(r2);
				user.setRuoli(ruoli2);
				
				utente.save(dio);
				utente.save(user);
				
			}
		};
	}

	@Bean
	public CommandLineRunner runner(RuoloServiceImpl ruolo, UtenteServiceImpl utente) {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				Ruolo r1 = new Ruolo();
				r1.setNome("admin");

				Ruolo r2 = new Ruolo();
				r2.setNome("user");
				
				ruolo.save(r1);
				ruolo.save(r2);

				Utente dio = new Utente();
				dio.setEmail("KalashniRob98@yahoo.it");
				dio.setEta(24);
				dio.setPassword("Grappaaaa9!");
				dio.setPatente("123456789");
				dio.setUsername("KalashniRob");
				Set<Ruolo> ruoli = new HashSet<Ruolo>();
				ruoli.add(r2);
				ruoli.add(r1);
				dio.setRuoli(ruoli);

				Utente user = new Utente();
				user.setEmail("Gabrielepeperna98@gmail.com");
				user.setEta(24);
				user.setPassword("Sambuca8!");
				user.setPatente("987654321");
				user.setUsername("KalashniRob2");
				Set<Ruolo> ruoli2 = new HashSet<Ruolo>();
				ruoli2.add(r2);
				user.setRuoli(ruoli2);

				utente.save(dio);
				utente.save(user);

			}
		};
	}

}
