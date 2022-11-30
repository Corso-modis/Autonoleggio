package com.agg.entities;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Automobile {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable=false )
	private String marca;
	
	@Column(nullable=false)
	private String modello;
	
	@Column(nullable=false, length=7)
	private String targa;
	
	@Column(nullable=false)
	private String colore;
	
	@Column(nullable=false)
	private String alimentazione;
	
	@Column(nullable=false)
	private double km;
	
	@Column(nullable=false)
	private int cavalli;
	
	@Column(nullable=false)
	private boolean isNoleggiata;
	
	@Column(nullable=false)
	private Timestamp dataImmatricolazione;
	
	@ManyToOne
	@JoinColumn(nullable=false)
	private Categoria categoria;
	
	@OneToMany(mappedBy="automobile")
	@JsonIgnore
	private Set<Noleggio> noleggi;

	
	public Automobile(long id, String marca, String modello, String targa, String colore, String alimentazione,
			double km, int cavalli, boolean isNoleggiata, Timestamp dataImmatricolazione, Categoria categoria) {
		super();
		this.id = id;
		this.marca = marca;
		this.modello = modello;
		this.targa = targa;
		this.colore = colore;
		this.alimentazione = alimentazione;
		this.km = km;
		this.cavalli = cavalli;
		this.isNoleggiata = isNoleggiata;
		this.dataImmatricolazione = dataImmatricolazione;
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
