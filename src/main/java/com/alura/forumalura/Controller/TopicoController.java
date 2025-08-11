package com.alura.forumalura.Controller;

import com.alura.forumalura.dto.DadosCadastroTopico;
import com.alura.forumalura.dto.DadosDetalhamentoTopico;
import com.alura.forumalura.model.Curso;
import com.alura.forumalura.model.Topico;
import com.alura.forumalura.model.Usuario;
import com.alura.forumalura.repository.CursoRepository;
import com.alura.forumalura.repository.TopicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroTopico dados, @AuthenticationPrincipal Usuario autor, UriComponentsBuilder uriBuilder) {
        // Busca ou cria o curso
        Curso curso = cursoRepository.findByNome(dados.nomeCurso())
                .orElseGet(() -> cursoRepository.save(new Curso(null, dados.nomeCurso())));

        // Cria o tópico associando o autor logado e o curso
        Topico topico = new Topico(); // 1. Usa o construtor vazio
        topico.setTitulo(dados.titulo()); // 2. Define os valores com os setters
        topico.setMensagem(dados.mensagem());
        topico.setAutor(autor);
        topico.setCurso(curso);
        topicoRepository.save(topico);

        // Retorna 201 Created com a localização do novo recurso
        var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoTopico(topico));
    }

    @GetMapping
    public ResponseEntity<Page<DadosDetalhamentoTopico>> listar(@PageableDefault(size = 10, sort = {"dataCriacao"}) Pageable paginacao) {
        var page = topicoRepository.findAll(paginacao).map(DadosDetalhamentoTopico::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var topico = topicoRepository.findById(id);
        if (topico.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new DadosDetalhamentoTopico(topico.get()));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizar(@PathVariable Long id, @RequestBody @Valid DadosCadastroTopico dados, @AuthenticationPrincipal Usuario autor) {
        var topico = topicoRepository.getReferenceById(id);

        // REGRA DE NEGÓCIO: Só o autor pode alterar o próprio tópico
        if (!topico.getAutor().equals(autor)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acesso negado: você não é o autor deste tópico.");
        }

        topico.setTitulo(dados.titulo());
        topico.setMensagem(dados.mensagem());
       

        return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable Long id, @AuthenticationPrincipal Usuario autor) {
        var topico = topicoRepository.findById(id);
        if (topico.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // REGRA DE NEGÓCIO: Só o autor pode deletar o próprio tópico
        if (!topico.get().getAutor().equals(autor)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acesso negado: você não é o autor deste tópico.");
        }

        topicoRepository.deleteById(id);
        return ResponseEntity.noContent().build(); // Retorna 204 No Content
    }
}
