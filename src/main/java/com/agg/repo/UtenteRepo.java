package com.agg.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.agg.entities.Utente;

public interface UtenteRepo extends JpaRepository<Utente, Long>{

}
