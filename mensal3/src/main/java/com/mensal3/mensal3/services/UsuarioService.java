package com.mensal3.mensal3.services;

import java.util.List;
import java.util.Optional;

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
	
	public void deleteUsuario(Long idUsuario) throws Exception {
		UsuarioEntity usuarioEntity = usuarioRepository.findById(idUsuario)
				.orElseThrow(() -> new Exception("Usuário não encontrado"));
		
		usuarioRepository.delete(usuarioEntity);
	}
}