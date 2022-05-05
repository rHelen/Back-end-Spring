package com.gamestore.gamestore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gamestore.gamestore.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long>{
	public List<Category> findAllByTypeContainingIgnoreCase(@Param("type") String type);
}
