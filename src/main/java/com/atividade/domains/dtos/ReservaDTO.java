package com.atividade.domains.dtos;

import com.atividade.domains.Atendente;
import com.atividade.domains.Reserva;
import com.atividade.domains.Reservante;
import com.atividade.domains.enums.StatusReserva;
import com.atividade.domains.enums.TipoQuarto;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class ReservaDTO {

    private UUID id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCheckin;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCheckout;

    @Digits(integer = 15, fraction = 3)
    private BigDecimal valor;

    @NotNull(message = "O campo status é requerido")
    private Integer statusReserva;

    @NotNull(message = "O campo tipo do quarto é requerido")
    private Integer tipoQuarto;

    @NotNull(message = "O campo Atendente é requerido")
    private Long atendente;


    @NotNull(message = "O campo Reservante é requerido")
    private Long reservante;

    private String nomeAtendente;
    private String nomeReservante;

    public ReservaDTO() {
    }

    public ReservaDTO(Reserva obj) {
        this.id = obj.getId();
        this.dataCheckin = obj.getDataCheckin();
        this.dataCheckout = obj.getDataCheckout();
        this.valor = obj.getValor();
        this.statusReserva = obj.getStatusReserva().getId();
        this.tipoQuarto = obj.getTipoQuarto().getId();
        this.atendente = obj.getAtendente().getId();
        this.reservante = obj.getReservante().getId();
        this.nomeAtendente = obj.getAtendente().getNome();
        this.nomeReservante = obj.getReservante().getNome();
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

    public @Digits(integer = 15, fraction = 3) BigDecimal getValor() {
        return valor;
    }

    public void setValor(@Digits(integer = 15, fraction = 3) BigDecimal valor) {
        this.valor = valor;
    }

    public @NotNull(message = "O campo status é requerido") Integer getStatusReserva() {
        return statusReserva;
    }

    public void setStatusReserva(@NotNull(message = "O campo status é requerido") Integer statusReserva) {
        this.statusReserva = statusReserva;
    }

    public @NotNull(message = "O campo tipo do quarto é requerido") Integer getTipoQuarto() {
        return tipoQuarto;
    }

    public void setTipoQuarto(@NotNull(message = "O campo tipo do quarto é requerido") Integer tipoQuarto) {
        this.tipoQuarto = tipoQuarto;
    }

    public @NotNull(message = "O campo Atendente é requerido") Long getAtendente() {
        return atendente;
    }

    public void setAtendente(@NotNull(message = "O campo Atendente é requerido") Long atendente) {
        this.atendente = atendente;
    }

    public @NotNull(message = "O campo Reservante é requerido") Long getReservante() {
        return reservante;
    }

    public void setReservante(@NotNull(message = "O campo Reservante é requerido") Long reservante) {
        this.reservante = reservante;
    }

    public String getNomeAtendente() {
        return nomeAtendente;
    }

    public void setNomeAtendente(String nomeAtendente) {
        this.nomeAtendente = nomeAtendente;
    }

    public String getNomeReservante() {
        return nomeReservante;
    }

    public void setNomeReservante(String nomeReservante) {
        this.nomeReservante = nomeReservante;
    }
}
