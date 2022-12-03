package com.agg.service.impl;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.agg.repo.NoleggioRepo;
import com.agg.entities.Noleggio;
import com.agg.service.NoleggioService;

@Service
@Transactional
public class NoleggioServiceImpl implements NoleggioService {
	private NoleggioRepo noleggioRepo;

	public NoleggioServiceImpl(@Valid NoleggioRepo noleggioRepo) {
		super();
		this.noleggioRepo = noleggioRepo;
	}

	@Override
	public Noleggio findById(long id) {
		return noleggioRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Non esiste un noleggio con id " + id));
	}

	@Override
	public void save(@Valid Noleggio noleggio) {
		noleggioRepo.save(noleggio);
	}

	@Override
	public List<Noleggio> findAll() {
		return noleggioRepo.findAll();
	}

	@Override
	public void update(@Valid Noleggio noleggio) {
		noleggioRepo.findById(noleggio.getId())
				.orElseThrow(() -> new IllegalArgumentException("Non esiste un noleggio con id " + noleggio.getId()));
		noleggioRepo.save(noleggio);
	}

	@Override
	public void delete(@Valid Noleggio noleggio) {
		noleggioRepo.delete(noleggio);
	}

}
