package com.example.ClinicaOdontologica.Service;

import com.example.ClinicaOdontologica.Dto.TurnoDTO;
import com.example.ClinicaOdontologica.Entity.Domicilio;
import com.example.ClinicaOdontologica.Entity.Odontologo;
import com.example.ClinicaOdontologica.Entity.Paciente;
import com.example.ClinicaOdontologica.Entity.Turno;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TurnoTestService {
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private TurnoService turnoService;
    private TurnoDTO turnoDTO;
    @Test
    @Order(1)
    public void guardarTurno(){
        Paciente paciente = pacienteService.guardarPaciente(new Paciente("Giancarlo", "Vilchez", "2410", LocalDate.of(2024,9,12), new Domicilio("Calle aleatoria", 123, "Miraflores", "Lima"), "gncrl@gmail.com"));
        Odontologo odontologo = odontologoService.guardarOdontologo(new Odontologo("Stefano", "Nuñez", "241000"));
        TurnoDTO turnoDTO = turnoService.guardarTurno(new Turno(paciente, odontologo, LocalDate.of(2024, 9, 12)));
        Assertions.assertEquals(1L, turnoDTO.getId());
    }
}
