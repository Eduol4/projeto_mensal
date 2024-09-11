package com.mensal3.mensal3.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "usuario")
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @Column(name = "nomeUsuario")
    @NotBlank
    private String nomeUsuario;
    
    @Column(name = "email")
    @NotBlank
    @Pattern(regexp = "^[^@]+@[^@]+\\.[^@]+$")
    private String email;
    
    @Column(name = "senha")
    //@NotBlank
    private String senha;

    @OneToMany(mappedBy = "autor")
    private List<TextoEntity> texto;

    public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}

    public List<TextoEntity> getTexto() {
		return texto;
	}
	public void setTexto(List<TextoEntity> texto) {
		this.texto = texto;
	}
}
