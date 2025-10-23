package com.patryck.aprendendospring.business;

import com.patryck.aprendendospring.infrastructure.entity.Usuario;
import com.patryck.aprendendospring.infrastructure.exceptions.ConflictException;
import com.patryck.aprendendospring.infrastructure.exceptions.ResourceNotFoundException;
import com.patryck.aprendendospring.infrastructure.respository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;


public class UsuarioService {


    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UsuarioService salvausuario(UsuarioService usuarioService) {
        try {
            emailexistente(usuarioRepository.getEmail());
            Object Usuario = null;
            salvausuario((UsuarioService) usuarioRepository).setSenha
                    (passwordEncoder.encode(usuarioRepository.getEmail()));
            return (UsuarioService) usuarioRepository.save(usuarioRepository);

        } catch (ConflictException e) {
            throw new ConflictException("Email ja cadastrado", e.getCause());

        }

    }

    private void setSenha(String encode) {
    }


    public void emailexistente(String email) {
        try {
            boolean existe = verificaEmailExistente(email);
            if (existe) {
                throw new ConflictException("Email ja cadastrado" + email);

            }
        } catch (ConflictException e) {
            throw new ConflictException("Email ja cadastrado" + e.getCause());
        }

    }

    public boolean verificaEmailExistente(String email) {
        return usuarioRepository.existsByEmail(email);
    }

      public Usuario buscarusuarioPorEmail(String email) throws Throwable {
        return (Usuario) usuarioRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("Email nao encontrado" + email));

      }

      public  void deletaUsuarioPorEMail(String email){
      usuarioRepository.deleteByEmail(email);
      }


}









