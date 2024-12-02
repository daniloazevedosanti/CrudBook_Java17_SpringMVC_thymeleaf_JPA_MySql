package com.crudbook.crudbook.model.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;

@Entity
@Table(name = "tb_livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codL;

    private String titulo;

    private String editora;

    private Integer edicao;

    @Column(name = "ano_publicacao")
    private String anoPublicacao;

    private Double valor;

    @CreationTimestamp
    private Instant creationTimeStamp;

    @UpdateTimestamp
    private Instant updateTimeStamp;

    @ManyToMany
    @JoinTable(name = "tb_livro_autor",
            joinColumns = @JoinColumn(name="livro_codl"),
            inverseJoinColumns = @JoinColumn(name ="autor_codAu"))
    private List<Autor> autores;

    @ManyToMany
    @JoinTable(name = "tb_livro_assunto",
            joinColumns = @JoinColumn(name="livro_codl"),
            inverseJoinColumns = @JoinColumn(name ="assunto_codAs"))
    private List<Assunto> assuntos;

    public Livro(Integer codL, String titulo, String editora, Integer edicao, Double valor, String anoPublicacao, Instant creationTimeStamp, Instant updateTimeStamp, List<Autor> autores, List<Assunto> assuntos) {
        this.codL = codL;
        this.titulo = titulo;
        this.editora = editora;
        this.edicao = edicao;
        this.creationTimeStamp = creationTimeStamp;
        this.valor = valor;
        this.anoPublicacao = anoPublicacao;
        this.updateTimeStamp = updateTimeStamp;
        this.autores = autores;
        this.assuntos = assuntos;
    }

    public Livro() {
    }

    public Integer getCodL() {
        return codL;
    }

    public void setCodL(Integer codL) {
        this.codL = codL;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public Integer getEdicao() {
        return edicao;
    }

    public void setEdicao(Integer edicao) {
        this.edicao = edicao;
    }

    public String getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(String anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public Instant getCreationTimeStamp() {
        return creationTimeStamp;
    }

    public void setCreationTimeStamp(Instant creationTimeStamp) {
        this.creationTimeStamp = creationTimeStamp;
    }

    public Instant getUpdateTimeStamp() {
        return updateTimeStamp;
    }

    public void setUpdateTimeStamp(Instant updateTimeStamp) {
        this.updateTimeStamp = updateTimeStamp;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public List<Assunto> getAssuntos() {
        return assuntos;
    }

    public void setAssuntos(List<Assunto> assuntos) {
        this.assuntos = assuntos;
    }

}
