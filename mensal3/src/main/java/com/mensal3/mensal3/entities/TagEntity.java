package com.mensal3.mensal3.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;

@Entity(name = "tag")
public class TagEntity {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTag;

    @Column(name = "tituloTag")
	@NotBlank
    private String tituloTag;

    @ManyToMany(mappedBy = "tag")
    private List<TextoEntity> texto;

	public TagEntity() {}

	public TagEntity(Long idTag, String tituloTag, List<TextoEntity> texto) {
		this.idTag = idTag;
		this.tituloTag = tituloTag;
		this.texto = texto;
	}

    public String getTituloTag() {
		return tituloTag;
	}
	public void setTituloTag(String tituloTag) {
		this.tituloTag = tituloTag;
	}
	
	public Long getIdTag() {
		return idTag;
	}
	public void setIdTag(Long idTag) {
		this.idTag = idTag;
	}

    public List<TextoEntity> getTexto() {
		return texto;
	}
	public void setTexto(List<TextoEntity> texto) {
		this.texto = texto;
	}
}