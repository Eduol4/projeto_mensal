package com.mensal3.mensal3.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ComentarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String conteudo;

    @ManyToOne
    @JoinColumn(name = "autor_id", nullable = false)
    private UsuarioEntity autor;

    @ManyToOne
    @JoinColumn(name = "texto_id", nullable = false)
    private TextoEntity texto;

    @Column(nullable = false)
    private LocalDateTime dataComentario;

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public UsuarioEntity getAutor() {
        return autor;
    }

    public void setAutor(UsuarioEntity autor) {
        this.autor = autor;
    }

    public TextoEntity getTexto() {
        return texto;
    }

    public void setTexto(TextoEntity texto) {
        this.texto = texto;
    }

    public LocalDateTime getDataComentario() {
        return dataComentario;
    }

    public void setDataComentario(LocalDateTime dataComentario) {
        this.dataComentario = dataComentario;
    }
}
