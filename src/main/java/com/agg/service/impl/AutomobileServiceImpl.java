package com.agg.service.impl;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.ValidationException;

import org.springframework.stereotype.Service;

import com.agg.repo.AutomobileRepo;
import com.agg.entities.Automobile;
import com.agg.service.AutomobileService;
import com.agg.entities.Categoria;

@Service
@Transactional
public class AutomobileServiceImpl implements AutomobileService {
	private AutomobileRepo automobileRepo;

	public AutomobileServiceImpl(@Valid AutomobileRepo automobileRepo) throws ValidationException{
		this.automobileRepo = automobileRepo;
	}

	@Override
	public Automobile findById(long id) {
		return automobileRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Non esiste un automobile con id " + id));
	}

	@Override
	public void save(@Valid Automobile automobile) throws ValidationException{
		automobileRepo.save(automobile);
	}

	@Override
	public List<Automobile> findAll() {
		return automobileRepo.findAll();
	}

	@Override
	public void update(@Valid Automobile automobile) throws ValidationException{
		automobileRepo.findById(automobile.getId()).orElseThrow(
				() -> new IllegalArgumentException("Non esiste un automobile con id " + automobile.getId()));
		automobileRepo.save(automobile);
	}

	@Override
	public Automobile findByTarga(String targa) {
		return automobileRepo.findByTarga(targa);
	}

	@Override
	public void delete(@Valid Automobile automobile) {
		automobileRepo.delete(automobile);
	}

	@Override
	public List<Automobile> findByCategoria(@Valid Categoria categoria)  throws ValidationException{
		return automobileRepo.findByCategoria(categoria);
	}

	@Override
	public List<Automobile> findByMarca(String marca) {
		return automobileRepo.findByMarca(marca);
	}

	@Override
	public List<Automobile> findByModello(String modello) {
		return automobileRepo.findByModello(modello);
	}

}
