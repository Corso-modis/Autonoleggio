package com.agg.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;

public class AutomobileDto implements Serializable {
    private final long id;
    @NotBlank(message = "La marca non può essere vuota")
    private final String marca;
    @NotBlank(message = "Il modello non può essere vuoto")
    private final String modello;
    @NotBlank(message = "La targa non può essere vuota")
    private final String targa;
    @NotBlank(message = "Il colore non può essere vuoto")
    private final String colore;
    @NotBlank(message = "L'alimentazione non può essere vuota")
    private final String alimentazione;
    @PositiveOrZero(message = " km >= 0 non rispettata ")
    private final double km;
    @Min(value = 80)
    private final int cavalli;
    private final boolean isNoleggiata;
    @NotNull(message = "La data di immatricolazione non può essere vuota")
    @PastOrPresent(message = "La macchina del tempo non esiste ancora nel nostro tempo")
    private final Timestamp dataImmatricolazione;
    @NotNull(message = "Campo categoria non può essere vuoto")
    private final CategoriaDto categoria;
    private final Set<NoleggioDto> noleggi;

    public AutomobileDto(long id, String marca, String modello, String targa, String colore, String alimentazione, double km, int cavalli, boolean isNoleggiata, Timestamp dataImmatricolazione, CategoriaDto categoria, Set<NoleggioDto> noleggi) {
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
        this.noleggi = noleggi;
    }

    public long getId() {
        return id;
    }

    public String getMarca() {
        return marca;
    }

    public String getModello() {
        return modello;
    }

    public String getTarga() {
        return targa;
    }

    public String getColore() {
        return colore;
    }

    public String getAlimentazione() {
        return alimentazione;
    }

    public double getKm() {
        return km;
    }

    public int getCavalli() {
        return cavalli;
    }

    public boolean getIsNoleggiata() {
        return isNoleggiata;
    }

    public Timestamp getDataImmatricolazione() {
        return dataImmatricolazione;
    }

    public CategoriaDto getCategoria() {
        return categoria;
    }

    public Set<NoleggioDto> getNoleggi() {
        return noleggi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AutomobileDto entity = (AutomobileDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.marca, entity.marca) &&
                Objects.equals(this.modello, entity.modello) &&
                Objects.equals(this.targa, entity.targa) &&
                Objects.equals(this.colore, entity.colore) &&
                Objects.equals(this.alimentazione, entity.alimentazione) &&
                Objects.equals(this.km, entity.km) &&
                Objects.equals(this.cavalli, entity.cavalli) &&
                Objects.equals(this.isNoleggiata, entity.isNoleggiata) &&
                Objects.equals(this.dataImmatricolazione, entity.dataImmatricolazione) &&
                Objects.equals(this.categoria, entity.categoria) &&
                Objects.equals(this.noleggi, entity.noleggi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, marca, modello, targa, colore, alimentazione, km, cavalli, isNoleggiata, dataImmatricolazione, categoria, noleggi);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "marca = " + marca + ", " +
                "modello = " + modello + ", " +
                "targa = " + targa + ", " +
                "colore = " + colore + ", " +
                "alimentazione = " + alimentazione + ", " +
                "km = " + km + ", " +
                "cavalli = " + cavalli + ", " +
                "isNoleggiata = " + isNoleggiata + ", " +
                "dataImmatricolazione = " + dataImmatricolazione + ", " +
                "categoria = " + categoria + ", " +
                "noleggi = " + noleggi + ")";
    }
}
