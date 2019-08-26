package com.sandbox.data;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sandbox.domain.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long>{
	public Vehicle findByName(String name);
} 