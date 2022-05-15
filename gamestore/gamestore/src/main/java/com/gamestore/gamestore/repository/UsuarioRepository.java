package com.gamestore.gamestore.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gamestore.gamestore.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
	
	public Optional<Usuario> findByUser(String user);
	
	public List<Usuario> findAllByNameContainingIgnoreCase(String name);

}
