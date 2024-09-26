package com.mensal3.mensal3.controllers;

import com.mensal3.mensal3.dto.ComentarioDTO;
import com.mensal3.mensal3.entities.ComentarioEntity;
import com.mensal3.mensal3.services.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comentarios")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    @PostMapping("/registrarComentario")
    public ResponseEntity<ComentarioEntity> registrarComentario(@Validated @RequestBody ComentarioDTO comentarioDTO) {
        try {
            return ResponseEntity.ok(comentarioService.registrarComentario(comentarioDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/listarComentarios/{idTexto}")
    public ResponseEntity<List<ComentarioEntity>> listarComentarios(@PathVariable Long idTexto) {
        try {
            return ResponseEntity.ok(comentarioService.listarComentarios(idTexto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @DeleteMapping("/deletarComentario/{idComentario}")
    public ResponseEntity<Void> deletarComentario(@PathVariable Long idComentario) {
        try {
            comentarioService.deletarComentario(idComentario);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
