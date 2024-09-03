package com.mensal3.mensal3.entities;
// @AllArgsConstructor

// @Table
// @Entity(name = "categoria")
public class CategoriaEntity {

    // @Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategoria;

    // @Column(name = "tituloCategoria")
	// @NotBlank
    private String tituloCategoria;

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

    // public List<TextoEntity> getTexto() {
	// 	return texto;
	// }

	// public void setTexto(List<TextoEntity> texto) {
	// 	this.texto = texto;
	// }
}