package com.alura.forumalura.Controller;

import com.alura.forumalura.dto.DadosAutenticacao;
import com.alura.forumalura.dto.DadosTokenJWT;
import com.alura.forumalura.model.Usuario;
import com.alura.forumalura.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager; // Objeto do próprio Spring que executa a lógica de autenticação

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
        // 1. Cria um objeto para representar as credenciais (username/password)
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.username(), dados.password());

        // 2. O Spring Security usa o seu AutenticacaoService para verificar se o usuário existe e a senha bate
        var authentication = manager.authenticate(authenticationToken);

        // 3. Se a autenticação for bem-sucedida, gera o token
        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        // 4. Retorna o token em uma resposta 200 OK
        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }
}