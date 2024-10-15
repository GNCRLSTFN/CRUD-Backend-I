package com.example.ClinicaOdontologica.Service;



import com.example.ClinicaOdontologica.Entity.Paciente;
import com.example.ClinicaOdontologica.Repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {
    @Autowired
    private PacienteRepository pacienteRepository;
    public Paciente guardarPaciente(Paciente paciente){
        return pacienteRepository.save(paciente);
    }
    public void actualizarPaciente(Paciente paciente){
        pacienteRepository.save(paciente);
    }
    public Optional<Paciente> buscarPorId(Long id){
        return pacienteRepository.findById(id);
    }
    public Optional<Paciente> buscarPorCorreo(String email){
        return pacienteRepository.findByEmail(email);
    }
    public List<Paciente> listarTodos(){
        return pacienteRepository.findAll();
    }
    public void eliminarPaciente(Long id){
        pacienteRepository.deleteById(id);
    }


   /* private iDao<Paciente> pacienteiDao;

    public PacienteService() {
        pacienteiDao= new PacienteDAOH2();
    }
    //metodos manuales CRUD
    public Paciente guardarPaciente(Paciente paciente){
        return pacienteiDao.guardar(paciente);
    }
    public Paciente buscarPorId(Integer id){
        return pacienteiDao.buscarPorId(id);
    }
    public Paciente buscarPorCorreo(String email){
        return pacienteiDao.buscarPorString(email);
    }*/
}
