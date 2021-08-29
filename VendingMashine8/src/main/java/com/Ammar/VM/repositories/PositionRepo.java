package com.Ammar.VM.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.Ammar.VM.models.Position;

public interface PositionRepo extends CrudRepository<Position,Long>{
	List<Position> findAll();
	Optional<Position> findById(Long Id);
	Optional<Position> findByNumber(int number);
}

