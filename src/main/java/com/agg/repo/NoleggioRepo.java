package com.agg.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.agg.entities.Noleggio;

public interface NoleggioRepo extends JpaRepository<Noleggio, Long>{

}
