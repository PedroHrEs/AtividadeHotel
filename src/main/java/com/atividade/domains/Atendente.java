package com.atividade.domains;

import com.atividade.domains.dtos.AtendenteDTO;
import com.atividade.domains.enums.TipoPessoa;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name= "atendente")
public class Atendente extends Pessoa{

    @JsonIgnore
    @OneToMany(mappedBy = "atendente")
    private List<Reserva> reserva = new ArrayList<>();

    public Atendente(Long id, String nome, String cpf, String email, String senha) {
        super(id, nome, cpf, email, senha);
        addTipoPessoa(TipoPessoa.FUNCIONARIO);
    }

    public Atendente(){
        super();
        addTipoPessoa(TipoPessoa.FUNCIONARIO);
    }

    public Atendente(AtendenteDTO obj){
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.cpf = obj.getCpf();
        this.email = obj.getEmail();
        this.senha = obj.getSenha();
        this.dataCriacao = obj.getDataCriacao();
        this.tipoPessoa = obj.getTipoPessoa().stream().map(x -> x.getId()).collect(Collectors.toSet());
        addTipoPessoa(TipoPessoa.CLIENTE);
        addTipoPessoa(TipoPessoa.FUNCIONARIO);
    }


    public List<Reserva> getReserva() {
        return reserva;
    }

    public void setReserva(List<Reserva> reserva) {
        this.reserva = reserva;
    }
}
