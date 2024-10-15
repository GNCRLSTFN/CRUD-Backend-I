package com.example.ClinicaOdontologica.Service;

import com.example.ClinicaOdontologica.Entity.Domicilio;
import com.example.ClinicaOdontologica.Entity.Paciente;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PacienteServiceTest {
    @Autowired
    private PacienteService pacienteService;
    @Test
    @Order(1)
    public  void guardarPaciente(){
        Paciente paciente = new Paciente("Giancarlo", "Vilchez", "123456", LocalDate.of(2024, 9,12), new Domicilio("Calle siempre viva", 32, "Miraflores", "Lima"), "gncrl@gmail.com");
        Paciente pacienteGuardado = pacienteService.guardarPaciente(paciente);
        Assertions.assertEquals(1L, pacienteGuardado.getId());
    }
    @Test
    @Order(2)
    public void buscarPaciente(){
        Long id= 1L;
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPorId(id);
        Assertions.assertNotNull(pacienteBuscado.get());
    }
    @Test
    @Order(3)
    public void actualizarPaciente(){
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPorId(1L);
        if (pacienteBuscado.isPresent()){
            pacienteBuscado.get().setApellido("Mendoza");
        }
        pacienteService.actualizarPaciente(pacienteBuscado.get());
        Assertions.assertEquals("Mendoza", pacienteBuscado.get().getApellido());
    }
    @Test
    @Order(4)
    public void buscarPacientes(){
        List<Paciente> pacientes = pacienteService.listarTodos();
        Assertions.assertEquals(1, pacientes.size());
    }
    @Test
    @Order(5)
    public void eliminarPaciente(){
        pacienteService.eliminarPaciente(1L);
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPorId(1L);
        Assertions.assertFalse(pacienteBuscado.isPresent());
    }
}
