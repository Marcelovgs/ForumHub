package com.alura.forumalura.dto;

import com.alura.forumalura.model.Topico;
import java.time.LocalDateTime;

// Dados que são enviados como resposta ao detalhar um tópico
public record DadosDetalhamentoTopico(
        Long id,
        String titulo,
        String mensagem,
        LocalDateTime dataCriacao,
        String nomeAutor,
        String nomeCurso
) {
    public DadosDetalhamentoTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getDataCriacao(),
                topico.getAutor().getUsername(), topico.getCurso().getNome());
    }
}