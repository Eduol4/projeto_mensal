package com.mensal3.mensal3.controllersTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mensal3.mensal3.controllers.CategoriaController;
import com.mensal3.mensal3.entities.CategoriaEntity;
import com.mensal3.mensal3.entities.TextoEntity;
import com.mensal3.mensal3.repositories.CategoriaRepository;
import com.mensal3.mensal3.services.CategoriaService;

@SpringBootTest
public class CategoriaControllerTests {
    @Autowired
    CategoriaService categoriaService;

    @Autowired
    CategoriaController categoriaController;

    @MockBean
    CategoriaRepository categoriaRepository;


    @Test
    @DisplayName("Teste que registra uma categoria")
    void registrarCategoriaTest() {
        List<TextoEntity> novoTexto = new ArrayList<>();
        CategoriaEntity novaCategoria = new CategoriaEntity(6L, "Nova Categoria", novoTexto);
        when(categoriaService.registrarCategoria(novaCategoria)).thenReturn(novaCategoria);

        ResponseEntity<CategoriaEntity> categoriaRegistrada = categoriaController.registrar(novaCategoria);
        assertEquals(HttpStatus.OK, categoriaRegistrada.getStatusCode());
        assertEquals(novaCategoria, categoriaRegistrada.getBody());
    }

    @Test
    @DisplayName("Testa um erro ao registrar uma categoria")
    void registrarCategoriaTestErro() {
        List<TextoEntity> novoTexto = new ArrayList<>();
        CategoriaEntity novaCategoria = new CategoriaEntity(7L, "novaCategoria2", novoTexto);
        when(categoriaService.registrarCategoria(any(CategoriaEntity.class)))
            .thenThrow(new RuntimeException("Erro ao registrar categoria"));

        ResponseEntity<CategoriaEntity> categoriaRegistradaErro = categoriaController.registrar(novaCategoria);
        assertEquals(HttpStatus.BAD_REQUEST, categoriaRegistradaErro.getStatusCode());
        assertNull(categoriaRegistradaErro.getBody());
    }

    @Test
    @DisplayName("Teste que lista todas as categorias")
    void listarCategoriasTest() {
        List<CategoriaEntity> listaCategorias = new ArrayList<>();
        List<TextoEntity> textoTeste1 = new ArrayList<>();
        List<TextoEntity> textoTeste2 = new ArrayList<>();
        List<TextoEntity> textoTeste3 = new ArrayList<>();

        listaCategorias.add(new CategoriaEntity(1L, "CategoriaTeste", textoTeste1));
        listaCategorias.add(new CategoriaEntity(2L, "TesteCategoria", textoTeste2));
        listaCategorias.add(new CategoriaEntity(3L, "TeTeste", textoTeste3));
        when(categoriaService.listAllCategoria()).thenReturn(listaCategorias);

        ResponseEntity<List<CategoriaEntity>> lista = this.categoriaController.listAll();
        assertEquals(HttpStatus.OK, lista.getStatusCode());
        assertEquals(3, lista.getBody().size());
    }

    @Test
    @DisplayName("Testa um erro ao tentar listar todas as categorias")
    void listarCategoriasTestErro() {
        when(categoriaService.listAllCategoria()).thenThrow(new RuntimeException("Erro ao listar categorias"));

        ResponseEntity<List<CategoriaEntity>> lista = this.categoriaController.listAll();
        assertEquals(HttpStatus.BAD_REQUEST, lista.getStatusCode());
        assertNull(lista.getBody());
    }

    // @Test
    // @DisplayName("Teste que busca uma categoria por Id")
    // void buscarCategoriasByIdTest() {
    //     CategoriaEntity categoriaEntity1 = new CategoriaEntity(4L, "Testão", null);
    //     when(categoriaService.findById(4L)).thenReturn(categoriaEntity1);
        
    //     ResponseEntity<CategoriaEntity> CategoriaById = this.categoriaController.findById(4L);
    //     assertEquals(HttpStatus.OK, CategoriaById.getStatusCode());
    //     assertEquals("Testão", CategoriaById.getBody().getTituloCategoria());
    // }

    // @Test
    // @DisplayName("Testa um erro ao tentar buscar uma categoria por Id")
    // void buscarCategoriasByIdTestErro() {
    //     when(categoriaService.findById(10L)).thenThrow(new NoSuchElementException("Categoria não encontrada"));

    //     ResponseEntity<CategoriaEntity> CategoriaByIdErro = this.categoriaController.findById(10L);
    //     assertEquals(HttpStatus.BAD_REQUEST, CategoriaByIdErro.getStatusCode());
    // }

    // @Test
    // @DisplayName("Teste para alteração de categorias pelo Id")
    // void alterarCategoriaById() throws Exception {
    // List<TextoEntity> textoAlteracao =  new ArrayList<>();
    // CategoriaEntity novaCategoria = new CategoriaEntity(10L, "Categoria Nova", textoAlteracao);
    
    // when(categoriaService.alterarCategoria(10L, novaCategoria)).thenReturn(novaCategoria);
    
    // ResponseEntity<CategoriaEntity> resposta = categoriaController.alterar(10L, novaCategoria);
    // assertEquals(HttpStatus.OK, resposta.getStatusCode());
    // assertEquals(novaCategoria, resposta.getBody());

    // }

    // @Test
    // @DisplayName("Testa um erro ao tentar alterar categoria pelo Id")
    // void alterarCategoriaByIdErro() throws Exception {
    //     List<TextoEntity> textoAlteracao = new ArrayList<>();
    //     CategoriaEntity novaCategoria = new CategoriaEntity(11L, "Categoria Nova2", textoAlteracao);
    //     doThrow(new Exception("Categoria 11 não encontrada!")).when(categoriaService).alterarCategoria(11L, novaCategoria);
    
    //     ResponseEntity<CategoriaEntity> resposta = categoriaController.alterar(11L, novaCategoria);
    //     assertEquals(HttpStatus.BAD_REQUEST, resposta.getStatusCode());
    //     assertNull(resposta.getBody());
    // }
}
