package com.crudbook.crudbook.model.dtos;

import com.crudbook.crudbook.model.entity.Assunto;
import com.crudbook.crudbook.model.entity.Autor;

import java.util.List;

public record CreatedCompletedLivroDto(String titulo, String editora, Integer edicao, String anoPublicacao, Double valor, List<Autor> autores, List<Assunto> assuntos) {
}
