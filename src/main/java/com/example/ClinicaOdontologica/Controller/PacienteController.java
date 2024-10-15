package com.example.ClinicaOdontologica.Controller;

import com.example.ClinicaOdontologica.Entity.Paciente;
import com.example.ClinicaOdontologica.Exception.BadRequestException;
import com.example.ClinicaOdontologica.Exception.ResourceNotFoundException;
import com.example.ClinicaOdontologica.Service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/paciente")
public class PacienteController {
    @Autowired
    private PacienteService pacienteService;


  @PostMapping
    public ResponseEntity<Paciente> guardarPaciente(@RequestBody Paciente paciente) {
        return ResponseEntity.ok(pacienteService.guardarPaciente(paciente));
  }
  @PutMapping
   public ResponseEntity<String> actualizarPaciente(@RequestBody Paciente paciente){
      Optional<Paciente> pacienteBuscado= pacienteService.buscarPorId(paciente.getId());
      if(pacienteBuscado.isPresent()){
          pacienteService.actualizarPaciente(paciente);
          return ResponseEntity.ok("Paciente actualizado con éxito");
      }else{
          return ResponseEntity.badRequest().body("Paciente no encontrado");
      }
  }
  @GetMapping("/buscar/{id}")
    public ResponseEntity<Optional<Paciente>> buscarPorId(@PathVariable Long id){
      return ResponseEntity.ok(pacienteService.buscarPorId(id));
  }
  @GetMapping("/buscar/email/{email}")
    public ResponseEntity<Optional<Paciente>> buscarPorEmail(@PathVariable String email) throws ResourceNotFoundException{
      Optional<Paciente> pacienteBuscado= pacienteService.buscarPorCorreo(email);
      if(pacienteBuscado.isPresent()){
          return ResponseEntity.ok(pacienteBuscado);
      }else{
         throw new ResourceNotFoundException("Paciente no encontrado por email");
      }
  }
  @GetMapping
    public ResponseEntity<List<Paciente>> listarTodos(){
      return ResponseEntity.ok(pacienteService.listarTodos());
  }
  @DeleteMapping("{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable Long id) throws ResourceNotFoundException{
      Optional<Paciente> pacienteBuscado = pacienteService.buscarPorId(id);
      if (pacienteBuscado.isPresent()){
          pacienteService.eliminarPaciente(id);
          return ResponseEntity.ok().body("Paciente eliminado con éxito");
      }else {
          throw new ResourceNotFoundException("Paciente no encontrado");
      }
  }
}
