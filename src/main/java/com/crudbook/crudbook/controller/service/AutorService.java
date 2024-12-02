package com.crudbook.crudbook.controller.service;

import com.crudbook.crudbook.model.dtos.AutorDto;
import com.crudbook.crudbook.model.entity.Autor;
import com.crudbook.crudbook.controller.repository.AutorRepository;
import com.crudbook.crudbook.controller.service.interfaces.IAutorService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class AutorService implements IAutorService {

    private AutorRepository repository;

    public AutorService(AutorRepository repository) {
        this.repository = repository;
    }

    @Override
    public Integer criar(AutorDto autorDto){

        var entity = new Autor(null,
                autorDto.nome(),
                Instant.now(),
                null);

        var saved = repository.save(entity);
        return saved.getCodAu();
    }

    @Override
    public List<Autor> listar() {
        return repository.findAll();
    }

    @Override
    public Optional<Autor> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Autor atualizar(AutorDto autorDto, Long id) {
        var autorExist = repository.findById(id);
        if(autorExist.isPresent()){
            Autor autor = autorExist.get();

            autor.setNome(autorDto.nome());

            var saved = repository.save(autor);
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
