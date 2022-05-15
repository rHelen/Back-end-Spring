package com.farmacia.farmaciasaude.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.farmacia.farmaciasaude.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto,Long>{
	
	public List <Produto> findAllByNomeContainingIgnoreCase(@Param("nome") String nome );
}
