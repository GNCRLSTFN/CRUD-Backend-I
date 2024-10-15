package com.example.ClinicaOdontologica.Repository;


import com.example.ClinicaOdontologica.Entity.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OdontologoRepository extends JpaRepository<Odontologo,Long> {

    Optional<Odontologo> findByMatricula(String matricula);
}
