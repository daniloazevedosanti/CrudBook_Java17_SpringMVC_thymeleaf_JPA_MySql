package com.crudbook.crudbook.controller.service.interfaces;

import com.crudbook.crudbook.model.dtos.AssuntoDto;
import com.crudbook.crudbook.model.entity.Assunto;

import java.util.List;
import java.util.Optional;

public interface IAssuntoService {

    Integer criar(AssuntoDto assuntoDto);

    List<Assunto> listar();

    Optional<Assunto>  getById(Long id);

    Assunto atualizar(AssuntoDto assuntoDto, Long id);

    void deleteById(Long id);

}
