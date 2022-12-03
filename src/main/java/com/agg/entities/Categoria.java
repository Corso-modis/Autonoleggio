package com.agg.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Categoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable=false)
	@NotBlank(message = "Il nome non può essere vuoto")
	private String nome;
	
	@Column(nullable=false)
	@Positive(message = "Non può essere 0")
	private double prezzo_giornaliero;
	
	@Column(nullable=false)
	@Positive(message = "Non può essere 0")
	private double prezzo_settimanale;
	
	@Column(nullable=false)
	@Positive(message = "Non può essere 0")
	private double prezzo_mensile;
	
	@OneToMany(mappedBy= "categoria")
	@JsonIgnore
	private Set<Automobile> automobili;

	
	
	public Categoria(long id, String nome, double prezzo_giornaliero, double prezzo_settimanale,
			double prezzo_mensile) {
		super();
		this.id = id;
		this.nome = nome;
		this.prezzo_giornaliero = prezzo_giornaliero;
		this.prezzo_settimanale = prezzo_settimanale;
		this.prezzo_mensile = prezzo_mensile;
	}



	public Categoria() {
		super();
	}



	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public double getPrezzo_giornaliero() {
		return prezzo_giornaliero;
	}



	public void setPrezzo_giornaliero(double prezzo_giornaliero) {
		this.prezzo_giornaliero = prezzo_giornaliero;
	}



	public double getPrezzo_settimanale() {
		return prezzo_settimanale;
	}



	public void setPrezzo_settimanale(double prezzo_settimanale) {
		this.prezzo_settimanale = prezzo_settimanale;
	}



	public double getPrezzo_mensile() {
		return prezzo_mensile;
	}



	public void setPrezzo_mensile(double prezzo_mensile) {
		this.prezzo_mensile = prezzo_mensile;
	}



	public Set<Automobile> getAutomobili() {
		return automobili;
	}


	public void setAutomobili(Set<Automobile> automobili) {
		this.automobili = automobili;
	}


	@Override
	public String toString() {
		return "Categoria [id=" + id + ", nome=" + nome + ", prezzo_giornaliero=" + prezzo_giornaliero
				+ ", prezzo_settimanale=" + prezzo_settimanale + ", prezzo_mensile=" + prezzo_mensile + "]";
	}
}
