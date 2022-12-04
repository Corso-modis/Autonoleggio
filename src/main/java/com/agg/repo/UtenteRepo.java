package com.agg.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.agg.entities.Utente;

public interface UtenteRepo extends JpaRepository<Utente, Long>{
	public Optional<Utente> findByUsername(String username);
}
