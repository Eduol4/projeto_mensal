package com.mensal3.mensal3.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mensal3.mensal3.entities.TextoEntity;
import com.mensal3.mensal3.services.TextoService;

@RestController
@RequestMapping("/textos")
public class TextoController {

    @Autowired
	private TextoService textoService;
	
	@PostMapping("/registrarTexto")
	public ResponseEntity<TextoEntity> registrarTexto(@Validated @RequestBody TextoEntity textoEntity) {
		try {
			return ResponseEntity.ok(textoService.registrarTexto(textoEntity));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	@GetMapping("/listarTextos")
	public ResponseEntity<List<TextoEntity>> listAllTexto() {
		try {
			return ResponseEntity.ok(textoService.listAllTexto());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
    @GetMapping("/buscarTag/{tagName}")
    public ResponseEntity<List<TextoEntity>> buscarTextoTag(@PathVariable String tagName) {
        try {
            List<TextoEntity> textoEntity = textoService.buscarTextoTag(tagName);
            
            if (textoEntity.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            
            return ResponseEntity.ok(textoEntity);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/buscarCategoria/{categoriaName}")
    public ResponseEntity<List<TextoEntity>> buscarTextoCategoria(@PathVariable String categoriaName) {
        try {
            List<TextoEntity> textoEntity = textoService.buscarTextoCategoria(categoriaName);
            
            if (textoEntity.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            
            return ResponseEntity.ok(textoEntity);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
	
	@DeleteMapping("/deletarTexto/{idTexto}")
    public ResponseEntity<Void> deleteTexto(@PathVariable Long idTexto) {
		try {
	        textoService.deleteTexto(idTexto);
	        return ResponseEntity.noContent().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	@PutMapping("/alterarTitulo/{idTexto}")
	public ResponseEntity<TextoEntity> alterarTitulo(@PathVariable Long idTexto, @RequestBody TextoEntity novaTextoEntity) {
		try {
			TextoEntity textoAlterada = textoService.alterarTitulo(idTexto, novaTextoEntity);
			return ResponseEntity.ok(textoAlterada);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	@PutMapping("/alterarConteudo/{idTexto}")
	public ResponseEntity<TextoEntity> alterarConteudo(@PathVariable Long idTexto, @RequestBody TextoEntity novaTextoEntity) {
		try {
			TextoEntity textoAlterada = textoService.alterarConteudo(idTexto, novaTextoEntity);
			return ResponseEntity.ok(textoAlterada);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
}