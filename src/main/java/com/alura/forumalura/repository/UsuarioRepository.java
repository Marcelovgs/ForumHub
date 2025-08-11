package com.alura.forumalura.repository;

import com.alura.forumalura.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Este método será usado pelo Spring Security para buscar um usuário pelo username
    Optional<Usuario> findByUsername(String username);
}