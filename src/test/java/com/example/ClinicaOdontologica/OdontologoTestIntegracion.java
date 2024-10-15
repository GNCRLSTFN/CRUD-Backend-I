package com.example.ClinicaOdontologica;

import com.example.ClinicaOdontologica.Entity.Odontologo;
import com.example.ClinicaOdontologica.Service.OdontologoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class OdontologoTestIntegracion {
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private MockMvc mockMvc;
    public void crearOdontologo(){
        Odontologo odontologo = odontologoService.guardarOdontologo(new Odontologo("Giancarlo", "Vilchez", "2410"));
    }
    @Test
    public void listarOdontologos() throws Exception{
        crearOdontologo();
        MvcResult respuesta = mockMvc.perform(MockMvcRequestBuilders.get("/odontologo").accept(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        Assertions.assertFalse(respuesta.getResponse().getContentAsString().isEmpty());
    }
}
