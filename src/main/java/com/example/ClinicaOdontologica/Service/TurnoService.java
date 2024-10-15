package com.example.ClinicaOdontologica.Service;

import com.example.ClinicaOdontologica.Entity.Turno;
import com.example.ClinicaOdontologica.Repository.TurnoRepository;
import com.example.ClinicaOdontologica.Dto.TurnoDTO;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {
    @Autowired
    private TurnoRepository turnoRepository;


    private TurnoDTO turnoATurnoDto(Turno turno){
        TurnoDTO dto= new TurnoDTO();
        dto.setId(turno.getId());
        dto.setFecha(turno.getFecha());
        dto.setOdontologoId(turno.getOdontologo().getId());
        dto.setPacienteId(turno.getPaciente().getId());
        return dto;
    }

    public TurnoDTO guardarTurno(Turno turno){
         Turno turnoGuardado= turnoRepository.save(turno);
         return turnoATurnoDto(turnoGuardado);
    }
    public void actualizarTurno(Turno turno){
        turnoRepository.save(turno);
    }
    public Optional<Turno> buscarPorId(Long id){
        return turnoRepository.findById(id);
    }
    public List<TurnoDTO> listarTurnos() {
        List<Turno> listarTurnos = turnoRepository.findAll();
        List<TurnoDTO> listaDTO = new ArrayList<>();
        for (Turno turno : listarTurnos) {
            listaDTO.add(turnoATurnoDto(turno));
        }
        return listaDTO;
    }
    public void eliminarTurno(Long id){
        turnoRepository.deleteById(id);
    }
}
