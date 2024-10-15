package com.example.ClinicaOdontologica.Controller;

import com.example.ClinicaOdontologica.Dto.TurnoDTO;
import com.example.ClinicaOdontologica.Entity.Odontologo;
import com.example.ClinicaOdontologica.Entity.Paciente;
import com.example.ClinicaOdontologica.Entity.Turno;
import com.example.ClinicaOdontologica.Exception.BadRequestException;
import com.example.ClinicaOdontologica.Exception.ResourceNotFoundException;
import com.example.ClinicaOdontologica.Service.OdontologoService;
import com.example.ClinicaOdontologica.Service.PacienteService;
import com.example.ClinicaOdontologica.Service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turno")
public class TurnoController {
    /*
private TurnoService turnoService;
private PacienteService pacienteService;
private OdontologoService odontologoService;


public TurnoController() {
    turnoService = new TurnoService();
    pacienteService = new PacienteService();
    odontologoService = new OdontologoService();
}

@PostMapping
public ResponseEntity<Turno>registrarTurno(@RequestBody Turno turno){
    Paciente pacienteBuscado = pacienteService.buscarPorId(turno.getPaciente().getId());
    Odontologo odontologoBuscado = odontologoService.buscarPorId(turno.getOdontologo().getId());
    if (pacienteBuscado != null && odontologoBuscado != null){
        return ResponseEntity.ok(turnoService.guardarTurno(turno));
    }
    else {
        return ResponseEntity.badRequest().build();
    }
}
@GetMapping
public ResponseEntity<List<Turno>> listarTodos(){
    return ResponseEntity.ok(turnoService.listarTodos());
}
*/
    @Autowired
    private TurnoService turnoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;


    @PostMapping
    public ResponseEntity<TurnoDTO> guardarTurno(@RequestBody Turno turno) throws BadRequestException {
        Optional<Paciente> pacienteBuscado= pacienteService.buscarPorId(turno.getPaciente().getId());
        Optional<Odontologo> odontologoBuscado= odontologoService.buscarPorId(turno.getOdontologo().getId());
        if(pacienteBuscado.isPresent()&& odontologoBuscado.isPresent()){
            turno.setPaciente(pacienteBuscado.get());
            turno.setOdontologo(odontologoBuscado.get());
            return ResponseEntity.ok(turnoService.guardarTurno(turno));
        }else{
            throw new BadRequestException("Paciente u odontólogo no encontrado.");
        }
    }
    @PutMapping
    public ResponseEntity<TurnoDTO> actualizarTurno(@RequestBody Turno turno){
        Optional<Paciente> pacienteBuscado= pacienteService.buscarPorId(turno.getPaciente().getId());
        Optional<Odontologo> odontologoBuscado= odontologoService.buscarPorId(turno.getOdontologo().getId());
        if(pacienteBuscado.isPresent()&& odontologoBuscado.isPresent()){
            turno.setPaciente(pacienteBuscado.get());
            turno.setOdontologo(odontologoBuscado.get());
            return ResponseEntity.ok(turnoService.guardarTurno(turno));
        }else{
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping
    public ResponseEntity<List<TurnoDTO>> listarTurnos(){
        return ResponseEntity.ok(turnoService.listarTurnos());
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Optional<Turno>> buscarTurnoPorId(@PathVariable Long id) throws ResourceNotFoundException{
        Optional<Turno> turnoBuscado = turnoService.buscarPorId(id);
        if (turnoBuscado.isPresent()){
            return ResponseEntity.ok((turnoService.buscarPorId(id)));
        }else {
            throw new ResourceNotFoundException("Turno no encontrado");
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Long id) throws ResourceNotFoundException{
        Optional<Turno> turnoBuscado = turnoService.buscarPorId(id);
        if (turnoBuscado.isPresent()){
            turnoService.eliminarTurno(id);
            return ResponseEntity.ok().body("Turno eliminado con éxito");
        }
        else {
            throw new ResourceNotFoundException("Turno no encontrado");
        }
    }
}
