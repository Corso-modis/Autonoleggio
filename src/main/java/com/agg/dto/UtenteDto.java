package com.agg.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

public class UtenteDto implements Serializable {
    private final long id;
    @Email(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,4})$", message = "Email non valida")
    private final String email;
    @NotBlank(message = "Username non puo' essere vuoto")
    private final String username;
    private final String password;
    @Min(value = 18, message = "Devi avere almeno 18 anni per registrarti")
    private final int eta;
    @NotBlank(message = "Username non puo' essere vuoto")
    @Size(min = 9, max = 9, message = "Lunghezza non rispettata")
    private final String patente;
    private final Set<NoleggioDto> noleggi;
    private final Set<RuoloDto> ruoli;

    public UtenteDto(long id, String email, String username, String password, int eta, String patente, Set<NoleggioDto> noleggi, Set<RuoloDto> ruoli) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.eta = eta;
        this.patente = patente;
        this.noleggi = noleggi;
        this.ruoli = ruoli;
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getEta() {
        return eta;
    }

    public String getPatente() {
        return patente;
    }

    public Set<NoleggioDto> getNoleggi() {
        return noleggi;
    }

    public Set<RuoloDto> getRuoli() {
        return ruoli;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UtenteDto entity = (UtenteDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.email, entity.email) &&
                Objects.equals(this.username, entity.username) &&
                Objects.equals(this.password, entity.password) &&
                Objects.equals(this.eta, entity.eta) &&
                Objects.equals(this.patente, entity.patente) &&
                Objects.equals(this.noleggi, entity.noleggi) &&
                Objects.equals(this.ruoli, entity.ruoli);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, username, password, eta, patente, noleggi, ruoli);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "email = " + email + ", " +
                "username = " + username + ", " +
                "password = " + password + ", " +
                "eta = " + eta + ", " +
                "patente = " + patente + ", " +
                "noleggi = " + noleggi + ", " +
                "ruoli = " + ruoli + ")";
    }
}
