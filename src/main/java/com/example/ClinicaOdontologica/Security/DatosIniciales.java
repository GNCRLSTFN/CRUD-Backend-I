package com.example.ClinicaOdontologica.Security;

import com.example.ClinicaOdontologica.Entity.Usuario;
import com.example.ClinicaOdontologica.Entity.UsuarioRole;
import com.example.ClinicaOdontologica.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DatosIniciales implements ApplicationRunner {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String passSinCifrar = "admin";
        String passCifrado = bCryptPasswordEncoder.encode(passSinCifrar);
        System.out.println("Pass cifrado; " + passCifrado);
        Usuario usuario = new Usuario(UsuarioRole.ROLE_USER,  passCifrado, "gcksndk@gmail.com", "gncrl", "Gian" );
        usuarioRepository.save(usuario);
    }
}
