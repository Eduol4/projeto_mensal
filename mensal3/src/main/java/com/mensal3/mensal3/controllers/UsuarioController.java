package com.mensal3.mensal3.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mensal3.mensal3.entities.UsuarioEntity;
import com.mensal3.mensal3.services.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
	private UsuarioService usuarioService;
	
	@PostMapping("/registrarUsuario")
	public ResponseEntity<UsuarioEntity> registrar(@RequestBody UsuarioEntity usuarioEntity) {
		try {
			return ResponseEntity.ok(usuarioService.registrarUsuario(usuarioEntity));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	@GetMapping("/listarUsuarios")
	public ResponseEntity<List<UsuarioEntity>> listAll() {
		try {
			return ResponseEntity.ok(usuarioService.listAllUsuario());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	@GetMapping("/findById/{idUsuario}")
	public ResponseEntity<UsuarioEntity> findById(@PathVariable Long idUsuario) {
		try {
			return ResponseEntity.ok(usuarioService.findById(idUsuario));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	@DeleteMapping("/deletarUsuario/{idUsuario}")
    public ResponseEntity<Void> delete(@PathVariable Long idUsuario) {
		try {
	        usuarioService.deleteUsuario(idUsuario);
	        return ResponseEntity.noContent().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
    }
}