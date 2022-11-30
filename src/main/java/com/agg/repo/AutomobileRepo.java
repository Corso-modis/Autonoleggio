package com.agg.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.agg.entities.Automobile;
import com.agg.entities.Categoria;

public interface AutomobileRepo extends JpaRepository<Automobile, Long>{
	Automobile findByTarga(String targa);
	List<Automobile> findByCategoria(Categoria categoria);
	List<Automobile> findByMarca(String marca);
	List<Automobile> findByModello(String modello);
}
