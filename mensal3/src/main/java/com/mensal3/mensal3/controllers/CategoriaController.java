package com.mensal3.mensal3.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mensal3.mensal3.entities.CategoriaEntity;
import com.mensal3.mensal3.services.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
	private CategoriaService categoriaService;
	
	@PostMapping("/registrarCategoria")
	public ResponseEntity<CategoriaEntity> registrar(@Validated @RequestBody CategoriaEntity categoriaEntity) {
		try {
			return ResponseEntity.ok(categoriaService.registrarCategoria(categoriaEntity));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	public ResponseEntity<List<CategoriaEntity>> listAll() {
		try {
			return ResponseEntity.ok(categoriaService.listAllCategoria());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}

	public ResponseEntity<CategoriaEntity> findById(Long idCategoria) {
		try {
			return ResponseEntity.ok(categoriaService.findById(idCategoria));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	@DeleteMapping("/deletarCategoria/{idCategoria}")
    public ResponseEntity<Void> delete(@PathVariable Long idCategoria) {
		try {
			categoriaService.deleteCategoria(idCategoria);
	        return ResponseEntity.noContent().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	@PutMapping("/alterarCategoria/{idCategoria}")
	public ResponseEntity<CategoriaEntity> alterar(@PathVariable Long idCategoria, @RequestBody CategoriaEntity novaCategoriaEntity) {
		try {
			CategoriaEntity categoriaAlterada = categoriaService.alterarCategoria(idCategoria, novaCategoriaEntity);
			return ResponseEntity.ok(categoriaAlterada);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
}