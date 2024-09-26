package com.mensal3.mensal3.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity(name = "comentario")
public class ComentarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idComentario;

    @NotBlank
    @Column(name = "conteudoComentario")
    private String conteudoComentario;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private UsuarioEntity autor;

    @ManyToOne
    @JoinColumn(name = "texto_id")
    private TextoEntity texto;

    @Column(name = "dataComentario")
    private LocalDateTime dataComentario;

    public ComentarioEntity() {
    }

    public ComentarioEntity(String conteudoComentario, UsuarioEntity autor, TextoEntity texto, LocalDateTime dataComentario) {
        this.conteudoComentario = conteudoComentario;
        this.autor = autor;
        this.texto = texto;
        this.dataComentario = dataComentario;
    }

    public Long getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(Long idComentario) {
        this.idComentario = idComentario;
    }

    public String getConteudoComentario() {
        return conteudoComentario;
    }

    public void setConteudoComentario(String conteudoComentario) {
        this.conteudoComentario = conteudoComentario;
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
