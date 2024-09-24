package com.mensal3.mensal3.controllersTest;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mensal3.mensal3.controllers.TagController;
import com.mensal3.mensal3.entities.TagEntity;
import com.mensal3.mensal3.entities.TextoEntity;
import com.mensal3.mensal3.repositories.TagRepository;
import com.mensal3.mensal3.services.TagService;

@SpringBootTest
public class TagControllerTests {
    @MockBean
    TagService tagService;
    
    @Autowired
    TagController tagController;

    @MockBean
    TagRepository tagRepository;

    // @BeforeEach
    // void setUp() {
        // List<TagEntity> listaTags = new ArrayList<>();
        // List<TextoEntity> textoTeste1 = new ArrayList<>();
        // List<TextoEntity> textoTeste2 = new ArrayList<>();
        // List<TextoEntity> textoTeste3 = new ArrayList<>();

        // listaTags.add(new TagEntity(1L, "Titulo Teste", textoTeste1));
        // listaTags.add(new TagEntity(2L, "Titulo Horrível", textoTeste2));
        // listaTags.add(new TagEntity(3L, "Titulo Maravilhoso", textoTeste3));
        // when(tagRepository.findAll()).thenReturn(listaTags);

        // TagEntity tagEntity1 = new TagEntity(4L, "Tag teste", textoTeste1);
        // TagEntity tagEntity2 = new TagEntity(5L, "Teste tag", textoTeste2);
        // when(tagRepository.findById(4L)).thenReturn(Optional.of(tagEntity1));
        // when(tagRepository.findById(5L)).thenReturn(Optional.of(tagEntity2));
    // }

    @Test
    @DisplayName("Teste que registra uma tag")
    void registrarTagTest() {
        List<TextoEntity> novoTexto = new ArrayList<>();
        TagEntity novaTag = new TagEntity(6L, "novaTag", novoTexto);
        when(tagService.registrarTag(novaTag)).thenReturn(novaTag);

        ResponseEntity<TagEntity> tagRegistrada = tagController.registrar(novaTag);
        assertEquals(HttpStatus.OK, tagRegistrada.getStatusCode());
        assertEquals(novaTag, tagRegistrada.getBody());
    }

    @Test
    @DisplayName("Teste que lista todas as tags")
    void listarTagsTest() {
        List<TagEntity> listaTags = new ArrayList<>();
        List<TextoEntity> textoTeste1 = new ArrayList<>();
        List<TextoEntity> textoTeste2 = new ArrayList<>();
        List<TextoEntity> textoTeste3 = new ArrayList<>();

        listaTags.add(new TagEntity(1L, "Titulo Teste", textoTeste1));
        listaTags.add(new TagEntity(2L, "Titulo Horrível", textoTeste2));
        listaTags.add(new TagEntity(3L, "Titulo Maravilhoso", textoTeste3));
        when(tagService.listAllTag()).thenReturn(listaTags);

        ResponseEntity<List<TagEntity>> lista = this.tagController.listAll();
        assertEquals(HttpStatus.OK, lista.getStatusCode());
        assertEquals(3, lista.getBody().size());
    }

    @Test
    @DisplayName("Teste que busca uma tag por Id")
    void buscarTagsByIdTest() {
        List<TextoEntity> textoTeste1 = new ArrayList<>();
        TagEntity tagEntity1 = new TagEntity(4L, "Tag teste", textoTeste1);
        when(tagService.findById(4L)).thenReturn(tagEntity1);
        
        ResponseEntity<TagEntity> TagById = this.tagController.findById(4L);
        assertEquals(HttpStatus.OK, TagById.getStatusCode());
        assertEquals("Tag teste", TagById.getBody().getTituloTag());
    }

    @Test
    @DisplayName("Testa um erro ao tentar buscar uma tag por Id")
    void buscarTagsByIdTestErro() {
        when(tagService.findById(10L)).thenThrow(new NoSuchElementException("Tag não encontrada"));

        ResponseEntity<TagEntity> TagByIdErro = this.tagController.findById(10L);
        assertEquals(HttpStatus.BAD_REQUEST, TagByIdErro.getStatusCode());
    }

    // @Test
    // @DisplayName("Teste para deletar tags pelo Id")
    // void deletarTagById() {
    //     List<TextoEntity> textoTeste1 = new ArrayList<>();
    //     TagEntity tagEntity1 = new TagEntity(4L, "Tag teste", textoTeste1);
    //     when(tagService.findById(4L)).thenReturn(tagEntity1);

    //     ResponseEntity<Void> tagByIdErro = this.tagController.delete(4L);
    //     assertEquals(HttpStatus.NO_CONTENT, tagByIdErro.getStatusCode());
    // }

    // @Test
    // @DisplayName("Testa um erro ao deletar uma tag pelo Id")
    // void deletarTagByIdTestErro() {
    //     ResponseEntity<Void> tagByIdErro = this.tagController.delete(69L);
    //     assertEquals(HttpStatus.BAD_REQUEST, tagByIdErro.getStatusCode());
    // }

    @Test
    @DisplayName("Teste para alteração de tags pelo Id")
    void alterarTagById() throws Exception {
    List<TextoEntity> textoAlteracao =  new ArrayList<>();
    TagEntity novaTag = new TagEntity(10L, "Tag Nova", textoAlteracao);
    
    when(tagService.alterarTag(10L, novaTag)).thenReturn(novaTag);
    
    ResponseEntity<TagEntity> resposta = tagController.alterar(10L, novaTag);
    assertEquals(HttpStatus.OK, resposta.getStatusCode());
    assertEquals(novaTag, resposta.getBody());

    // verify(tagService, times(1)).alterarTag(eq(1L), any(TagEntity.class));
    }
}
