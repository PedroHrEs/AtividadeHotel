package com.atividade.services;

import com.atividade.domains.*;
import com.atividade.domains.enums.Conservacao;
import com.atividade.domains.enums.Situacao;
import com.atividade.domains.enums.StatusReserva;
import com.atividade.domains.enums.TipoQuarto;
import com.atividade.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class DBService {

    @Autowired
    private AluguelRepository aluguelRepo;

    @Autowired
    private CarroRepository carroRepo;

    @Autowired
    private ClienteRepository clienteRepo;

    @Autowired
    private LocadoraRepository locadoraRepo;

    @Autowired
    private ReservanteRepository reservanteRepo;

    @Autowired
    private AtendenteRepository atendenteRepo;

    @Autowired
    private ReservaRepository reservaRepo;

    @Autowired
    private VeiculoRepository veiculoRepo;

    public void initDB(){

        Cliente cliente01 = new Cliente(null,"pedro", "12322", "11212", "pedro@email");
        Cliente cliente02 = new Cliente(null,"paulo", "11102", "11123", "paulo@email");

        Locadora locadora01 = new Locadora(null, "alugaveiculos", "222321");

        Carro carro01 = new Carro(null, "volks", "gol", "as1234", locadora01, Conservacao.NOVO);
        Carro carro02 = new Carro(null, "volks", "golf", "ap2125",locadora01, Conservacao.USADO);

        Aluguel aluguel01 = new Aluguel(null, "123123232", new BigDecimal("120.00"), cliente01, carro01, Situacao.ANDAMENTO);

        clienteRepo.save(cliente01);
        clienteRepo.save(cliente02);
        locadoraRepo.save(locadora01);
        carroRepo.save(carro01);
        carroRepo.save(carro02);
        aluguelRepo.save(aluguel01);

        Atendente aten01 = new Atendente(null, "Pedro", "02569095099", "pedro@email.com", "senha123");
        Reservante reser01 = new Reservante(null, "Carlos", "02569095044", "carlos@email.com", "senha123");
        Reservante reser02 = new Reservante(null, "Paulo", "8930824000", "paulo@email.com", "senha123");

        Reserva reserva01 = new Reserva(null, StatusReserva.CHECKIN, TipoQuarto.UMAPESSOA,aten01, reser01);
        Reserva reserva02 = new Reserva(null, StatusReserva.RESERVADO, TipoQuarto.CASAL,aten01, reser02);

        atendenteRepo.save(aten01);
        reservanteRepo.save(reser01);
        reservanteRepo.save(reser02);
        reservaRepo.save(reserva01);
        reservaRepo.save(reserva02);

        Veiculo veiculo01 = new Veiculo(null, "golf", LocalDate.of(2003, 12,24), 1222322.5, "pedro", "123123");

        veiculoRepo.save(veiculo01);
    }
}
