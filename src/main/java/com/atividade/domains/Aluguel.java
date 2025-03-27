package com.atividade.domains;

import com.atividade.domains.dtos.AluguelDTO;
import com.atividade.domains.enums.Situacao;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "aluguel")
public class Aluguel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_aluguel")
    private Long id;

    @JsonFormat (pattern = "dd/MM/YYYY")
    private LocalDate dataInicio = LocalDate.now();


    @JsonFormat (pattern = "dd/MM/yyyy")
    private LocalDate dataFim;

    @NotNull
    @NotBlank
    @Column(unique = true)
    private String comprovanteReserva;

    @NotNull
    @Digits(integer = 15, fraction = 3)
    private BigDecimal valorAluguel;


    @ManyToOne
    @JoinColumn(name = "idcliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "idcarro")
    private Carro carro;

    @Enumerated(EnumType.ORDINAL)
    @JoinColumn(name = "situacao")
    private Situacao situacao;

    public Aluguel() {
        this.dataInicio = LocalDate.now();
    }

    public Aluguel(Long id, String comprovanteReserva, BigDecimal valorAluguel, Cliente cliente, Carro carro, Situacao situacao) {
        this.id = id;
        this.comprovanteReserva = comprovanteReserva;
        this.dataInicio = LocalDate.now();
        this.dataFim = dataFim;
        this.valorAluguel = valorAluguel;
        this.cliente = cliente;
        this.carro = carro;
        this.situacao = situacao;
    }

    public Aluguel(AluguelDTO dto) {
        this.id = dto.getId();
        this.comprovanteReserva = dto.getComprovanteReserva();
        this.dataInicio = dto.getDataInicio();
        this.dataFim = dto.getDataFim();
        this.valorAluguel = dto.getValorAluguel();
        this.cliente = new Cliente();
        this.cliente.setId(dto.getCliente());
        this.carro = new Carro();
        this.carro.setId(dto.getCarro());
        this.situacao = Situacao.toEnum(dto.getSituacao());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public String getComprovanteReserva() {
        return comprovanteReserva;
    }

    public void setComprovanteReserva(String comprovanteReserva) {
        this.comprovanteReserva = comprovanteReserva;
    }

    public BigDecimal getValorAluguel() {
        return valorAluguel;
    }

    public void setValorAluguel(BigDecimal valorAluguel) {
        this.valorAluguel = valorAluguel;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Aluguel aluguel = (Aluguel) o;
        return Objects.equals(id, aluguel.id) && Objects.equals(comprovanteReserva, aluguel.comprovanteReserva);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, comprovanteReserva);
    }
}
