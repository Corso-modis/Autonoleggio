package com.agg.service.impl;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.ValidationException;

import org.springframework.stereotype.Service;

import com.agg.repo.RuoloRepo;
import com.agg.entities.Ruolo;
import com.agg.service.RuoloService;

@Service
@Transactional
public class RuoloServiceImpl implements RuoloService {
	private RuoloRepo ruoloRepo;

	public RuoloServiceImpl(@Valid RuoloRepo ruoloRepo) {
		super();
		this.ruoloRepo = ruoloRepo;
	}

	@Override
	public Ruolo findById(long id) {
		return ruoloRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Non esiste un ruolo con id " + id));
	}

	@Override
	public void save(@Valid Ruolo ruolo)  throws ValidationException{
		ruoloRepo.save(ruolo);
	}

	@Override
	public List<Ruolo> findAll() {
		return ruoloRepo.findAll();
	}

	@Override
	public void update(@Valid Ruolo ruolo) throws ValidationException {
		ruoloRepo.findById(ruolo.getId_ruolo())
		.orElseThrow(() -> new IllegalArgumentException("Non esiste un ruolo con id " + ruolo.getId_ruolo()));
		ruoloRepo.save(ruolo);
	}

	@Override
	public void delete(@Valid Ruolo ruolo) throws ValidationException {
		ruoloRepo.delete(ruolo);
	}

}
