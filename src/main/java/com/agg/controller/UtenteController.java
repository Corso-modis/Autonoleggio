package com.agg.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agg.entities.Utente;
import com.agg.service.UtenteService;

@RestController
@RequestMapping("/utente")
public class UtenteController {
	private UtenteService utenteService;

	public UtenteController(UtenteService utenteService) {
		super();
		this.utenteService = utenteService;
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<List<Utente>> findUtenti() {
		try {
			return new ResponseEntity<>(utenteService.findAll(), HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
		}
	}
	
	@GetMapping("/findById/{id}")
	public ResponseEntity<Utente> findUtenteById(@PathVariable ("id") long id) {
		try {
			return new ResponseEntity<>(utenteService.findById(id), HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
		}
	}
	
	@PostMapping("/saveUtente")
	public ResponseEntity<?> saveUtente(@RequestBody Utente utente) {
		try {
			utenteService.save(utente);
			return new ResponseEntity<>(HttpStatus.CREATED);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
		}
	}
	
	@PutMapping("/updateUtente")
	public ResponseEntity<?> updateUtente(@RequestBody Utente utente) {
		try {
			utenteService.update(utente);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
		}
	}
	
	@DeleteMapping("/deleteUtente")
	public ResponseEntity<?> deleteUtente(@RequestBody Utente utente) {
		try {
			utenteService.delete(utente);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
		}
	}
}
