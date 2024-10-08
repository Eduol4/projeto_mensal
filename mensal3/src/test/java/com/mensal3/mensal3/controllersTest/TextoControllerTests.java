package com.mensal3.mensal3.controllersTest;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
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
import com.mensal3.mensal3.entities.TagEntity;
import com.mensal3.mensal3.entities.TextoEntity;
import com.mensal3.mensal3.entities.UsuarioEntity;
import com.mensal3.mensal3.repositories.TextoRepository;
import com.mensal3.mensal3.services.TextoService;

@SpringBootTest
public class TextoControllerTests {
    @Autowired
    TextoService textoService;
    
    @Autowired
    TextoController textoController;

    @MockBean
    TextoRepository textoRepository;

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
    @DisplayName("Testa um erro ao registrar um texto")
    void registrarTextoTestErro() {
        UsuarioEntity novoAutor = new UsuarioEntity();
        CategoriaEntity novaCategoria = new CategoriaEntity();
        List<TagEntity> novaTag = new ArrayList<>();
        TextoEntity novoTexto = new TextoEntity(6L, "Texto Novo2", "Conteudo novo2", novoAutor, novaTag, novaCategoria);
        when(textoService.registrarTexto(any(TextoEntity.class)))
            .thenThrow(new RuntimeException("Erro ao registrar texto"));

        ResponseEntity<TextoEntity> textoRegistradoErro = textoController.registrar(novoTexto);
        assertEquals(HttpStatus.BAD_REQUEST, textoRegistradoErro.getStatusCode());
        assertNull(textoRegistradoErro.getBody());
    }

    @Test
    @DisplayName("Teste que lista todos os textos")
    void listarTextoTest() {
        List<TextoEntity> listaTexto = new ArrayList<>();
        UsuarioEntity autor1 = new UsuarioEntity();
        List<TagEntity> tag1 = new ArrayList<>();
        CategoriaEntity categoria1 = new CategoriaEntity();
        UsuarioEntity autor2 = new UsuarioEntity();
        List<TagEntity> tag2 = new ArrayList<>();
        CategoriaEntity categoria2 = new CategoriaEntity();

        listaTexto.add(new TextoEntity(1L, "Teste", "Teste", autor1, tag1, categoria1));
        listaTexto.add(new TextoEntity(2L, "Texte", "Texte", autor2, tag2, categoria2));
        when(textoService.listAllTexto()).thenReturn(listaTexto);

        ResponseEntity<List<TextoEntity>> lista = this.textoController.listAll();
        assertEquals(HttpStatus.OK, lista.getStatusCode());
        assertEquals(2, lista.getBody().size());
    }

    @Test
    @DisplayName("Testa um erro ao tentar listar todos os textos")
    void listarTextosTestErro() {
        when(textoService.listAllTexto()).thenThrow(new RuntimeException("Erro ao listar textos"));

        ResponseEntity<List<TextoEntity>> lista = this.textoController.listAll();
        assertEquals(HttpStatus.BAD_REQUEST, lista.getStatusCode());
        assertNull(lista.getBody());
    }

    @Test
    @DisplayName("Teste que busca um texto por Id")
    void buscarTextosByIdTest() {
        TextoEntity textoEntity1 = new TextoEntity(4L, "TituloTeste", "Conteudo horrível", null, null, null);
        when(textoService.findById(4L)).thenReturn(textoEntity1);
        
        ResponseEntity<TextoEntity> TextoById = this.textoController.findById(4L);
        assertEquals(HttpStatus.OK, TextoById.getStatusCode());
        assertEquals("TituloTeste", TextoById.getBody().getTituloTexto());
    }

    @Test
    @DisplayName("Testa um erro ao tentar buscar um texto por Id")
    void buscarTextosByIdTestErro() {
        when(textoService.findById(10L)).thenThrow(new NoSuchElementException("Texto não encontrado"));

        ResponseEntity<TextoEntity> TextoByIdErro = this.textoController.findById(10L);
        assertEquals(HttpStatus.BAD_REQUEST, TextoByIdErro.getStatusCode());
    }

    @Test
    @DisplayName("Teste para alteração de textos pelo Id")
    void alterarTextoById() throws Exception {
        TextoEntity novoTexto = new TextoEntity(10L, "Titulo", "Conteudo", null, null, null);
        when(textoService.alterarTexto(10L, novoTexto)).thenReturn(novoTexto);
    
        ResponseEntity<TextoEntity> resposta = textoController.alterar(10L, novoTexto);
        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertEquals(novoTexto, resposta.getBody());

        // verify(tagService, times(1)).alterarTag(eq(1L), any(TagEntity.class));
    }

