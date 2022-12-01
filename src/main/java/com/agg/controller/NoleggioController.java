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

import com.agg.entities.Noleggio;
import com.agg.service.NoleggioService;

@RestController
@RequestMapping("/noleggio")
public class NoleggioController {
	private NoleggioService noleggioService;

	public NoleggioController(NoleggioService noleggioService) {
		super();
		this.noleggioService = noleggioService;
	}

	@GetMapping("/findAll")
	public ResponseEntity<List<Noleggio>> findAll() {
		try {
			return new ResponseEntity<>(noleggioService.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
		}
	}

	@GetMapping("/findById/{id}")
	public ResponseEntity<Noleggio> findById(@PathVariable("id") long id) {
		try {
			return new ResponseEntity<>(noleggioService.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
		}
	}

	@PostMapping("/saveNoleggio")
	public ResponseEntity<?> save(@RequestBody Noleggio noleggio) {
		try {
			noleggioService.save(noleggio);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
		}
	}

	@PutMapping("/updateNoleggio")
	public ResponseEntity<?> update(@RequestBody Noleggio noleggio) {
		try {
			noleggioService.update(noleggio);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
		}
	}

	@DeleteMapping("/deleteNoleggio")
	public ResponseEntity<?> delete(@RequestBody Noleggio noleggio) {
		try {
			noleggioService.delete(noleggio);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
		}
	}
}
