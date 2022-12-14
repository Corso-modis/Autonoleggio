package com.agg.controller;

import java.util.List;

import javax.validation.ValidationException;

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
import org.springframework.web.server.ResponseStatusException;

import com.agg.entities.Categoria;
import com.agg.service.CategoriaService;

@CrossOrigin
@RestController
@RequestMapping("/categoria")
public class CategoriaRest {
	private CategoriaService categoriaService;

	public CategoriaRest(CategoriaService categoriaService) {
		super();
		this.categoriaService = categoriaService;
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<Categoria> findById(@PathVariable("id") long id) {
		try {
			return new ResponseEntity<>(categoriaService.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
		}
	}

	@GetMapping("/all")
	public ResponseEntity<List<Categoria>> findAll() {
		try {
			return new ResponseEntity<>(categoriaService.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
		}
	}

	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody Categoria categoria) {
		try {
			categoriaService.update(categoria);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (ValidationException e) {
			throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT, e.getMessage());
		}
	}

	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody Categoria categoria) {
		try {
			categoriaService.save(categoria);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (ValidationException e) {
			throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT, e.getMessage());
		}
	}

	@DeleteMapping("/delete")
	public ResponseEntity<?> delete(@RequestBody Categoria categoria) {
		try {
			categoriaService.delete(categoria);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (ValidationException e) {
			throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT, e.getMessage());
		}
	}
}
