package com.agg.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Utente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id_utente;
	
	@Column(nullable=false)
	private String email;
	
	@Column(nullable=false)
	private String username;
	
	@Column(nullable=false)
	private String password;
	
	@Column(nullable=false)
	private int eta;
	
	@Column(nullable=false, length=9)
	private String patente;
	
	@OneToMany(mappedBy="utente")
	@JsonIgnore
	private Set<Noleggio> noleggi;

	
	public Utente() {
		super();
	}
	
	public Utente(long id_utente, String email, String username, String password, int eta, String patente,
			Set<Noleggio> noleggi) {
		super();
		this.id_utente = id_utente;
		this.email = email;
		this.username = username;
		this.password = password;
		this.eta = eta;
		this.patente = patente;
		this.noleggi = noleggi;
	}
	
	
	public long getId_utente() {
		return id_utente;
	}

	public void setId_utente(long id_utente) {
		this.id_utente = id_utente;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getEta() {
		return eta;
	}

	public void setEta(int eta) {
		this.eta = eta;
	}

	public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

	public Set<Noleggio> getNoleggi() {
		return noleggi;
	}

	public void setNoleggi(Set<Noleggio> noleggi) {
		this.noleggi = noleggi;
	}

	@Override
	public String toString() {
		return "Utente [id=" + id_utente + ", email=" + email + ", username=" + username + ", password=" + password + ", eta="
				+ eta + ", patente=" + patente + "]";
	}
}
