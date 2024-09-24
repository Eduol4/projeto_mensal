package com.mensal3.mensal3.controllersTest;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
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
    @MockBean
    TextoService textoService;
    
    @Autowired
    TextoController textoController;

    @MockBean
    TextoRepository textoRepository;

    // @BeforeEach
    // void setUp() {
        // List<TextoEntity> listaTexto = new ArrayList<>();
        // UsuarioEntity autor1 = new UsuarioEntity();
        // List<TagEntity> tag1 = new ArrayList<>();
        // CategoriaEntity categoria1 = new CategoriaEntity();
        // UsuarioEntity autor2 = new UsuarioEntity();
        // List<TagEntity> tag2 = new ArrayList<>();
        // CategoriaEntity categoria2 = new CategoriaEntity();

        // listaTexto.add(new TextoEntity(1L, "Teste", "Teste", autor1, tag1, categoria1));
        // listaTexto.add(new TextoEntity(2L, "Texte", "Texte", autor2, tag2, categoria2));

        // TextoEntity textoEntity1 = new TextoEntity(4L, "TituloTeste", "Conteudo horrível", null, null, null);
        // when(textoRepository.findByCategoria_TituloCategoria(categoria1)).thenReturn(Optional.of(textoEntity1));
        // when(textoRepository.findById(4L)).thenReturn(Optional.of(textoEntity1));
    // }

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

    // @Test
    // @DisplayName("Teste para deletar um texto pelo Id")
    // void deletarTextoByIdTest() {
    //     ResponseEntity<Void> deletedTexto = this.textoController.delete(4L);
    //     assertEquals(HttpStatus.NO_CONTENT, deletedTexto.getStatusCode());
    // }

    // @Test
    // @DisplayName("Testa um erro ao deletar um texto pelo Id")
    // void deletarTextoByIdTestErro() {
    //     ResponseEntity<Void> deletedTexto = this.textoController.delete(69L);
    //     assertEquals(HttpStatus.BAD_REQUEST, deletedTexto.getStatusCode());
    // }

    @Test
    @DisplayName("Teste para alteração de tags pelo Id")
    void alterarTagById() throws Exception {
        TextoEntity novoTexto = new TextoEntity(10L, "Titulo", "Conteudo", null, null, null);
        when(textoService.alterarTexto(10L, novoTexto)).thenReturn(novoTexto);
    
        ResponseEntity<TextoEntity> resposta = textoController.alterar(10L, novoTexto);
        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertEquals(novoTexto, resposta.getBody());

        // verify(tagService, times(1)).alterarTag(eq(1L), any(TagEntity.class));
    }

}
