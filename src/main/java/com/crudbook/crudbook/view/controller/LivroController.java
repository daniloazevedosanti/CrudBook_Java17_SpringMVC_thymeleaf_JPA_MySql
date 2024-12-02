package com.crudbook.crudbook.view.controller;

import com.crudbook.crudbook.model.dtos.CreatedCompletedLivroDto;
import com.crudbook.crudbook.model.entity.Livro;
import com.crudbook.crudbook.controller.service.interfaces.ILivroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/livros")
public class LivroController {

    private ILivroService service;

    public LivroController(ILivroService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Livro> created(@RequestBody CreatedCompletedLivroDto createdLivroDto){
        var id = service.criar(createdLivroDto);
        return ResponseEntity.created(URI.create("/v1/livros/"+id.toString())).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> getById(@PathVariable("id") Long id){
        var livro = service.getById(id);

        if (livro.isPresent()){
            return ResponseEntity.ok(livro.get());
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Livro>>  listAll(){
        var livros = service.listar();
        return  ResponseEntity.ok(livros);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livro> updated(@RequestBody CreatedCompletedLivroDto updatedLivroDto,
                                             @PathVariable("id") Long id){

        var livro = service.atualizar(updatedLivroDto, id);
        if(livro != null){
            return ResponseEntity.ok(livro);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