    @Test
    @DisplayName("Testa um erro ao tentar alterar texto pelo Id")
    void alterarTextoByIdErro() throws Exception {
        TextoEntity novaTexto = new TextoEntity(11L, "Texto Novo2", "Conteudo Novo2", null, null, null);
        doThrow(new Exception("Texto 11 não encontrado!")).when(textoService).alterarTexto(11L, novaTexto);
    
        ResponseEntity<TextoEntity> resposta = textoController.alterar(11L, novaTexto);
        assertEquals(HttpStatus.BAD_REQUEST, resposta.getStatusCode());
        assertNull(resposta.getBody());
    }

    @Test
    @DisplayName("Teste que busca textos por tag")
    void buscarTextoPorTagTest() {
        List<TextoEntity> textoByTag = new ArrayList<>();
        textoByTag.add(new TextoEntity(1L, "TítuloByTag1", "ConteúdoByTag1", null, null, null));
        textoByTag.add(new TextoEntity(2L, "TítuloByTag2", "ConteúdoByTag2", null, null, null));
        when(textoService.buscarTextoTag("TagTeste")).thenReturn(textoByTag);

        ResponseEntity<List<TextoEntity>> resposta = textoController.buscarTag("TagTeste");
        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertEquals(2, resposta.getBody().size());
        assertEquals("TítuloByTag1", resposta.getBody().get(0).getTituloTexto());
        assertEquals("TítuloByTag2", resposta.getBody().get(1).getTituloTexto());

        // verify(textoService, times(1)).buscarTextoTag("TagTeste");
    }

    @Test
    @DisplayName("Testa um NO CONTENT ao buscar textos por tag")
    void buscarTextoPorTagTestNoContent() {
        when(textoService.buscarTextoTag("TagVazia")).thenReturn(new ArrayList<>());

        ResponseEntity<List<TextoEntity>> resposta = textoController.buscarTag("TagVazia");
        assertEquals(HttpStatus.NO_CONTENT, resposta.getStatusCode());
        assertNull(resposta.getBody());

        // verify(textoService, times(1)).buscarTextoTag("TagVazia");
    }

    @Test
    @DisplayName("Testa um erro ao tentar buscar por tag")
    void buscarTextoPorTagTestErro() {
        when(textoService.buscarTextoTag("TagErrada")).thenThrow(new RuntimeException("Erro"));

        ResponseEntity<List<TextoEntity>> resposta = textoController.buscarTag("TagErrada");
        assertEquals(HttpStatus.BAD_REQUEST, resposta.getStatusCode());
        assertNull(resposta.getBody());

        // verify(textoService, times(1)).buscarTextoTag("TagErro");
    }

    @Test
    @DisplayName("Teste que busca textos por categoria")
    void buscarTextoPorCategoriaTest() {
        List<TextoEntity> textosByCategoria = new ArrayList<>();
        textosByCategoria.add(new TextoEntity(1L, "TítuloByCategoria1", "ConteúdoByCategoria1", null, null, null));
        textosByCategoria.add(new TextoEntity(2L, "TítuloByCategoria2", "ConteúdoByCategoria2", null, null, null));
        when(textoService.buscarTextoCategoria("CategoriaTeste")).thenReturn(textosByCategoria);

        ResponseEntity<List<TextoEntity>> resposta = textoController.buscarCategoria("CategoriaTeste");
        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertEquals(2, resposta.getBody().size());
        assertEquals("TítuloByCategoria1", resposta.getBody().get(0).getTituloTexto());
        assertEquals("TítuloByCategoria2", resposta.getBody().get(1).getTituloTexto());

        // verify(textoService, times(1)).buscarTextoCategoria("CategoriaTeste");
    }

    @Test
    @DisplayName("Testa um NO CONTENT ao buscar textos por categoria")
    void buscarTextoPorCategoriaTestNoContent() {
        when(textoService.buscarTextoCategoria("CategoriaVazia")).thenReturn(new ArrayList<>());

        ResponseEntity<List<TextoEntity>> resposta = textoController.buscarCategoria("CategoriaVazia");
        assertEquals(HttpStatus.NO_CONTENT, resposta.getStatusCode());
        assertNull(resposta.getBody());

        // verify(textoService, times(1)).buscarTextoCategoria("CategoriaVazia");
    }

    @Test
    @DisplayName("Testa um erro ao tentar buscar textos por categoria")
    void buscarTextoPorCategoriaTestErro() {
        when(textoService.buscarTextoCategoria("CategoriaErro")).thenThrow(new RuntimeException("Erro"));
        ResponseEntity<List<TextoEntity>> resposta = textoController.buscarCategoria("CategoriaErro");
        assertEquals(HttpStatus.BAD_REQUEST, resposta.getStatusCode());
        assertNull(resposta.getBody());

        // verify(textoService, times(1)).buscarTextoCategoria("CategoriaErro");
    }
}
