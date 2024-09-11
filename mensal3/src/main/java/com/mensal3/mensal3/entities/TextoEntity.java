package com.mensal3.mensal3.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
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
}