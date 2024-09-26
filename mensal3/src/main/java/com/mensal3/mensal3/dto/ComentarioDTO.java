package com.mensal3.mensal3.dto;

public class ComentarioDTO {

    private String conteudo;
    private Long autorId;
    private Long textoId;

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public Long getAutorId() {
        return autorId;
    }

    public void setAutorId(Long autorId) {
        this.autorId = autorId;
    }

    public Long getTextoId() {
        return textoId;
    }

    public void setTextoId(Long textoId) {
        this.textoId = textoId;
    }
}
