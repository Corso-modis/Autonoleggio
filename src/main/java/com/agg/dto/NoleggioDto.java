package com.agg.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public class NoleggioDto implements Serializable {
    private final long id;
    @NotBlank(message = "La data di inizio noleggio non può essere vuota")
    private final Timestamp dataInizio;
    @NotBlank(message = "La data di fine noleggio non può essere vuota")
    private final Timestamp dataFine;
    @Positive(message = "costo >0 non rispettata")
    private final double costo;
    @NotNull(message = "Campo automobile non può essere vuoto")
    private final AutomobileDto automobile;
    @NotNull(message = "Campo utente non può essere vuota")
    private final UtenteDto utente;

    public NoleggioDto(long id, Timestamp dataInizio, Timestamp dataFine, double costo, AutomobileDto automobile, UtenteDto utente) {
        this.id = id;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.costo = costo;
        this.automobile = automobile;
        this.utente = utente;
    }

    public long getId() {
        return id;
    }

    public Timestamp getDataInizio() {
        return dataInizio;
    }

    public Timestamp getDataFine() {
        return dataFine;
    }

    public double getCosto() {
        return costo;
    }

    public AutomobileDto getAutomobile() {
        return automobile;
    }

    public UtenteDto getUtente() {
        return utente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NoleggioDto entity = (NoleggioDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.dataInizio, entity.dataInizio) &&
                Objects.equals(this.dataFine, entity.dataFine) &&
                Objects.equals(this.costo, entity.costo) &&
                Objects.equals(this.automobile, entity.automobile) &&
                Objects.equals(this.utente, entity.utente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dataInizio, dataFine, costo, automobile, utente);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "dataInizio = " + dataInizio + ", " +
                "dataFine = " + dataFine + ", " +
                "costo = " + costo + ", " +
                "automobile = " + automobile + ", " +
                "utente = " + utente + ")";
    }
}
