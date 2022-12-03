package com.agg.entities;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Automobile {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Positive(message = "id negativo")
	private long id;
	
	@Column(nullable=false)
	@NotBlank(message = "La marca non può essere vuota")
	private String marca;
	
	@Column(nullable=false)
	@NotBlank(message = "Il modello non può essere vuoto")
	private String modello;
	
	@Column(nullable=false, length=7)
	@NotBlank(message = "La targa non può essere vuota")
	private String targa;
	
	@Column(nullable=false)
	@NotBlank(message = "Il colore non può essere vuoto")
	private String colore;
	
	@Column(nullable=false)
	@NotBlank(message = "L'alimentazione non può essere vuota")
	private String alimentazione;
	
	@Column(nullable=false)
	@PositiveOrZero(message=" km >= 0 non rispettata ")
	private double km;
	
	@Column(nullable=false)
	@Min(value=80)
	private int cavalli;
	
	@Column(nullable=false)
	private boolean isNoleggiata;
	
	@Column(nullable=false)
	@NotBlank(message = "La marca non può essere vuota")
	@PastOrPresent(message= "La macchina del tempo non esiste ancora nel nostro tempo")
	private Timestamp dataImmatricolazione;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(nullable=false)
	@NotNull(message = "Campo categoria non può essere vuoto")
	private Categoria categoria;
	
	@OneToMany(mappedBy="automobile")
	@JsonIgnore
	private Set<Noleggio> noleggi;

	
	public Automobile(long id, String marca, String modello, String targa, String colore, String alimentazione,
			double km, int cavalli, Timestamp dataImmatricolazione, Categoria categoria) {
		super();
		this.id = id;
		this.marca = marca;
		this.modello = modello;
		this.targa = targa;
		this.colore = colore;
		this.alimentazione = alimentazione;
		this.km = km;
		this.cavalli = cavalli;
		this.dataImmatricolazione = dataImmatricolazione;
		this.isNoleggiata = false;
		this.categoria = categoria;
	}

	
	public Automobile() {
		super();
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getMarca() {
		return marca;
	}


	public void setMarca(String marca) {
		this.marca = marca;
	}


	public String getModello() {
		return modello;
	}


	public void setModello(String modello) {
		this.modello = modello;
	}


	public String getTarga() {
		return targa;
	}


	public void setTarga(String targa) {
		this.targa = targa;
	}


	public String getColore() {
		return colore;
	}


	public void setColore(String colore) {
		this.colore = colore;
	}


	public String getAlimentazione() {
		return alimentazione;
	}


	public void setAlimentazione(String alimentazione) {
		this.alimentazione = alimentazione;
	}


	public double getKm() {
		return km;
	}


	public void setKm(double km) {
		this.km = km;
	}


	public int getCavalli() {
		return cavalli;
	}


	public void setCavalli(int cavalli) {
		this.cavalli = cavalli;
	}


	public boolean isNoleggiata() {
		return isNoleggiata;
	}


	public void setNoleggiata(boolean isNoleggiata) {
		this.isNoleggiata = isNoleggiata;
	}


	public Timestamp getDataImmatricolazione() {
		return dataImmatricolazione;
	}


	public void setDataImmatricolazione(Timestamp dataImmatricolazione) {
		this.dataImmatricolazione = dataImmatricolazione;
	}


	public Categoria getCategoria() {
		return categoria;
	}


	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}


	public Set<Noleggio> getNoleggi() {
		return noleggi;
	}


	public void setNoleggi(Set<Noleggio> noleggi) {
		this.noleggi = noleggi;
	}


	@Override
	public String toString() {
		return "Automobile [id=" + id + ", marca=" + marca + ", modello=" + modello + ", targa=" + targa + ", colore="
				+ colore + ", alimentazione=" + alimentazione + ", km=" + km + ", cavalli=" + cavalli
				+ ", isNoleggiata=" + isNoleggiata + ", dataImmatricolazione=" + dataImmatricolazione + "]";
	}
}
