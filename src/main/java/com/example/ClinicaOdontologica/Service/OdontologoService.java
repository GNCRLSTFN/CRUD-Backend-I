package com.example.ClinicaOdontologica.Service;


import com.example.ClinicaOdontologica.Entity.Odontologo;
import com.example.ClinicaOdontologica.Repository.OdontologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService {
    @Autowired
    private OdontologoRepository odontologoRepository;
    public Odontologo guardarOdontologo(Odontologo odontologo){
        return odontologoRepository.save(odontologo);
    }
    public Optional<Odontologo> buscarPorId(Long id){
        return odontologoRepository.findById(id);
    }
    public void actualizarOdontologo (Odontologo odontologo){
        odontologoRepository.save(odontologo);
    }
    public void eliminarOdontologo(Long id){
        odontologoRepository.deleteById(id);
    }
    public List<Odontologo> listarTodos(){ return odontologoRepository.findAll();}

    /*private iDao<Odontologo> odontologoiDao;

    public OdontologoService() {
        odontologoiDao = new OdontologoDAOH2();
    }
    public Odontologo guardarOdontologo(Odontologo odontologo){
        return odontologoiDao.guardar(odontologo);
    }
    public Odontologo buscarPorId(Integer id){
        return odontologoiDao.buscarPorId(id);
    }
    public void actualizarOdontologo(Odontologo odontologo){
        odontologoiDao.actualizar(odontologo);
    }
    public void eliminarOdontologo(Integer id){
        odontologoiDao.eliminar(id);
    }
    public List<Odontologo> listarTodos(){
        return odontologoiDao.buscarTodos();
    }*/
}
