package com.mensal3.mensal3.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mensal3.mensal3.entities.UsuarioEntity;
import com.mensal3.mensal3.repositories.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioEntity registrarUsuario(UsuarioEntity usuarioEntity) {
		return usuarioRepository.save(usuarioEntity);
	}
	
	public List<UsuarioEntity> listAllUsuario() {
		return usuarioRepository.findAll();
	}
	
	public UsuarioEntity findById(Long idUsuario) {
		return usuarioRepository.findById(idUsuario).get();
	}
	
	// public UsuarioEntity findByEmail(String email) {
	// 	return usuarioRepository.findByEmail(email);
	// }
	
	public void deleteUsuario(Long idUsuario) {
		usuarioRepository.deleteById(idUsuario);
	}
}