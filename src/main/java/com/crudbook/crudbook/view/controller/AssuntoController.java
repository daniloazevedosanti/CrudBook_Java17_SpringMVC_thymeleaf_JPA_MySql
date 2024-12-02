package com.crudbook.crudbook.view.controller;

import com.crudbook.crudbook.model.dtos.AssuntoDto;
import com.crudbook.crudbook.model.entity.Assunto;
import com.crudbook.crudbook.controller.service.interfaces.IAssuntoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/assuntos")
public class AssuntoController {

    private IAssuntoService service;

    public AssuntoController(IAssuntoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Assunto> created(@RequestBody AssuntoDto assuntoDto){
        var id = service.criar(assuntoDto);
        return ResponseEntity.created(URI.create("/v1/assuntos/"+id.toString())).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Assunto> getById(@PathVariable("id") Long id){
        var assunto = service.getById(id);

        if (assunto.isPresent()){
            return ResponseEntity.ok(assunto.get());
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Assunto>>  listAll(){
        var assuntos = service.listar();
        return  ResponseEntity.ok(assuntos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Assunto> updated(@RequestBody AssuntoDto assuntoDto,
                                             @PathVariable("id") Long id){

        var livro = service.atualizar(assuntoDto, id);
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
