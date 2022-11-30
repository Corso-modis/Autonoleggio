package com.agg.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.agg.entities.Automobile;

public interface AutomobileRepo extends JpaRepository<Automobile, Long>{
	
}
