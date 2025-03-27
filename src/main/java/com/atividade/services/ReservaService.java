package com.atividade.services;

import com.atividade.domains.Atendente;
import com.atividade.domains.Reserva;
import com.atividade.domains.Reservante;
import com.atividade.domains.dtos.ReservaDTO;
import com.atividade.domains.enums.StatusReserva;
import com.atividade.domains.enums.TipoQuarto;
import com.atividade.repositories.ReservaRepository;
import com.atividade.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepo;

    @Autowired
    private AtendenteService atendenteService;

    @Autowired
    private ReservanteService reservanteService;

    public Reserva findById(UUID id){
        Optional<Reserva> obj = reservaRepo.findById(id);
        return obj.orElseThrow(()-> new ObjectNotFoundException("Reserva não encontrada! id: "+id));
    }

    public List<ReservaDTO> findAll(){

        return reservaRepo.findAll().stream().map(obj -> new ReservaDTO(obj)).collect(Collectors.toList());
    }

    private Reserva newsReserva(ReservaDTO obj){
        Atendente atendente = atendenteService.findById(obj.getAtendente());

        Reservante reservante = reservanteService.findById(obj.getReservante());

        Reserva reserva = new Reserva();
        if(obj.getId() != null){
            reserva.setId(obj.getId());
        }
        if(obj.getTipoQuarto().equals(0) || obj.getTipoQuarto().equals(2)){
            reserva.setValor(new BigDecimal("120.00"));
        }else{
            reserva.setValor(new BigDecimal("80.00"));
        }

        reserva.setAtendente(atendente);
        reserva.setReservante(reservante);
        reserva.setStatusReserva(StatusReserva.toEnum(obj.getStatusReserva()));
        reserva.setTipoQuarto(TipoQuarto.toEnum(obj.getTipoQuarto()));
        return reserva;
    }

    public Reserva create(ReservaDTO objDto){
        return reservaRepo.save(newsReserva(objDto));
    }

    public Reserva update(UUID id, ReservaDTO objDto){
        objDto.setId(id);
        Reserva oldObj = findById(id);
        oldObj = newsReserva(objDto);
        return reservaRepo.save(oldObj);
    }
}
