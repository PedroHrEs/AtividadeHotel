package com.atividade.domains.dtos;

import com.atividade.domains.Aluguel;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AluguelDTO {

    private Long id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataInicio;


    @JsonFormat (pattern = "dd/MM/yyyy")
    private LocalDate dataFim;

    @NotNull(message = "O campo Comprovante reserva não pode ser nulo")
    @NotBlank(message = "O campo Comprovante reserva não pode estar vazio")
    private String comprovanteReserva;

    @NotNull(message = "O campo valor aluguel não pode ser nulo")
    @Digits(integer = 15, fraction = 3)
    private BigDecimal valorAluguel;

    @NotNull(message = "O campo cliente é requerido")
    private Long cliente;
    private String nomeCliente;

    @NotNull(message = "O campo carro é requerido")
    private Long carro;
    private String modeloCarro;

    private int situacao;

    public AluguelDTO() {
    }

    public AluguelDTO(Aluguel aluguel) {
        this.id = aluguel.getId();
        this.dataInicio = aluguel.getDataInicio();
        this.dataFim = aluguel.getDataFim();
        this.comprovanteReserva = aluguel.getComprovanteReserva();
        this.valorAluguel = aluguel.getValorAluguel();
        this.cliente = aluguel.getCliente().getId();
        this.nomeCliente = aluguel.getCliente().getNome();
        this.carro = aluguel.getCarro().getId();
        this.modeloCarro = aluguel.getCarro().getModelo();
        this.situacao = aluguel.getSituacao().getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @JsonFormat (pattern = "dd/MM/yyyy")LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(@JsonFormat (pattern = "dd/MM/yyyy")LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public @JsonFormat (pattern = "dd/MM/yyyy")LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(@JsonFormat (pattern = "dd/MM/yyyy")LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public @NotNull(message = "O campo Comprovante reserva não pode ser nulo") @NotBlank(message = "O campo Comprovante reserva não pode estar vazio") String getComprovanteReserva() {
        return comprovanteReserva;
    }

    public void setComprovanteReserva(@NotNull(message = "O campo Comprovante reserva não pode ser nulo") @NotBlank(message = "O campo Comprovante reserva não pode estar vazio") String comprovanteReserva) {
        this.comprovanteReserva = comprovanteReserva;
    }

    public @NotNull(message = "O campo valor aluguel não pode ser nulo") @Digits(integer = 15, fraction = 3) BigDecimal getValorAluguel() {
        return valorAluguel;
    }

    public void setValorAluguel(@NotNull(message = "O campo valor aluguel não pode ser nulo") @Digits(integer = 15, fraction = 3)BigDecimal valorAluguel) {
        this.valorAluguel = valorAluguel;
    }

    public @NotNull(message = "O campo cliente é requerido") Long getCliente() {
        return cliente;
    }

    public void setCliente(@NotNull(message = "O campo cliente é requerido") Long cliente) {
        this.cliente = cliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public @NotNull(message = "O campo carro é requerido") Long getCarro() {
        return carro;
    }

    public void setCarro(@NotNull(message = "O campo carro é requerido") Long carro) {
        this.carro = carro;
    }

    public String getModeloCarro() {
        return modeloCarro;
    }

    public void setModeloCarro(String modeloCarro) {
        this.modeloCarro = modeloCarro;
    }

    public int getSituacao() {
        return situacao;
    }

    public void setSituacao(int situacao) {
        this.situacao = situacao;
    }
}
