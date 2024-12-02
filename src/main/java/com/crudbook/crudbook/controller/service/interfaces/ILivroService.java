package com.crudbook.crudbook.controller.service.interfaces;

import com.crudbook.crudbook.model.dtos.CreatedCompletedLivroDto;
import com.crudbook.crudbook.model.dtos.CreatedLivroDto;
import com.crudbook.crudbook.model.entity.Livro;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ILivroService {

    Integer criar(CreatedCompletedLivroDto createdLivroDto);

    List<Livro> listar();

    Optional<Livro> getById(Long id);

    Livro atualizar(CreatedCompletedLivroDto updatedLivroDto, Long id);

    void deleteById(Long id);

    ArrayList<CreatedLivroDto> reportList();

}
