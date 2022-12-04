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

import com.agg.entities.Utente;
import com.agg.service.UtenteService;

@CrossOrigin
@RestController
@RequestMapping("/utente")
public class UtenteController {
	private UtenteService utenteService;

	public UtenteController(UtenteService utenteService) {
		super();
		this.utenteService = utenteService;
	}

	@GetMapping("/all")
	public ResponseEntity<List<Utente>> findAll() {
		try {
			return new ResponseEntity<>(utenteService.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
		}
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<Utente> findById(@PathVariable("id") long id) {
		try {
			return new ResponseEntity<>(utenteService.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
		}
	}

	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody Utente utente) {
		try {
			utenteService.save(utente);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (ValidationException e) {
			throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT, e.getMessage());
		}
	}

	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody Utente utente) {
		try {
			utenteService.update(utente);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (ValidationException e) {
			throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT, e.getMessage());
		}
	}

	@DeleteMapping("/delete")
	public ResponseEntity<?> delete(@RequestBody Utente utente) {
		try {
			utenteService.delete(utente);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (ValidationException e) {
			throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT, e.getMessage());
		}
	}
}
