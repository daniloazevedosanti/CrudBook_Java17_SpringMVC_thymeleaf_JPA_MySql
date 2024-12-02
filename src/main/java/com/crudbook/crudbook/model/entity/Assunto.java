package com.crudbook.crudbook.model.entity;

import jakarta.persistence.*;

import java.util.List;

import static jakarta.persistence.CascadeType.ALL;

@Entity
@Table(name = "tb_assunto")
public class Assunto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codAs;

    private String descricao;

    @ElementCollection
    @ManyToMany(mappedBy="assuntos", cascade = ALL)
    private List<Livro> livros;

    public Assunto(Integer codAs, String descricao, List<Livro> livros) {
        this.codAs = codAs;
        this.descricao = descricao;
        this.livros = livros;
    }

    public Assunto() {
    }

    public Integer getCodAs() {
        return codAs;
    }

    public void setCodAs(Integer codAs) {
        this.codAs = codAs;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }
}
