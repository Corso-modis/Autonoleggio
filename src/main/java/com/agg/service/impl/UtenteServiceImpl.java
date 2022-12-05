package com.agg.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.agg.entities.Utente;
import com.agg.repo.UtenteRepo;
import com.agg.service.UtenteService;

@Service
@Transactional
public class UtenteServiceImpl implements UtenteService{
	private UtenteRepo utenteRepo;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	public UtenteServiceImpl(UtenteRepo utenteRepo) {
		super();
		this.utenteRepo = utenteRepo;
	}

	@Override
	public Utente findById(long id) {
		return utenteRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Non esiste un utente con id " + id));
	}

	@Override
	public void save(@Valid Utente utente)  throws ValidationException{
		utente.setPassword(passwordEncoder.encode(utente.getPassword()));
		utenteRepo.save(utente);

	}

	@Override
	public List<Utente> findAll() {
		return utenteRepo.findAll();
	}

	@Override
	public void update(@Valid Utente utente)  throws ValidationException{
		utente.setPassword(passwordEncoder.encode(utente.getPassword()));
		utenteRepo.findById(utente.getId_utente())
				.orElseThrow(() -> new IllegalArgumentException("Non esiste un utente con id " + utente.getId_utente()));
		utenteRepo.save(utente);
	}

	@Override
	public void delete(@Valid Utente utente)  throws ValidationException{
		utente.setPassword(passwordEncoder.encode(utente.getPassword()));
		utenteRepo.delete(utente);
	}

	@Override
	public UserDetails loadUtenteByUsername(String username) throws UsernameNotFoundException {
		Optional<Utente> utenteOpt = utenteRepo.findByUsername(username);
		Utente utente = utenteOpt.orElseThrow(() -> {
			return new UsernameNotFoundException("Utente con username "+username+" non trovato");
		});
		System.err.println(utente);
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		utente.getRuoli().forEach((ruolo) -> {
			authorities.add(new SimpleGrantedAuthority(ruolo.getNome()));
		});
		System.err.println(authorities);
		return new User(utente.getUsername(), utente.getPassword(), authorities);
	}
	
	@Override
	public Utente findByUsername(String username) {
		Optional<Utente> utenteOpt = utenteRepo.findByUsername(username);
		return utenteOpt.orElseThrow(() -> new UsernameNotFoundException("Username "+username+ "non trovata"));
	}

}
