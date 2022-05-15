package com.gamestore.gamestore.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gamestore.gamestore.model.Category;
import com.gamestore.gamestore.repository.CategoryRepository;

@RestController
@RequestMapping("/category")
@CrossOrigin(origins = "*",allowedHeaders ="*")
public class CategoryController {
	
	@Autowired
	private CategoryRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Category>> GetAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	@GetMapping("/{id}")
	public ResponseEntity<Category> GetById(@PathVariable Long id){
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}
	@GetMapping("/type/{type}")
	public ResponseEntity<List<Category>> GetByType (@PathVariable String type){
		return ResponseEntity.ok(repository.findAllByTypeContainingIgnoreCase(type));
	}
	@PostMapping
	public ResponseEntity<Category> post (@Valid @RequestBody Category categoria){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(categoria));
	}
	@PutMapping
	public ResponseEntity<Category> put (@Valid @RequestBody Category categoria){
		return ResponseEntity.ok(repository.save(categoria));
	}
	@DeleteMapping("/{id}")
	public void delete (@PathVariable Long id) {
		repository.deleteById(id);
	}
}
