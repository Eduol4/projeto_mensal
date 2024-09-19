package com.mensal3.mensal3.controllersTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mensal3.mensal3.controllers.CategoriaController;
import com.mensal3.mensal3.entities.CategoriaEntity;
import com.mensal3.mensal3.entities.TagEntity;
import com.mensal3.mensal3.entities.TextoEntity;
import com.mensal3.mensal3.repositories.CategoriaRepository;
import com.mensal3.mensal3.services.CategoriaService;

@SpringBootTest
public class CategoriaControllerTests {
    @MockBean
    CategoriaService categoriaService;

    @Autowired
    CategoriaController categoriaController;

    @MockBean
    CategoriaRepository categoriaRepository;

    @BeforeEach
    void setUp() {
        List<CategoriaEntity> listaCategorias = new ArrayList<>();
        List<TextoEntity> textoTeste1 = new ArrayList<>();
        List<TextoEntity> textoTeste2 = new ArrayList<>();
        List<TextoEntity> textoTeste3 = new ArrayList<>();

        listaCategorias.add(new CategoriaEntity(1L, "CategoriaTeste", textoTeste1));
        listaCategorias.add(new CategoriaEntity(2L, "TesteCategoria", textoTeste2));
        listaCategorias.add(new CategoriaEntity(3L, "TeTeste", textoTeste3));
        when(categoriaRepository.findAll()).thenReturn(listaCategorias);

        CategoriaEntity categoriaEntity1 = new CategoriaEntity(4L, "Testão", null);
        CategoriaEntity categoriaEntity2 = new CategoriaEntity(5L, "Testinha", null);
        when(categoriaRepository.findById(4L)).thenReturn(Optional.of(categoriaEntity1));
        when(categoriaRepository.findById(5L)).thenReturn(Optional.of(categoriaEntity2));
    }

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
    @DisplayName("Teste que lista todas as categorias")
    void listarCategoriasTest() {
        ResponseEntity<List<CategoriaEntity>> lista = this.categoriaController.listAll();
        assertEquals(HttpStatus.OK, lista.getStatusCode());
        assertEquals(3, lista.getBody().size());
    }

    @Test
    @DisplayName("Teste para deletar categorias pelo Id")
    void deletarCategoriaById() {
        ResponseEntity<Void> categoriaByIdErro = this.categoriaController.delete(10L);
        assertEquals(HttpStatus.NO_CONTENT, categoriaByIdErro.getStatusCode());
    }
}
