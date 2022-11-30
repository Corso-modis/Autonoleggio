package com.agg.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Ruolo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id_ruolo;

	@Column(nullable = false)
	private String nome;

	@JoinTable(name = "ruoli_utenti", joinColumns = @JoinColumn(name = "id_ruolo"),
			inverseJoinColumns = @JoinColumn(name = "id_utente"))
	@JsonIgnore
	private Set<Utente> utenti;

	public Ruolo(long id_ruolo, String nome) {
		super();
		this.id_ruolo = id_ruolo;
		this.nome = nome;
	}

	public Ruolo() {
		super();
	}

	public long getId_ruolo() {
		return id_ruolo;
	}

	public void setId_ruolo(long id_ruolo) {
		this.id_ruolo = id_ruolo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Set<Utente> getUtenti() {
		return utenti;
	}

	public void setUtenti(Set<Utente> utenti) {
		this.utenti = utenti;
	}

	@Override
	public String toString() {
		return "Ruolo [id_ruolo=" + id_ruolo + ", nome=" + nome + "]";
	}
}
