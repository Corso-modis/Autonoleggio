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

import com.agg.entities.Automobile;
import com.agg.service.AutomobileService;

@CrossOrigin
@RestController
@RequestMapping("automobile/")
public class AutomobileRest {
	private AutomobileService automobileService;

	public AutomobileRest(AutomobileService automobileService) {
		super();
		this.automobileService = automobileService;
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<Automobile> findById(@PathVariable("id") long id) {
		try {
			return new ResponseEntity<>(automobileService.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
		}
	}

	@GetMapping("/all")
	public ResponseEntity<List<Automobile>> findAll() {
		try {
			return new ResponseEntity<>(automobileService.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
		}
	}

	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody Automobile automobile) {
		try {
			automobileService.update(automobile);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (ValidationException e) {
			throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT, e.getMessage());
		}
	}

	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody Automobile automobile) {
		try {
			automobileService.save(automobile);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (ValidationException e) {
			throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT, e.getMessage());
		}
	}

	@DeleteMapping("/delete")
	public ResponseEntity<?> delete(@RequestBody Automobile automobile) {
		try {
			automobileService.delete(automobile);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (ValidationException e) {
			throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT, e.getMessage());
		}
	}
}
