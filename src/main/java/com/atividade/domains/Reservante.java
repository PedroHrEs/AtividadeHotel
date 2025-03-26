package com.atividade.domains;

import com.atividade.domains.enums.TipoPessoa;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "reservante")
public class Reservante extends Pessoa{

    @JsonIgnore
    @OneToMany(mappedBy = "reservate")
    private List<Reserva> reserva = new ArrayList<>();


    public Reservante(Long id, String nome, String cpf, String email, String senha, List<Reserva> reserva) {
        super(id, nome, cpf, email, senha);
        this.reserva = reserva;
        addTipoPessoa(TipoPessoa.CLIENTE);
    }

    public Reservante(){
        super();
        addTipoPessoa(TipoPessoa.CLIENTE);
    }

    public List<Reserva> getReserva() {
        return reserva;
    }

    public void setReserva(List<Reserva> reserva) {
        this.reserva = reserva;
    }
}
