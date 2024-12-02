package com.crudbook.crudbook.view.controller;


import com.crudbook.crudbook.model.dtos.AutorDto;
import com.crudbook.crudbook.model.entity.Autor;
import com.crudbook.crudbook.controller.service.interfaces.IAutorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/autores")
public class AutorController {

    private IAutorService service;

    public AutorController(IAutorService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Autor> created(@RequestBody AutorDto autorDto) {
        var id = service.criar(autorDto);
        return ResponseEntity.created(URI.create("/v1/autores/"+id.toString())).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Autor> getById(@PathVariable("id") Long id){
        var autor = service.getById(id);

        if (autor.isPresent()){
            return ResponseEntity.ok(autor.get());
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Autor>>  listAll(){
        var autores = service.listar();
        return  ResponseEntity.ok(autores);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Autor> updated(@RequestBody AutorDto updatedAutorDto,
                                             @PathVariable("id") Long id){
        var autor = service.atualizar(updatedAutorDto, id);
        if(autor != null){
            return ResponseEntity.ok(autor);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
