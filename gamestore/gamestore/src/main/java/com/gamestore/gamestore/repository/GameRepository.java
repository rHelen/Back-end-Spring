package com.gamestore.gamestore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gamestore.gamestore.model.Game;

@Repository
public interface GameRepository extends JpaRepository<Game,Long>{
	public List<Game> findAllByNameContainingIgnoreCase(@Param("name") String name);
}
