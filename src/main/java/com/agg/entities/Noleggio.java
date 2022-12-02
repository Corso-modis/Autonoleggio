package com.agg.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Noleggio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false)
	private Timestamp dataInizio;

	@Column(nullable = false)
	private Timestamp dataFine;

	@Column(nullable = false)
	private double costo;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(nullable = false)
	private Automobile automobile;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Utente utente;

	public Noleggio(long id, Timestamp dataInizio, Timestamp dataFine, double costo, Automobile automobile,
			Utente utente) {
		super();
		this.id = id;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
		this.costo = costo;
		this.automobile = automobile;
		this.utente = utente;
	}

	public Noleggio() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Timestamp getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(Timestamp dataInizio) {
		this.dataInizio = dataInizio;
	}

	public Timestamp getDataFine() {
		return dataFine;
	}

	public void setDataFine(Timestamp dataFine) {
		this.dataFine = dataFine;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public Automobile getAutomobile() {
		return automobile;
	}

	public void setAutomobile(Automobile automobile) {
		this.automobile = automobile;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	@Override
	public String toString() {
		return "Noleggio [id=" + id + ", dataInizio=" + dataInizio + ", dataFine=" + dataFine + ", costo=" + costo
				+ "]";
	}
}
