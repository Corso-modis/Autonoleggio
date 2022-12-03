package com.agg.controller;

import java.util.List;

import javax.validation.ValidationException;

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
import org.springframework.web.server.ResponseStatusException;

import com.agg.entities.Noleggio;
import com.agg.service.AutomobileService;
import com.agg.service.NoleggioService;

@RestController
@RequestMapping("/noleggio")
public class NoleggioController {
	private NoleggioService noleggioService;
	private AutomobileService automobileService;

	public NoleggioController(NoleggioService noleggioService, AutomobileService automobileService) {
		super();
		this.noleggioService = noleggioService;
		this.automobileService = automobileService;
	}

	@GetMapping("/all")
	public ResponseEntity<List<Noleggio>> findAll() {
		try {
			return new ResponseEntity<>(noleggioService.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
		}
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<Noleggio> findById(@PathVariable("id") long id) {
		try {
			return new ResponseEntity<>(noleggioService.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
		}
	}

	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody Noleggio noleggio) {
		try {
			noleggioService.save(noleggio);
			calcoloCosto(noleggio);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (ValidationException e) {
			throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT, e.getMessage());
		}

	}

	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody Noleggio noleggio) {
		try {
			noleggioService.update(noleggio);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (ValidationException e) {
			throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT, e.getMessage());
		}
	}

	@DeleteMapping("/delete")
	public ResponseEntity<?> delete(@RequestBody Noleggio noleggio) {
		try {
			noleggioService.delete(noleggio);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (ValidationException e) {
			throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT, e.getMessage());
		}
	}

	private void calcoloCosto(Noleggio noleggio) {
		noleggio.setAutomobile(automobileService.findById(noleggio.getAutomobile().getId()));
		long d = (noleggio.getDataFine().getTime() - noleggio.getDataInizio().getTime()) / 1000 / 60 / 60 / 24;
		long prezzo = 0;
		while (d > 0) {
			if (d < 7) {
				prezzo += noleggio.getAutomobile().getCategoria().getPrezzo_giornaliero();
				d--;
			} else if (d >= 7 && d < 30) {
				prezzo += noleggio.getAutomobile().getCategoria().getPrezzo_settimanale();
				d -= 7;
			} else {
				prezzo += noleggio.getAutomobile().getCategoria().getPrezzo_mensile();
				d -= 30;
			}
		}
		noleggio.setCosto(prezzo);
		noleggioService.update(noleggio);
	}
}
