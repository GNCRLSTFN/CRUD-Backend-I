package com.example.ClinicaOdontologica.Repository;


import com.example.ClinicaOdontologica.Entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PacienteRepository extends JpaRepository<Paciente,Long> {

    Optional<Paciente> findByEmail(String correo);
}
