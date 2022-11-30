package com.agg.service.impl;

import java.util.List;

import com.agg.entities.Utente;
import com.agg.repo.UtenteRepo;

public class UtenteServiceImpl {
	private UtenteRepo utenteRepo;

	public UtenteServiceImpl(UtenteRepo utenteRepo) {
		super();
		this.utenteRepo = utenteRepo;
	}

	@Override
	public Utente findById(long id) {
		return utenteRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Non esiste una categoria con id " + id));
	}

	@Override
	public void save(Utente utente) {
		utenteRepo.save(utente);

	}

	@Override
	public List<Utente> findAll() {
		return utenteRepo.findAll();
	}

	@Override
	public void update(Utente utente) {
		utenteRepo.findById(utente.getId())
				.orElseThrow(() -> new IllegalArgumentException("Non esiste una categoria con id " + id));
		utenteRepo.save(utente);
	}

	@Override
	public void delete(Utente utente) {
		utenteRepo.delete(utente);
	}

}
