package com.example.ClinicaOdontologica.Controller;


import com.example.ClinicaOdontologica.Entity.Odontologo;
import com.example.ClinicaOdontologica.Entity.Paciente;
import com.example.ClinicaOdontologica.Exception.BadRequestException;
import com.example.ClinicaOdontologica.Service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologo")
public class OdontologoController {
    @Autowired
    private OdontologoService odontologoService;
    @PostMapping
    public ResponseEntity<Odontologo> guardarOdontologo(@RequestBody Odontologo odontologo){
        return ResponseEntity.ok(odontologoService.guardarOdontologo(odontologo));
    }
    @PutMapping
    public ResponseEntity<String> actualizarOdontologo(@RequestBody Odontologo odontologo){
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorId(odontologo.getId());
        if (odontologoBuscado.isPresent()){
            odontologoService.actualizarOdontologo(odontologo);
            return ResponseEntity.ok("Odontólogo actualizado con éxito");
        }else {
            return ResponseEntity.badRequest().body("Odontólogo no encontrado");
        }
    }
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Optional<Odontologo>> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(odontologoService.buscarPorId(id));
    }
    @GetMapping
    public ResponseEntity<List<Odontologo>> listarTodos(){
        return ResponseEntity.ok(odontologoService.listarTodos());
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Long id) throws BadRequestException {
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorId(id);
        if (odontologoBuscado.isPresent()){
            odontologoService.eliminarOdontologo(id);
            return ResponseEntity.ok().body("Odontólogo eliminado con éxito");
        }
        else {
            throw new BadRequestException("Odontólogo no encontrado");
        }
    }
}
