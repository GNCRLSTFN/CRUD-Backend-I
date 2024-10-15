package com.example.ClinicaOdontologica.Repository;


import com.example.ClinicaOdontologica.Entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurnoRepository extends JpaRepository<Turno,Long> {
}
