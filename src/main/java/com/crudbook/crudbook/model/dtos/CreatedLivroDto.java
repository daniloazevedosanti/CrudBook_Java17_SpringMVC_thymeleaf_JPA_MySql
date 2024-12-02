package com.crudbook.crudbook.model.dtos;

public record CreatedLivroDto(Long codL, String titulo, String editora, Integer edicao, String anoPublicacao, Double valor, String autores, String assuntos) {
}
