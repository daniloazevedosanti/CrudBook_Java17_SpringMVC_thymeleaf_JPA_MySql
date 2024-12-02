package com.crudbook.crudbook.model.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;

@Entity
@Table(name = "tb_autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codAu;

    private String nome;

    @ElementCollection
    @ManyToMany(mappedBy="autores", cascade = ALL)
    private List<Livro> livros;

    @CreationTimestamp
    private Instant creationTimeStamp;

    @UpdateTimestamp
    private Instant updateTimeStamp;

    public Autor(Integer codAu, String nome, Instant creationTimeStamp, Instant updateTimeStamp) {
        this.codAu = codAu;
        this.nome = nome;
        this.creationTimeStamp = creationTimeStamp;
        this.updateTimeStamp = updateTimeStamp;
    }

    public Autor() {
    }

    public Integer getCodAu() {
        return codAu;
    }

    public void setCodAu(Integer codAu) {
        this.codAu = codAu;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
