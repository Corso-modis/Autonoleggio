package com.agg.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.agg.repo.NoleggioRepo;
import com.agg.entities.Noleggio;
import com.agg.service.NoleggioService;

@Service
@Transactional
public class NoleggioServiceImpl implements NoleggioService {
	private NoleggioRepo noleggioRepo;

	public NoleggioServiceImpl(NoleggioRepo noleggioRepo) {
		super();
		this.noleggioRepo = noleggioRepo;
	}

	@Override
	public Noleggio findById(long id) {
		return noleggioRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Non esiste un noleggio con id " + id));
	}

	@Override
	public void save(Noleggio noleggio) {
		noleggio.setCosto(0);
		noleggioRepo.save(noleggio);
	}

	@Override
	public List<Noleggio> findAll() {
		return noleggioRepo.findAll();
	}

	@Override
	public void update(Noleggio noleggio) {
		noleggioRepo.findById(noleggio.getId())
				.orElseThrow(() -> new IllegalArgumentException("Non esiste un noleggio con id " + noleggio.getId()));
		noleggioRepo.save(noleggio);
	}

	@Override
	public void delete(Noleggio noleggio) {
		noleggioRepo.delete(noleggio);
	}

}
