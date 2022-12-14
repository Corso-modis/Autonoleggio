package com.agg.service;

import java.util.List;
import com.agg.entities.Categoria;

public interface CategoriaService {
	public Categoria findById(long id);

	public void save(Categoria categoria);

	public List<Categoria> findAll();

	public void update(Categoria categoria);

	public void delete(Categoria categoria);

}
