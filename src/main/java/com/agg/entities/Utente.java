package com.agg.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Utente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false)
	@Email(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,4})$", message = "Email non valida")
	private String email;

	@Column(nullable = false)
	@NotBlank(message = "Username non puo' essere vuoto")
	private String username;

	@Column(nullable = false)
//	@PasswordConstraint(message = "La password non rispetta i criteri")
	private String password;

	@Column(nullable = false)
	@Min(value = 18, message = "Devi avere almeno 18 anni per registrarti")
	private int eta;

	@Column(nullable = false, length = 9)
	@NotBlank(message = "Username non puo' essere vuoto")
	@Size(min = 9, max = 9, message = "Lunghezza non rispettata")
	private String patente;

	@OneToMany(mappedBy = "utente")
	@JsonIgnore
	private Set<Noleggio> noleggi;

	@ManyToMany(fetch = FetchType.EAGER)
//	@JoinTable(name = "ruoli_utenti", joinColumns = @JoinColumn(name = "id_ruolo"), inverseJoinColumns = @JoinColumn(name = "id_utente"))
	private Set<Ruolo> ruoli = new HashSet<>();

	public Utente() {
		super();
	}

	public Utente(long id, String email, String username, String password, int eta, String patente) {
		super();
		this.id = id;
		this.email = email;
		this.username = username;
		this.password = password;
		this.eta = eta;
		this.patente = patente;
	}

	public long getId_utente() {
		return id;
	}

	public void setId_utente(long id) {
		this.id = id;
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

	public Set<Ruolo> getRuoli() {
		return ruoli;
	}

	public void setRuoli(Set<Ruolo> ruoli) {
		this.ruoli = ruoli;
	}

	@Override
	public String toString() {
		return "Utente [id=" + id + ", email=" + email + ", username=" + username + ", password=" + password + ", eta="
				+ eta + ", patente=" + patente + "]";
	}
}
