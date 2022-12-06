package com.agg.dto;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

public class RuoloDto implements Serializable {
    private final long id;
    @NotBlank(message = "Nome inserito non valido")
    private final String nome;
    private final Set<UtenteDto> utenti;

    public RuoloDto(long id, String nome, Set<UtenteDto> utenti) {
        this.id = id;
        this.nome = nome;
        this.utenti = utenti;
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Set<UtenteDto> getUtenti() {
        return utenti;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RuoloDto entity = (RuoloDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.nome, entity.nome) &&
                Objects.equals(this.utenti, entity.utenti);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, utenti);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "nome = " + nome + ", " +
                "utenti = " + utenti + ")";
    }
}
