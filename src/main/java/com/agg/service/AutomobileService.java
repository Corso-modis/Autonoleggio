package com.agg.service;

import java.util.List;

import com.agg.entities.Automobile;
import com.agg.entities.Categoria;

public interface AutomobileService {
	public Automobile findById(long id);

	public void save(Automobile automobile);

	public List<Automobile> findAll();

	public void update(Automobile automobile);

	public Automobile findByTarga(String targa);

	public void delete(Automobile automobile);

	public List<Automobile> findByCategoria(Categoria categoria);

	List<Automobile> findByMarca(String marca);

	List<Automobile> findByModello(String modello);

}
