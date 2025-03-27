package com.atividade.domains;

import com.atividade.domains.dtos.ReservanteDTO;
import com.atividade.domains.enums.TipoPessoa;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "reservante")
public class Reservante extends Pessoa{

    @JsonIgnore
    @OneToMany(mappedBy = "reservante")
    private List<Reserva> reserva = new ArrayList<>();


    public Reservante(Long id, String nome, String cpf, String email, String senha) {
        super(id, nome, cpf, email, senha);
        setDataCriacao(LocalDate.now());
        addTipoPessoa(TipoPessoa.CLIENTE);
    }

    public Reservante(){
        super();
        setDataCriacao(LocalDate.now());
        addTipoPessoa(TipoPessoa.CLIENTE);
    }
    public Reservante(ReservanteDTO obj){
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.cpf = obj.getCpf();
        this.email = obj.getEmail();
        this.senha = obj.getSenha();
        this.dataCriacao = obj.getDataCriacao();
        this.tipoPessoa = obj.getTipoPessoa().stream().map(x -> x.getId()).collect(Collectors.toSet());
        addTipoPessoa(TipoPessoa.CLIENTE);
    }

    public List<Reserva> getReserva() {
        return reserva;
    }

    public void setReserva(List<Reserva> reserva) {
        this.reserva = reserva;
    }
}
