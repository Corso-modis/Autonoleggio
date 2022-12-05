package com.agg.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

public class CategoriaDto implements Serializable {
    private final long id;
    @NotBlank(message = "Il nome non può essere vuoto")
    private final String nome;
    @Positive(message = "Non può essere 0")
    private final double prezzo_giornaliero;
    @Positive(message = "Non può essere 0")
    private final double prezzo_settimanale;
    @Positive(message = "Non può essere 0")
    private final double prezzo_mensile;
    private final Set<AutomobileDto> automobili;

    public CategoriaDto(long id, String nome, double prezzo_giornaliero, double prezzo_settimanale, double prezzo_mensile, Set<AutomobileDto> automobili) {
        this.id = id;
        this.nome = nome;
        this.prezzo_giornaliero = prezzo_giornaliero;
        this.prezzo_settimanale = prezzo_settimanale;
        this.prezzo_mensile = prezzo_mensile;
        this.automobili = automobili;
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getPrezzo_giornaliero() {
        return prezzo_giornaliero;
    }

    public double getPrezzo_settimanale() {
        return prezzo_settimanale;
    }

    public double getPrezzo_mensile() {
        return prezzo_mensile;
    }

    public Set<AutomobileDto> getAutomobili() {
        return automobili;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoriaDto entity = (CategoriaDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.nome, entity.nome) &&
                Objects.equals(this.prezzo_giornaliero, entity.prezzo_giornaliero) &&
                Objects.equals(this.prezzo_settimanale, entity.prezzo_settimanale) &&
                Objects.equals(this.prezzo_mensile, entity.prezzo_mensile) &&
                Objects.equals(this.automobili, entity.automobili);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, prezzo_giornaliero, prezzo_settimanale, prezzo_mensile, automobili);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "nome = " + nome + ", " +
                "prezzo_giornaliero = " + prezzo_giornaliero + ", " +
                "prezzo_settimanale = " + prezzo_settimanale + ", " +
                "prezzo_mensile = " + prezzo_mensile + ", " +
                "automobili = " + automobili + ")";
    }
}
