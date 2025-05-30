package com.mensal3.mensal3.entities;

import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity(name = "texto")
public class TextoEntity {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTexto;

    @Column(name = "tituloTexto")
	@NotBlank
    private String tituloTexto;

    @Column(name = "conteudoTexto")
	@NotBlank
    private String conteudoTexto;

    @ManyToOne
	@JoinColumn(name = "autor_id")
	private UsuarioEntity autor;
	
    @ManyToMany
    @JoinTable(
        name = "texto_tag",
        joinColumns = @JoinColumn(name = "texto_id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<TagEntity> tag;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private CategoriaEntity categoria;

	public TextoEntity() {}

	public TextoEntity(Long idTexto, String tituloTexto, String conteudoTexto, UsuarioEntity autor, List<TagEntity> tag, CategoriaEntity categoria) {
        this.idTexto = idTexto;
        this.tituloTexto = tituloTexto;
        this.conteudoTexto = conteudoTexto;
        this.autor = autor;
		this.tag = tag;
		this.categoria = categoria;
    }

    public String getTituloTexto() {
		return tituloTexto;
	}
	public void setTituloTexto(String tituloTexto) {
		this.tituloTexto = tituloTexto;
	}

	public String getConteudoTexto() {
		return conteudoTexto;
	}
	public void setConteudoTexto(String conteudoTexto) {
		this.conteudoTexto = conteudoTexto;
	}

	public Long getIdTexto() {
		return idTexto;
	}
	public void setIdTexto(Long idTexto) {
		this.idTexto = idTexto;
	}

    public UsuarioEntity getAutor() {
		return autor;
	}
	public void setAutor(UsuarioEntity autor) {
		this.autor = autor;
	}

	public List<TagEntity> getTag() {
		return tag;
	}
	public void setTag(List<TagEntity> tag) {
		this.tag = tag;
	}

	public CategoriaEntity getCategoria() {
		return categoria;
	}
	public void setCategoria(CategoriaEntity categoria) {
		this.categoria = categoria;
	}

	@OneToMany(mappedBy = "texto", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ComentarioEntity> comentarios;

	public List<ComentarioEntity> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<ComentarioEntity> comentarios) {
		this.comentarios = comentarios;
	}

}