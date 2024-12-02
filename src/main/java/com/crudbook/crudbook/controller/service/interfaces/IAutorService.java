package com.crudbook.crudbook.controller.service.interfaces;

import com.crudbook.crudbook.model.dtos.AutorDto;
import com.crudbook.crudbook.model.entity.Autor;

import java.util.List;
import java.util.Optional;

public interface IAutorService {

    Integer criar(AutorDto autorDto);

    List<Autor> listar();

    Optional<Autor> getById(Long id);

    Autor atualizar(AutorDto autorDto, Long id);

    void deleteById(Long id);

}
