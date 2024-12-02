package com.crudbook.crudbook.controller.service;

import com.crudbook.crudbook.model.dtos.AssuntoDto;
import com.crudbook.crudbook.model.entity.Assunto;
import com.crudbook.crudbook.controller.repository.AssuntoRepository;
import com.crudbook.crudbook.controller.service.interfaces.IAssuntoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssuntoService implements IAssuntoService {

    private AssuntoRepository repository;

    public AssuntoService(AssuntoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Integer criar(AssuntoDto assuntoDto){

        var entity = new Assunto();
        entity.setDescricao(assuntoDto.descricao());

        var saved = repository.save(entity);
        return saved.getCodAs();
    }

    @Override
    public List<Assunto> listar() {
        return repository.findAll();
    }

    @Override
    public Optional<Assunto> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Assunto atualizar(AssuntoDto assuntoDto, Long id) {
        var exist = repository.findById(id);
        if(exist.isPresent()){
            Assunto assunto = exist.get();

            assunto.setDescricao(assuntoDto.descricao());

            var saved = repository.save(assunto);
            return saved;
        }

        return null;
    }

    @Override
    public void deleteById(Long id) {
        var exist = repository.existsById(id);
        if(exist){
            repository.deleteById(id);
        }
    }

}
