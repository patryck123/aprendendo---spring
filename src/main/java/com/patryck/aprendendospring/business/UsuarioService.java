package com.patryck.aprendendospring.business;

import com.patryck.aprendendospring.infrastructure.entity.Usuario;
import com.patryck.aprendendospring.infrastructure.exceptions.ConflictException;
import com.patryck.aprendendospring.infrastructure.exceptions.ResourceNotFoundException;
import com.patryck.aprendendospring.infrastructure.respository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario salvarUsuario(Usuario usuario) {
        if (verificaEmailExistente(usuario.getEmail())) {
            throw new ConflictException("Email ja cadastrado: " + usuario.getEmail());
        }

        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }

    public void emailexistente(String email) {
        if (verificaEmailExistente(email)) {
            throw new ConflictException("Email ja cadastrado: " + email);
        }
    }

    public boolean verificaEmailExistente(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    public Usuario buscarusuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Email nao encontrado: " + email));
    }

    public void deletaUsuarioPorEMail(String email) {
        usuarioRepository.deleteByEmail(email);
    }
}









