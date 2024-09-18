package com.mensal3.mensal3.controllersTest;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mensal3.mensal3.controllers.TextoController;
import com.mensal3.mensal3.entities.CategoriaEntity;
import com.mensal3.mensal3.entities.TextoEntity;
import com.mensal3.mensal3.entities.UsuarioEntity;
import com.mensal3.mensal3.repositories.TextoRepository;
import com.mensal3.mensal3.services.TextoService;
import com.mensal3.mensal3.services.UsuarioService;

@SpringBootTest
public class TextoControllerTests {
    @MockBean
    UsuarioService usuarioService;
    
    @Autowired
    TextoController textoController;

    @MockBean
    TextoRepository textoRepository;

    // @Test
    // @DisplayName("Teste para buscar texto por tag via Controller")
    //     void buscarTextoTagControllerTest() {
    //     List<TextoEntity> textos = new ArrayList<>();
    //     textos.add(new TextoEntity(1L, "Título 1", "Conteúdo 1", new UsuarioEntity(), new ArrayList<>(), new CategoriaEntity()));

    //     when(TextoService.buscarTextoTag(any(String.class))).thenReturn(textos);

    //     ResponseEntity<List<TextoEntity>> resposta = textoController.buscarTextoTag("Tag de Teste");

    //     assertEquals(HttpStatus.OK, resposta.getStatusCode());
    //     assertNotNull(resposta.getBody());
    //     assertEquals(1, resposta.getBody().size());

    //     verify(TextoService, times(1)).buscarTextoTag(any(String.class));
    // }

    // @Test
    // @DisplayName("Teste para buscar texto por categoria via Controller")
    // void buscarTextoCategoriaControllerTest() {
    //     List<TextoEntity> textos = new ArrayList<>();
    //     textos.add(new TextoEntity(1L, "Título 1", "Conteúdo 1", new UsuarioEntity(), new ArrayList<>(), new CategoriaEntity()));

    //     when(textoService.buscarTextoCategoria(any(String.class))).thenReturn(textos);

    //     ResponseEntity<List<TextoEntity>> resposta = textoController.buscarTextoCategoria("Categoria de Teste");

    //     assertEquals(HttpStatus.OK, resposta.getStatusCode());
    //     assertNotNull(resposta.getBody());
    //     assertEquals(1, resposta.getBody().size());

    //     verify(textoService, times(1)).buscarTextoCategoria(any(String.class));
    // }
}
