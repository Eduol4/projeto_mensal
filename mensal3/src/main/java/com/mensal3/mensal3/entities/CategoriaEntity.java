package com.mensal3.mensal3.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor

@Table
@Entity(name = "categoria")
public class CategoriaEntity {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategoria;

    @Column(name = "tituloCategoria")
	@NotBlank
    private String tituloCategoria;

	@OneToMany(mappedBy = "categoria")
	private List<TextoEntity> texto;

    public String getTituloCategoria() {
		return tituloCategoria;
	}
	public void setTituloCategoria(String tituloCategoria) {
		this.tituloCategoria = tituloCategoria;
	}
	
	public Long getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}

    public List<TextoEntity> getTexto() {
		return texto;
	}
	public void setTexto(List<TextoEntity> texto) {
		this.texto = texto;
	}
}