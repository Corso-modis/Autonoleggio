package com.agg.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.agg.entities.Categoria;

public interface CategoriaRepo extends JpaRepository<Categoria, Long>{

}
