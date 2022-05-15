package com.gamestore.gamestore.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.gamestore.gamestore.model.Usuario;
import com.gamestore.gamestore.model.UsuarioLogin;
import com.gamestore.gamestore.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public Optional<Usuario> cadastrarUsuario(Usuario usuario) {

		if (usuarioRepository.findByUser(usuario.getUser())
			.isPresent())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
			"Usuário já existe!", null);

		usuario.setPassword(criptografarSenha(usuario.getPassword()));

		return Optional.of(usuarioRepository.save(usuario));
	}

	public Optional<Usuario> atualizarUsuario(Usuario usuario) {

		if (usuarioRepository.findById(usuario.getId()).isPresent()) {
			Optional<Usuario> buscaUsuario = usuarioRepository.
			findByUser(usuario.getUser());

			if (buscaUsuario.isPresent()) {				
				if (buscaUsuario.get().getId() != usuario.getId())
				  throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
					 "Usuário já existe!", null);
			}
			
			usuario.setPassword(criptografarSenha(usuario.getPassword()));

			return Optional.of(usuarioRepository.save(usuario));
		} 
			
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, 
		"Usuário não encontrado!", null);		
	}	
	
	public Optional<UsuarioLogin> logarUsuario(
		Optional<UsuarioLogin> usuarioLogin) {
		
		Optional<Usuario> usuario = usuarioRepository
		.findByUser(usuarioLogin.get().getUser());

		if (usuario.isPresent()) {
			if (compararSenhas(usuarioLogin.get().getPassword(), 
				usuario.get().getPassword())) {

				usuarioLogin.get().setId(usuario.get().getId());				
				usuarioLogin.get().setName(usuario.get().getName());
				usuarioLogin.get().setPhoto(usuario.get().getPhoto());
				usuarioLogin.get().setToken(
				gerarBasicToken(usuarioLogin.get().getUser(), 
				usuarioLogin.get().getPassword()));
				usuarioLogin.get().setPassword(usuario.get().getPassword());

				return usuarioLogin;

			}
		}		
		
		throw new ResponseStatusException(
			HttpStatus.UNAUTHORIZED, "Usuário ou senha inválidos!", null);
	}
	
	private String criptografarSenha(String senha) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String senhaEncoder = encoder.encode(senha);

		return senhaEncoder;
	}
	
	private boolean compararSenhas(String senhaDigitada, 
	String senhaBanco) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		return encoder.matches(senhaDigitada, senhaBanco);		
	}
	
	private  String  gerarBasicToken(String  email, String  password) {
		String estrutura = email + ":" + password;
		byte[] estruturaBase64 = Base64.encodeBase64(
		estrutura.getBytes(Charset.forName("US-ASCII")));
		return  "Basic " + new  String(estruturaBase64);
	}

}
