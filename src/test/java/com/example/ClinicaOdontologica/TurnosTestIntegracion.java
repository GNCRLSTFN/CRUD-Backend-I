package com.example.ClinicaOdontologica;

import com.example.ClinicaOdontologica.Dto.TurnoDTO;
import com.example.ClinicaOdontologica.Entity.Domicilio;
import com.example.ClinicaOdontologica.Entity.Odontologo;
import com.example.ClinicaOdontologica.Entity.Paciente;
import com.example.ClinicaOdontologica.Entity.Turno;
import com.example.ClinicaOdontologica.Service.OdontologoService;
import com.example.ClinicaOdontologica.Service.PacienteService;
import com.example.ClinicaOdontologica.Service.TurnoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.parameters.P;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class TurnosTestIntegracion {
    @Autowired
    private TurnoService turnoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private MockMvc mockMvc;

    public void cargarDatos(){
        Paciente paciente = pacienteService.guardarPaciente(new Paciente("Giancarlo", "Vilchez", "241099", LocalDate.of(2024, 9, 12), new Domicilio("Calle random", 123456, "Miraflores", "Lima"),"gncrl@gmail.com"));
        Odontologo odontologo = odontologoService.guardarOdontologo(new Odontologo("Stefano", "Nuñez", "321654"));
        TurnoDTO turnoDTO = turnoService.guardarTurno(new Turno(paciente, odontologo, LocalDate.of(2024,9,12)));
    }
    @Test
    public void listarTurnos() throws Exception{
        cargarDatos();
        MvcResult respuesta = mockMvc.perform(MockMvcRequestBuilders.get("/turno").accept(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        Assertions.assertFalse(respuesta.getResponse().getContentAsString().isEmpty());
    }
}
