package com.alura.forumalura.Controller;

import com.alura.forumalura.dto.DadosAutenticacao;
import com.alura.forumalura.model.Usuario;
import com.alura.forumalura.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder; // Injeta o Bean em SecurityConfigurations

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarUsuario(@RequestBody @Valid DadosAutenticacao dados) {
        if (repository.findByUsername(dados.username()).isPresent()) {
            return ResponseEntity.badRequest().body("Erro: Username já existe.");
        }

        // Cria um novo usuário com a senha CRIPTOGRAFADA
        var novoUsuario = new Usuario();
        novoUsuario.setUsername(dados.username());
        novoUsuario.setPassword(passwordEncoder.encode(dados.password()));

        repository.save(novoUsuario);

        return ResponseEntity.ok().build();
    }
}
