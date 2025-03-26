package com.atividade.domains;

import com.atividade.domains.enums.TipoPessoa;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;
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

    public List<Reserva> getReserva() {
        return reserva;
    }

    public void setReserva(List<Reserva> reserva) {
        this.reserva = reserva;
    }
}
