package com.alura.forumalura.dto;

import jakarta.validation.constraints.NotBlank;

// Dados que chegam no corpo da requisição para criar um tópico
public record DadosCadastroTopico(
        @NotBlank String titulo,
        @NotBlank String mensagem,
        @NotBlank String nomeCurso
) {}