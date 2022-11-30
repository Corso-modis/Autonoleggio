package com.agg.service;

import java.util.List;
import com.agg.entities.Noleggio;

public interface NoleggioService {
	public Noleggio findById(long id);

	public void save(Noleggio noleggio);

	public List<Noleggio> findAll();

	public void update(Noleggio noleggio);

	public void delete(Noleggio noleggio);

}
