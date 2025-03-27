package com.atividade.domains;

import com.atividade.domains.enums.StatusReserva;
import com.atividade.domains.enums.TipoQuarto;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "reserva")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCheckin;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCheckout;

    @Digits(integer = 15, fraction = 3)
    private BigDecimal valor;

    private StatusReserva statusReserva;
    private TipoQuarto tipoQuarto;

    @ManyToOne
    @JoinColumn(name = "idatendente")
    private Atendente atendente;

    @ManyToOne
    @JoinColumn(name = "idreservante")
    private Reservante reservante;

    public Reserva() {
    }

    public Reserva(UUID id, StatusReserva statusReserva, TipoQuarto tipoQuarto, Atendente atendente, Reservante reservante) {
        this.id = id;
        this.statusReserva = statusReserva;
        this.tipoQuarto = tipoQuarto;
        this.atendente = atendente;
        this.reservante = reservante;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getDataCheckin() {
        return dataCheckin;
    }

    public void setDataCheckin(LocalDate dataCheckin) {
        this.dataCheckin = dataCheckin;
    }

    public LocalDate getDataCheckout() {
        return dataCheckout;
    }

    public void setDataCheckout(LocalDate dataCheckout) {
        this.dataCheckout = dataCheckout;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public StatusReserva getStatusReserva() {
        return statusReserva;
    }

    public void setStatusReserva(StatusReserva statusReserva) {
        this.statusReserva = statusReserva;
    }

    public TipoQuarto getTipoQuarto() {
        return tipoQuarto;
    }

    public void setTipoQuarto(TipoQuarto tipoQuarto) {
        this.tipoQuarto = tipoQuarto;
    }

    public Atendente getAtendente() {
        return atendente;
    }

    public void setAtendente(Atendente atendente) {
        this.atendente = atendente;
    }

    public Reservante getReservante() {
        return reservante;
    }

    public void setReservante(Reservante reservante) {
        this.reservante = reservante;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Reserva reserva = (Reserva) o;
        return Objects.equals(id, reserva.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
