package com.mensal3.mensal3.controllersTest;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mensal3.mensal3.controllers.TextoController;
import com.mensal3.mensal3.entities.CategoriaEntity;
import com.mensal3.mensal3.entities.TagEntity;
import com.mensal3.mensal3.entities.TextoEntity;
import com.mensal3.mensal3.entities.UsuarioEntity;
import com.mensal3.mensal3.repositories.TextoRepository;
import com.mensal3.mensal3.services.TextoService;

@SpringBootTest
public class TextoControllerTests {
    @MockBean
    TextoService textoService;
    
    @Autowired
    TextoController textoController;

    @MockBean
    TextoRepository textoRepository;

    @BeforeEach
    void setUp() {
        List<TextoEntity> listaTexto = new ArrayList<>();
        UsuarioEntity autor1 = new UsuarioEntity();
        List<TagEntity> tag1 = new ArrayList<>();
        CategoriaEntity categoria1 = new CategoriaEntity();
        UsuarioEntity autor2 = new UsuarioEntity();
        List<TagEntity> tag2 = new ArrayList<>();
        CategoriaEntity categoria2 = new CategoriaEntity();
        UsuarioEntity autor3 = new UsuarioEntity();
        List<TagEntity> tag3 = new ArrayList<>();
        CategoriaEntity categoria3 = new CategoriaEntity();

        listaTexto.add(new TextoEntity(1L, "Teste", "Teste", autor1, tag1, categoria1));
        listaTexto.add(new TextoEntity(2L, "Texte", "Texte", autor2, tag2, categoria2));
        listaTexto.add(new TextoEntity(3L, "Texta", "Texta", autor3, tag3, categoria3));

        TextoEntity textoEntity1 = new TextoEntity(4L, "TituloTeste", "Conteudo horrível", null, null, null);
        // when(textoRepository.findByCategoria_TituloCategoria(categoria1)).thenReturn(Optional.of(textoEntity1));
        when(textoRepository.findById(4L)).thenReturn(Optional.of(textoEntity1));
    }

    @Test
    @DisplayName("Teste que registra um texto")
    void registrarTextoTest() {
        UsuarioEntity novoAutor = new UsuarioEntity();
        CategoriaEntity novaCategoria = new CategoriaEntity();
        List<TagEntity> novaTag = new ArrayList<>();
        TextoEntity novoTexto = new TextoEntity(5L, "Texto Novo", "conteudo novo", novoAutor, novaTag, novaCategoria);
        when(textoService.registrarTexto(novoTexto)).thenReturn(novoTexto);

        ResponseEntity<TextoEntity> textoRegistrado = textoController.registrar(novoTexto);
        assertEquals(HttpStatus.OK, textoRegistrado.getStatusCode());
        assertEquals(novoTexto, textoRegistrado.getBody());
    }

    @Test
    @DisplayName("Teste que lista todos os textos")
    void listarTextoTest() {
        ResponseEntity<List<TextoEntity>> lista = this.textoController.listAll();
        assertEquals(HttpStatus.OK, lista.getStatusCode());
        assertEquals(3, lista.getBody().size());
    }

    @Test
    @DisplayName("Teste para deletar um texto pelo Id")
    void deletarTextoByIdTest() {
        ResponseEntity<Void> deletedTexto = this.textoController.delete(4L);
        assertEquals(HttpStatus.NO_CONTENT, deletedTexto.getStatusCode());
    }

    @Test
    @DisplayName("Testa um erro ao deletar um texto pelo Id")
    void deletarTextoByIdTestErro() {
        ResponseEntity<Void> deletedTexto = this.textoController.delete(69L);
        assertEquals(HttpStatus.BAD_REQUEST, deletedTexto.getStatusCode());
    }

}
