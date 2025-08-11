package com.alura.forumalura.dto;

// Dados que chegam no corpo da requisição de login
public record DadosAutenticacao(String username, String password) {}