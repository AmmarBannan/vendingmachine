package com.Ammar.VM.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.Ammar.VM.models.Item;
import com.Ammar.VM.models.Position;



public interface ItemRepo extends CrudRepository<Item, Long>{
	List<Item> findAll();
	Item findByName(String name);
	Optional<Item> findById(Long id);
}