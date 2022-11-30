package com.agg.service;

import java.util.List;
import com.agg.entities.Ruolo;

public interface RuoloService {
	public Ruolo findById(long id);

	public void save(Ruolo ruolo);

	public List<Ruolo> findAll();

	public void update(Ruolo ruolo);

	public void delete(Ruolo ruolo);
}
