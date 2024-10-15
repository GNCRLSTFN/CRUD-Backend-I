package com.example.ClinicaOdontologica.Service;

import com.example.ClinicaOdontologica.Entity.Odontologo;
import com.example.ClinicaOdontologica.Entity.Paciente;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OdontologoServiceTest {
    @Autowired
    private OdontologoService odontologoService;
    @Test
    @Order(1)
    public void guardarOdontologo(){
        Odontologo odontologo = new Odontologo("Giancarlo", "Vilchez", "MN2410");
        Odontologo odontologoGuardado = odontologoService.guardarOdontologo(odontologo);
        Assertions.assertEquals(1L, odontologoGuardado.getId());
    }
    @Test
    @Order(2)
    public void buscarOdontologo(){
        Long id = 1L;
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorId(id);
        Assertions.assertNotNull(odontologoBuscado.get());
    }
    @Test
    @Order(3)
    public void actualizarOdontologo(){
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorId(1L);
        if (odontologoBuscado.isPresent()){
            odontologoBuscado.get().setNombre("Stefano");
        }
        odontologoService.actualizarOdontologo(odontologoBuscado.get());
        Assertions.assertEquals("Stefano", odontologoBuscado.get().getNombre());
    }
    @Test
    @Order(4)
    public void buscarOdontologos(){
        List<Odontologo> odontologos = odontologoService.listarTodos();
        Assertions.assertEquals(1, odontologos.size());
    }
    @Test
    @Order(5)
    public void eliminarOdontologo(){
        odontologoService.eliminarOdontologo(1L);
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorId(1L);
        Assertions.assertFalse(odontologoBuscado.isPresent());
    }
}
