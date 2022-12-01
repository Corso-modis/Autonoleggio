package com.agg.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.agg.repo.CategoriaRepo;
import com.agg.entities.Categoria;
import com.agg.service.CategoriaService;

@Service
@Transactional
public class CategoriaServiceImpl implements CategoriaService {
	private CategoriaRepo categoriaRepo;

	public CategoriaServiceImpl(CategoriaRepo categoriaRepo) {
		super();
		this.categoriaRepo = categoriaRepo;
	}

	@Override
	public Categoria findById(long id) {
		return categoriaRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Non esiste una categoria con id " + id));
	}

	@Override
	public void save(Categoria categoria) {
		categoriaRepo.save(categoria);
	}

	@Override
	public List<Categoria> findAll() {
		return categoriaRepo.findAll();
	}

	@Override
	public void update(Categoria categoria) {
		categoriaRepo.findById(categoria.getId())
				.orElseThrow(() -> new IllegalArgumentException("Non esiste una categoria con id " + categoria.getId()));
		categoriaRepo.save(categoria);
	}

	@Override
	public void delete(Categoria categoria) {
		categoriaRepo.delete(categoria);
	}
}
