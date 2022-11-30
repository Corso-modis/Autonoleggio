package com.agg.service.impl;

import java.util.List;

import com.agg.repo.RuoloRepo;
import com.agg.entities.Ruolo;
import com.agg.service.RuoloService;

public class RuoloServiceImpl implements RuoloService {
	private RuoloRepo ruoloRepo;

	public RuoloServiceImpl(RuoloRepo ruoloRepo) {
		super();
		this.ruoloRepo = ruoloRepo;
	}

	@Override
	public Ruolo findById(long id) {
		return ruoloRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Non esiste un ruolo con id " + id));
	}

	@Override
	public void save(Ruolo ruolo) {
		ruoloRepo.save(ruolo);
	}

	@Override
	public List<Ruolo> findAll() {
		return ruoloRepo.findAll();
	}

	@Override
	public void update(Ruolo ruolo) {
		ruoloRepo.findById(ruolo.getId_ruolo())
		.orElseThrow(() -> new IllegalArgumentException("Non esiste un ruolo con id " + ruolo.getId_ruolo()));
		ruoloRepo.save(ruolo);
	}

	@Override
	public void delete(Ruolo ruolo) {
		ruoloRepo.delete(ruolo);
	}

}
