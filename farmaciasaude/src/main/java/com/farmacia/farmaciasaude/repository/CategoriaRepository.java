package com.farmacia.farmaciasaude.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.farmacia.farmaciasaude.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria,Long> {
	public List<Categoria> findAllByTipoCotainingIgnoreCase(@Param("tipo") String tipo );
}
