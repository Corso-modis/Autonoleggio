package com.agg.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Utente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id_utente;

	@Column(nullable = false)
	@Email(regexp = "/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$/", message = "Email inserita non valida")
	private String email;

	@Column(nullable = false)
	@NotNull(message = "Username inserito vuoto")
	private String username;

	@Column(nullable = false)
	@NotNull(message = "Password inserito vuoto")
	private String password;

	@Column(nullable = false)
	@Min(value = 18, message = "Devi avere almeno 18 anni per registrarti")
	private int eta;

	@Column(nullable = false, length = 9)
	@NotNull(message = "Patente inserita ")
	private String patente;

	@OneToMany(mappedBy = "utente")
	@JsonIgnore
	private Set<Noleggio> noleggi;

	@ManyToMany(mappedBy = "utenti")
	private Set<Ruolo> ruoli = new HashSet<>();

	public Utente() {
		super();
	}

	public Utente(long id_utente, String email, String username, String password, int eta, String patente) {
		super();
		this.id_utente = id_utente;
		this.email = email;
		this.username = username;
		this.password = password;
		this.eta = eta;
		this.patente = patente;
		this.ruoli.add(new Ruolo(2, "user"));
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
		return "Utente [id=" + id_utente + ", email=" + email + ", username=" + username + ", password=" + password
				+ ", eta=" + eta + ", patente=" + patente + "]";
	}
}
