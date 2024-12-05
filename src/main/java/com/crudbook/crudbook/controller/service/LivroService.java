package com.crudbook.crudbook.controller.service;

import com.crudbook.crudbook.controller.repository.AssuntoRepository;
import com.crudbook.crudbook.controller.repository.AutorRepository;
import com.crudbook.crudbook.model.dtos.CreatedCompletedLivroDto;
import com.crudbook.crudbook.model.dtos.CreatedLivroDto;
import com.crudbook.crudbook.model.entity.Assunto;
import com.crudbook.crudbook.model.entity.Autor;
import com.crudbook.crudbook.model.entity.Livro;
import com.crudbook.crudbook.controller.repository.LivroRepository;
import com.crudbook.crudbook.controller.service.interfaces.ILivroService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LivroService implements ILivroService {

    private LivroRepository repository;
    private AutorRepository autorRepository;
    private AssuntoRepository assuntoRepository;

    public LivroService(LivroRepository repository, AutorRepository autorRepository, AssuntoRepository assuntoRepository) {
        this.repository = repository;
        this.autorRepository = autorRepository;
        this.assuntoRepository = assuntoRepository;
    }


    public Integer criar(CreatedCompletedLivroDto createdLivroDto) {

        var livro = new Livro(null,
                createdLivroDto.titulo(),
                createdLivroDto.editora(),
                createdLivroDto.edicao(),
                createdLivroDto.valor(),
                createdLivroDto.anoPublicacao(),
                Instant.now(),
                null, null, null);

        var autor = new Autor();
        if(createdLivroDto.autores() != null){
            for (var item : createdLivroDto.autores()) {
                autor = new Autor(null, item.getNome(), null, null);
                if (item.getCodAu() != null) {
                    autor.setCodAu(item.getCodAu());
                }
                var objAutor = autorRepository.save(autor);
                autor = new Autor();
            }
            livro.setAutores(createdLivroDto.autores());
        }

        var assunto = new Assunto();
        if(createdLivroDto.assuntos() != null){
            for (var item : createdLivroDto.assuntos()) {
                assunto = new Assunto(null, item.getDescricao(), null);
                if (item.getCodAs() != null) {
                    assunto.setCodAs(item.getCodAs());
                }
                var objAssunto = assuntoRepository.save(assunto);
                assunto = new Assunto();
            }
            livro.setAssuntos(createdLivroDto.assuntos());
        }

        var objLivro = repository.save(livro);

        return objLivro.getCodL();
    }

    @Override
    public List<Livro> listar() {
        return repository.findAll();
    }

    @Override
    public ArrayList<CreatedLivroDto>  reportList() {
        var list = repository.findAll();

        StringBuilder autores = new StringBuilder();
        StringBuilder assuntos = new StringBuilder();
        ArrayList<CreatedLivroDto> createdLivroDtos = null;
        createdLivroDtos = new ArrayList<CreatedLivroDto>();
        for(var item : list){
            for(var autor : item.getAutores()){
                autores.append(autor.getNome()).append(",");
            }

            for(var assunto : item.getAssuntos()){
                assuntos.append(assunto.getDescricao()).append(",");
            }

            CreatedLivroDto dto = new CreatedLivroDto(item.getCodL().longValue(), item.getTitulo(), item.getEditora(),
                                    item.getEdicao(), item.getAnoPublicacao(), item.getValor(),
                    autores.toString() != "" ? autores.toString().substring(0, autores.toString().length() - 1) : null,
                    assuntos.toString() != "" ? assuntos.toString().substring(0, assuntos.toString().length() - 1): null);

            createdLivroDtos.add(dto);
            autores = new StringBuilder();
            assuntos = new StringBuilder();

        }

        return createdLivroDtos;
    }

    public void addStrings(String strArray, String str){

        strArray = String.join(", ", str);
    }


    @Override
    public Optional<Livro> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Livro atualizar(CreatedCompletedLivroDto updatedLivroDto, Long id) {
        var livroExist = repository.findById(id);
        if (livroExist.isPresent()) {
            Livro livro = livroExist.get();

            livro.setTitulo(updatedLivroDto.titulo());
            livro.setEditora(updatedLivroDto.editora());
            livro.setEdicao(updatedLivroDto.edicao());
            livro.setValor(updatedLivroDto.valor());
            livro.setAnoPublicacao(updatedLivroDto.anoPublicacao());

            var autor = new Autor();
            for (var item : updatedLivroDto.autores()) {
                autor = new Autor(null, item.getNome(), null, null);
                if (item.getCodAu() != null) {
                    autor.setCodAu(item.getCodAu());
                }
                var objAutor = autorRepository.save(autor);
                autor = new Autor();
            }

            var assunto = new Assunto();
            for (var item : updatedLivroDto.assuntos()) {
                assunto = new Assunto(null, item.getDescricao(), null);
                if (item.getCodAs() != null) {
                    assunto.setCodAs(item.getCodAs());
                }
                var objAssunto = assuntoRepository.save(assunto);
                assunto = new Assunto();
            }

            livro.setAutores(updatedLivroDto.autores());
            livro.setAssuntos(updatedLivroDto.assuntos());

            var saved = repository.save(livro);
            return saved;
        }

        return null;
    }


    @Override
    public void deleteById(Long id) {
        var exist = repository.existsById(id);
        if (exist) {
            repository.deleteById(id);
        }
    }

}
