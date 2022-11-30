package com.agg.service;

import java.util.List;
import com.agg.entities.Utente;

public interface UtenteService {
	public Utente findById(long id);

	public void save(Utente utente);

	public List<Utente> findAll();

	public void update(Utente utente);

	public void delete(Utente utente);
}
